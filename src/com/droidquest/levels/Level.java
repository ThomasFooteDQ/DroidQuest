package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.SoundClip;
import com.droidquest.Wire;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Spark;
import com.droidquest.devices.Device;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.Initializer;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Level implements ImageObserver, Serializable {
    public Item player;
    public Item gameCursor;
    public Item solderingPen;
    public Item remote;
    public Item toolbox;
    public Item currentViewer;
    public Item helpCam;
    public Item paintbrush;
    public transient Portal portal;
    public boolean electricity;

    public Vector<Room> rooms = new Vector<Room>();
    public Vector<Material> materials = new Vector<Material>();
    public Vector<Item> items = new Vector<Item>();
    public Vector<Spark> sparks = new Vector<Spark>();

    public transient RoomDisplay roomdisplay;
    private transient Vector<Room> invRooms = new Vector<Room>();
    private transient Vector<Integer> invRoomIndexes = new Vector<Integer>();
    private transient Vector<Material> invMaterials = new Vector<Material>();
    private transient Vector<Integer> invMaterialIndexes = new Vector<Integer>();
    private transient Vector<Item> invItems = new Vector<Item>();
    private transient Vector<Integer> invItemIndexes = new Vector<Integer>();

    public transient HashMap<String, SoundClip> sounds = new HashMap<String, SoundClip>();

    public transient Random random = new Random();
    public transient static String ATTACHSOUND = "attach.WAV";
    public transient static String DETATCHSOUND = "detatch.WAV";
    public transient static String PICKUPSOUND = "pickup.WAV";
    public transient static String DROPSOUND = "drop.WAV";
    public transient static String BEEPSOUND = "beep.WAV";
    public transient static String BUMPSOUND = "bump.WAV";
    public transient static String CHARGESOUND = "charge.WAV";
    public transient static String DISCHARGESOUND = "discharge.WAV";
    public transient static String BURNSOUND = "burn.WAV";
    public transient static String ENDMUSICSOUND = "liberty.mid";
    public transient static String STARTMUSICSOUND = "sp001.wav";
    public transient static String TELEPORTSOUND = "teleport.WAV";
    public transient static String TRANSPORTSOUND = "transport.WAV";
    private String[] soundFiles = {
            ATTACHSOUND, DETATCHSOUND, PICKUPSOUND, DROPSOUND,
            BEEPSOUND, BUMPSOUND, CHARGESOUND, DISCHARGESOUND,
            BURNSOUND, ENDMUSICSOUND, STARTMUSICSOUND,
            TELEPORTSOUND, TRANSPORTSOUND
    };
    public transient boolean cheatmode = true;

    Level() {
        Item.level = this;
        Room.level = this;
        Material.level = this;
        InitSounds();
    }

    public Level(RoomDisplay rd) {
        roomdisplay = rd;
        Item.level = this;
        Room.level = this;
        Material.level = this;
        random.setSeed(System.currentTimeMillis());
        InitSounds();
    }

    public void LinkRoomsLeftRight(int L, int R) {
        rooms.elementAt(L).rightRoom = rooms.elementAt(R);
        rooms.elementAt(R).leftRoom = rooms.elementAt(L);
    }

    public void LinkRoomsUpDown(int U, int D) {
        rooms.elementAt(U).downRoom = rooms.elementAt(D);
        rooms.elementAt(D).upRoom = rooms.elementAt(U);
    }

    void LinkRoomsHorizontally(int[] roomlist) {
        for (int a = 0; a < roomlist.length - 1; a++) {
            LinkRoomsLeftRight(roomlist[a], roomlist[a + 1]);
        }
    }

    void LinkRoomsVertically(int[] roomlist) {
        for (int a = 0; a < roomlist.length - 1; a++) {
            LinkRoomsUpDown(roomlist[a], roomlist[a + 1]);
        }
    }

    void LinkRoomsGrid(int[][] roomgrid) {
        // Requires a rectangular grid... each array is the same length
        int height = roomgrid.length;
        int width = roomgrid[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < width - 1) {
                    LinkRoomsLeftRight(roomgrid[y][x], roomgrid[y][x + 1]);
                }
                if (y < height - 1) {
                    LinkRoomsUpDown(roomgrid[y][x], roomgrid[y + 1][x]);
                }
            }
        }
    }

    public Material materialAt(int x, int y, Room r) {
        if (x < 0 || x > 19 || y < 0 || y > 11) {
            Material mat = materials.elementAt(0);
            if (x < 0) {
                if (r.leftRoom != null) {
                    mat = materials.elementAt(r.leftRoom.RoomArray[y][x + 20]);
                }
            }
            if (x > 19) {
                if (r.rightRoom != null) {
                    mat = materials.elementAt(r.rightRoom.RoomArray[y][x - 20]);
                }
            }
            if (y < 0) {
                if (r.upRoom != null) {
                    mat = materials.elementAt(r.upRoom.RoomArray[y + 12][x]);
                }
            }
            if (y > 11) {
                if (r.downRoom != null) {
                    mat = materials.elementAt(r.downRoom.RoomArray[y - 12][x]);
                }
            }
            return mat;
        }
        else {
            return materials.elementAt(r.RoomArray[y][x]);
        }
    }

    public Item FindNearestItem(Item a) {
        Item nearest = null;
        int dx = 100;
        int dy = 100;
        int cxa = a.x + a.getWidth() / 2;
        int cya = a.y + a.getHeight() / 2;
        for (int i = 0; i < items.size(); i++) {
            Item b = items.elementAt(i);
            if (a.Overlaps(b)) {
                int cxb = b.x + b.getWidth() / 2;
                int cyb = b.y + b.getHeight() / 2;
                int dx2 = Math.abs(cxb - cxa);
                int dy2 = Math.abs(cyb - cya);
                if ((dx2 + dy2) < (dx + dy)) {
                    nearest = b;
                    dx = dx2;
                    dy = dy2;
                }
            }
        }
        return nearest;
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        // This does nothing, but allows Images to be drawn freely onto
        // other images. The only reason I have this function is so I have
        // an object that implements ImageObserver, which for some stupid
        // reason is a requirement for several Graphics methods.
        return false;
    }

    public void writeObject(ObjectOutputStream s) throws IOException {
        // Save Basic Room Data
        int a;
        s.writeInt(rooms.size());
        for (a = 0; a < rooms.size(); a++) {
            s.writeObject(rooms.elementAt(a));
        }

        // Save Materials Data
        s.writeInt(materials.size());
        for (a = 0; a < materials.size(); a++) {
            s.writeObject(materials.elementAt(a));
        }

        // Save Basic Items data
        s.writeInt(items.size());
        for (a = 0; a < items.size(); a++) {
            s.writeObject(items.elementAt(a));
        }

        // Save Electricity
        s.writeBoolean(electricity);

        // Save Player, GameCursor, CurrentViewer
        s.writeInt(items.indexOf(player));
        s.writeInt(items.indexOf(gameCursor));
        s.writeInt(items.indexOf(currentViewer));
        s.writeInt(items.indexOf(solderingPen));
        s.writeInt(items.indexOf(remote));
        s.writeInt(items.indexOf(toolbox));
        s.writeInt(items.indexOf(helpCam));
        s.writeInt(items.indexOf(paintbrush));

        // Save Room References (UDLRrooms, PortalItem, Wires)
        for (a = 0; a < rooms.size(); a++) {
            rooms.elementAt(a).writeRef(s);
        }

        // Save Item References
        for (a = 0; a < items.size(); a++) {
            items.elementAt(a).writeRef(s);
        }

    }

    public void readObject(ObjectInputStream s) throws IOException {
        int a;
        int numRooms = s.readInt();
        rooms = new Vector<Room>();
        for (a = 0; a < numRooms; a++) {
            try {
                Room r = (Room) s.readObject();
                rooms.addElement(r);
            }
            catch (ClassNotFoundException e) {
            }
        }

        int numMaterials = s.readInt();
        materials = new Vector<Material>();
        for (a = 0; a < numMaterials; a++) {
            try {
                Material m = (Material) s.readObject();
                materials.addElement(m);
            }
            catch (ClassNotFoundException e) {
            }
        }

        int numItems = s.readInt();
        items = new Vector<Item>();
        for (a = 0; a < numItems; a++) {
            try {
                Item i = (Item) s.readObject();
                items.addElement(i);
            }
            catch (ClassNotFoundException e) {
            }
        }

        electricity = s.readBoolean();
        player = FindItem(s.readInt());
        gameCursor = FindItem(s.readInt());
        currentViewer = FindItem(s.readInt());
        solderingPen = FindItem(s.readInt());
        remote = FindItem(s.readInt());
        toolbox = FindItem(s.readInt());
        helpCam = FindItem(s.readInt());
        paintbrush = FindItem(s.readInt());

        // Read Room References (UDLRrooms, PortalItem, Wires)
        for (a = 0; a < numRooms; a++) {
            rooms.elementAt(a).readRef(s);
        }

        // Read Item References
        for (a = 0; a < numItems; a++) {
            items.elementAt(a).readRef(s);
        }

        // Generate Material Icons
        for (a = 0; a < numMaterials; a++) {
            materials.elementAt(a).GenerateIcons();
        }

    }

    public Item FindItem(String classname) {
        for (Item item : items) {
            if (item != null && item.getClass().toString().endsWith(classname)) {
                return item;
            }
        }
        return null;
    }

    public Item FindItem(int itemIndex) {
        if (itemIndex == -1) {
            return null;
        }
        if (itemIndex >= items.size()) {
            return null;
        }
        return items.elementAt(itemIndex);
    }

    public Room FindRoom(int roomIndex) {
        if (roomIndex == -1) {
            return null;
        }
        if (roomIndex >= rooms.size()) {
            return null;
        }
        return rooms.elementAt(roomIndex);
    }

    public void Empty() {
        // This goes through the entire level structure and removes all
        // references to everything.

        int a;

        Room.level = null;
        Item.level = null;

        // Remove all Items
        for (a = 0; a < items.size(); a++) {
            Item item = items.elementAt(a);
            item.Erase();
        }
        items.clear();
        items = null;

        // Remove all Materials
        materials.clear();
        materials = null;

        // Remove all Rooms
        for (a = 0; a < rooms.size(); a++) {
            Room room = rooms.elementAt(a);
            room.Erase();
        }
        rooms.clear();
        rooms = null;

        // Remove all Local References
        player = null;
        gameCursor = null;
        solderingPen = null;
        remote = null;
        toolbox = null;
        currentViewer = null;
        helpCam = null;

        System.gc(); // Run Garbage Collection
    }

    public void WriteInventory() {
        if (player.carrying == null) {
            return;
        }
        AddItemToInventory(player.carrying);
        LinkInventory();
        SaveInventory();
    }

    void AddItemToInventory(Item item) {
        // Save Item

        if (item instanceof ToolBox) {
            return;
        }

        Item clonedItem = (Item) item.clone();
        invItems.addElement(clonedItem);
        invItemIndexes.addElement(items.indexOf(item));

        System.out.println((invItems.size() - 1) + ": "
                + "Saving " + item.getClass() + ", index=" + items.indexOf(item));

        if (item.carriedBy == player) {
            clonedItem.carriedBy = null;
            clonedItem.room = null;
        }

        if (item.carriedBy == player.carrying) {
            clonedItem.room = null;
        }

        // Save carried Item
        if (item.carrying != null && item.room == player.room) {
            AddItemToInventory(item.carrying);
        }

        if (item.InternalRoom != null) {
            // Store Copy of Room
            Room clonedRoom = (Room) item.InternalRoom.clone();
            invRooms.addElement(clonedRoom);
            invRoomIndexes.addElement(rooms.indexOf(item.InternalRoom));
            System.out.println("Saving Room to Inventory.");

            // Store all Materials in the Internal Room
            int matcount = 0;
            for (int Y = 0; Y < 12; Y++) {
                for (int X = 0; X < 20; X++) {
                    int matIndex = item.InternalRoom.RoomArray[Y][X];
                    Material originalMaterial = materials.elementAt(matIndex);
                    Material clonedMaterial = (Material) originalMaterial.clone();
                    boolean found = false;
                    for (int a = 0; a < invMaterials.size(); a++) {
                        Material testMaterial = invMaterials.elementAt(a);
                        if (testMaterial.equals(clonedMaterial)) {
                            found = true;
                        }
                    }
                    if (!found) {
                        invMaterials.addElement(clonedMaterial);
                        invMaterialIndexes.addElement(matIndex);
                        matcount++;
                    }
                }
            }
            System.out.println("Saved " + matcount + "Materials to Inventory.");

            // Store all Items in the Internal Room
            if (item.InternalRoom != null) {
                for (int a = 0; a < items.size(); a++) {
                    Item internalItem = items.elementAt(a);
                    if (internalItem.room == item.InternalRoom) {
                        AddItemToInventory(internalItem);
                    }
                }
            }
        }
    }

    void LinkInventory() {
        for (int a = 0; a < invItems.size(); a++) {
            Item item = invItems.elementAt(a);
            if (item.carrying != null) {
                Integer realItemIndex = items.indexOf(item.carrying);
                int b = 0;
                while (invItemIndexes.elementAt(b).intValue()
                        != realItemIndex.intValue()) {
                    b++;
                }
                item.carrying = invItems.elementAt(b);
                System.out.println(item.getClass()
                        + " carrying "
                        + item.carrying.getClass());
            }
            if (item.carriedBy != null) {
                Integer realItemIndex = items.indexOf(item.carriedBy);
                int b = 0;
                while (invItemIndexes.elementAt(b).intValue()
                        != realItemIndex.intValue()) {
                    b++;
                }
                item.carriedBy = invItems.elementAt(b);
                System.out.println(item.getClass()
                        + " carriedBy "
                        + item.carriedBy.getClass());
            }
            if (item.room != null) {
                Integer realRoomIndex = rooms.indexOf(item.room);
                int b = 0;
                while (invRoomIndexes.elementAt(b).intValue()
                        != realRoomIndex.intValue()) {
                    b++;
                }
                item.room = invRooms.elementAt(b);
                System.out.println(item.getClass()
                        + " is in room #" + b);
            }
            if (item.InternalRoom != null) {
                Integer realInternalRoomIndex = rooms.indexOf(item.InternalRoom);
                int b = 0;
                while (invRoomIndexes.elementAt(b).intValue()
                        != realInternalRoomIndex.intValue()) {
                    b++;
                }
                item.InternalRoom = invRooms.elementAt(b);
                //		  item.InternalRoom.portalItem = item;
                System.out.println(item.getClass()
                        + " has internal room #" + b);
            }
        }

        for (int a = 0; a < invRooms.size(); a++) {
            Room room = invRooms.elementAt(a);
            if (room.upRoom != null) {
                Integer realRoomIndex = rooms.indexOf(room.upRoom);
                int b = 0;
                while (invRoomIndexes.elementAt(b).intValue()
                        != realRoomIndex.intValue()) {
                    b++;
                }
                room.upRoom = invRooms.elementAt(b);
            }
            if (room.downRoom != null) {
                Integer realRoomIndex = rooms.indexOf(room.downRoom);
                int b = 0;
                while (invRoomIndexes.elementAt(b).intValue()
                        != realRoomIndex.intValue()) {
                    b++;
                }
                room.downRoom = invRooms.elementAt(b);
            }
            if (room.leftRoom != null) {
                Integer realRoomIndex = rooms.indexOf(room.leftRoom);
                int b = 0;
                while (invRoomIndexes.elementAt(b).intValue()
                        != realRoomIndex.intValue()) {
                    b++;
                }
                room.leftRoom = invRooms.elementAt(b);
            }
            if (room.rightRoom != null) {
                Integer realRoomIndex = rooms.indexOf(room.rightRoom);
                int b = 0;
                while (invRoomIndexes.elementAt(b).intValue()
                        != realRoomIndex.intValue()) {
                    b++;
                }
                room.rightRoom = invRooms.elementAt(b);
            }
            if (room.portalItem != null) {
                Integer realItemIndex = items.indexOf(room.portalItem);
                int b = 0;
                while (invItemIndexes.elementAt(b).intValue()
                        != realItemIndex.intValue()) {
                    b++;
                }
                room.portalItem = invItems.elementAt(b);
                System.out.println("Room #" + a + " is inside "
                        + room.portalItem.getClass());
            }

            for (int X = 0; X < 20; X++) {
                for (int Y = 0; Y < 12; Y++) {
                    Integer realMatIndex = room.RoomArray[Y][X];
                    room.RoomArray[Y][X] = invMaterialIndexes.indexOf(realMatIndex);
                }
            }

            for (int w = 0; w < room.wires.size(); w++) {
                Wire wire = room.wires.elementAt(w);

                Integer realItemIndex = items.indexOf(wire.fromPort.myDevice);
                int b = 0;
                while (invItemIndexes.elementAt(b).intValue()
                        != realItemIndex.intValue()) {
                    b++;
                }
                Item invItem = invItems.elementAt(b);
                Device invDevice = (Device) invItem;
                b = 0;
                while (((Device) (wire.fromPort.myDevice)).ports[b] != wire.fromPort) {
                    b++;
                }
                wire.fromPort = invDevice.ports[b];
                wire.fromPort.myWire = wire;
                wire.fromPort.myDevice = invDevice;

                realItemIndex = items.indexOf(wire.toPort.myDevice);
                b = 0;
                while (invItemIndexes.elementAt(b).intValue()
                        != realItemIndex.intValue()) {
                    b++;
                }
                invItem = invItems.elementAt(b);
                invDevice = (Device) invItem;
                b = 0;
                while (((Device) (wire.toPort.myDevice)).ports[b] != wire.toPort) {
                    b++;
                }
                wire.toPort = invDevice.ports[b];
                wire.toPort.myWire = wire;
                wire.toPort.myDevice = invDevice;

                if (wire.fromPort.type == Port.TYPE_INPUT) {
                    wire.inPort = wire.fromPort;
                    wire.outPort = wire.toPort;
                }
                else {
                    wire.inPort = wire.toPort;
                    wire.outPort = wire.fromPort;
                }
            }
        }
    }

    void SaveInventory() {
        if (invItems.size() == 0) {
            return;
        }

        String filename = "temp.inv";
        System.out.println("Saving Inventory ");
        try {
            FileOutputStream out = new FileOutputStream(filename);
            ObjectOutputStream s = new ObjectOutputStream(out);

            s.writeInt(invRooms.size());
            for (int a = 0; a < invRooms.size(); a++) {
                s.writeObject(invRooms.elementAt(a));
            }

            s.writeInt(invMaterials.size());
            for (int a = 0; a < invMaterials.size(); a++) {
                s.writeObject(invMaterials.elementAt(a));
            }

            s.writeInt(invItems.size());
            for (int a = 0; a < invItems.size(); a++) {
                s.writeObject(invItems.elementAt(a));
            }

            // Save Room References (UDLRrooms, PortalItem, Wires)
            for (int a = 0; a < invRooms.size(); a++) {
                Room room = invRooms.elementAt(a);
                s.writeInt(invRooms.indexOf(room.upRoom));
                s.writeInt(invRooms.indexOf(room.downRoom));
                s.writeInt(invRooms.indexOf(room.rightRoom));
                s.writeInt(invRooms.indexOf(room.leftRoom));
                s.writeInt(invItems.indexOf(room.portalItem));

                s.writeInt(room.wires.size());
                for (int b = 0; b < room.wires.size(); b++) {
                    Wire wire = room.wires.elementAt(b);
                    int p;
                    s.writeInt(invItems.indexOf(wire.fromPort.myDevice)); // Index of fromport device
                    p = 0;
                    while (((Device) wire.fromPort.myDevice).ports[p] != wire.fromPort) {
                        p++;
                    }
                    s.writeInt(p); // Index of fromport (as device.ports[?]

                    s.writeInt(invItems.indexOf(wire.toPort.myDevice)); // Index of toport device
                    p = 0;
                    while (((Device) wire.toPort.myDevice).ports[p] != wire.toPort) {
                        p++;
                    }
                    s.writeInt(p); // Index of toport (as device.ports[?]

                    s.writeInt(invItems.indexOf(wire.inPort.myDevice)); // Index of inport device
                    p = 0;
                    while (((Device) wire.inPort.myDevice).ports[p] != wire.inPort) {
                        p++;
                    }
                    s.writeInt(p); // Index of inport (as device.ports[?]

                    s.writeInt(invItems.indexOf(wire.outPort.myDevice)); // Index of outport device
                    p = 0;
                    while (((Device) wire.outPort.myDevice).ports[p] != wire.outPort) {
                        p++;
                    }
                    s.writeInt(p); // Index of outport (as device.ports[?]
                }
            }

            // Save Item References
            for (int a = 0; a < invItems.size(); a++) {
                Item item = invItems.elementAt(a);
                s.writeInt(invItems.indexOf(item.carrying));
                s.writeInt(invItems.indexOf(item.carriedBy));
                s.writeInt(invRooms.indexOf(item.room));
                s.writeInt(invRooms.indexOf(item.InternalRoom));
                if (item.getClass().toString().endsWith("SmallChip")) {
                    SmallChip sc = (SmallChip) item;
                    String chipfilename = "tmp" + a + ".chip";
                    sc.SaveChip(chipfilename);
                }
            }

            s.flush();
            s.close();
            out.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
        }

    }

    public void LoadInventory() {
        roomdisplay.timer.stop();
        String filename = "temp.inv";
        System.out.println("Loading Inventory ");
        int orgNumRooms = rooms.size();
        int orgNumMaterials = materials.size();
        int orgNumItems = items.size();

        try {
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream s = new ObjectInputStream(in);

            int numRooms = s.readInt();
            System.out.println("Loading " + numRooms + " Rooms from Inventory");
            for (int a = 0; a < numRooms; a++) {
                try {
                    Room room = (Room) s.readObject();
                    rooms.addElement(room);
                }
                catch (ClassNotFoundException e) {
                }
            }

            int numMaterials = s.readInt();
            System.out.println("Loading " + numMaterials + " Materials from Inventory");
            for (int a = 0; a < numMaterials; a++) {
                try {
                    Material material = (Material) s.readObject();
                    materials.addElement(material);
                    material.GenerateIcons();
                }
                catch (ClassNotFoundException e) {
                }
            }

            int numItems = s.readInt();
            for (int a = 0; a < numItems; a++) {
                try {
                    Item item = (Item) s.readObject();
                    items.addElement(item);
                    System.out.println("Loading " + item.getClass() + " from Inventory");
                }
                catch (ClassNotFoundException e) {
                }
            }

            for (int a = 0; a < numRooms; a++) {
                Room room = rooms.elementAt(orgNumRooms + a);
                int upRoomIndex = s.readInt();
                int downRoomIndex = s.readInt();
                int rightRoomIndex = s.readInt();
                int leftRoomIndex = s.readInt();
                int portalItemIndex = s.readInt();
                if (upRoomIndex != -1) {
                    room.upRoom = rooms.elementAt(upRoomIndex + orgNumRooms);
                }
                if (downRoomIndex != -1) {
                    room.downRoom = rooms.elementAt(downRoomIndex + orgNumRooms);
                }
                if (rightRoomIndex != -1) {
                    room.rightRoom = rooms.elementAt(rightRoomIndex + orgNumRooms);
                }
                if (leftRoomIndex != -1) {
                    room.leftRoom = rooms.elementAt(leftRoomIndex + orgNumRooms);
                }
                if (portalItemIndex != 1) {
                    room.portalItem = items.elementAt(portalItemIndex + orgNumItems);
                    System.out.println("Room #" + a + " has portalItem:" + room.portalItem.getClass());
                }

                int numWires = s.readInt();
                System.out.println("Linking " + numWires + " wires");

                for (int b = 0; b < numWires; b++) {
                    Wire wire = room.wires.elementAt(b);

                    Item tmpItem = items.elementAt(s.readInt() + orgNumItems);
                    Device tmpDevice = (Device) tmpItem;
                    wire.fromPort = tmpDevice.ports[s.readInt()];
                    wire.fromPort.myWire = wire;

                    tmpItem = items.elementAt(s.readInt() + orgNumItems);
                    tmpDevice = (Device) tmpItem;
                    wire.toPort = tmpDevice.ports[s.readInt()];
                    wire.toPort.myWire = wire;

                    tmpItem = items.elementAt(s.readInt() + orgNumItems);
                    tmpDevice = (Device) tmpItem;
                    wire.inPort = tmpDevice.ports[s.readInt()];

                    tmpItem = items.elementAt(s.readInt() + orgNumItems);
                    tmpDevice = (Device) tmpItem;
                    wire.outPort = tmpDevice.ports[s.readInt()];

                }

                // Modify the Material Indexes
                for (int X = 0; X < 20; X++) {
                    for (int Y = 0; Y < 12; Y++) {
                        room.RoomArray[Y][X] += orgNumMaterials;
                    }
                }

                room.GenerateArray();
            }

            for (int a = 0; a < numItems; a++) {
                Item item = items.elementAt(orgNumItems + a);
                int carryingIndex = s.readInt();
                int carriedByIndex = s.readInt();
                int roomIndex = s.readInt();
                int internalRoomIndex = s.readInt();
                if (carryingIndex != -1) {
                    item.carrying = items.elementAt(carryingIndex + orgNumItems);
                    System.out.println(item.getClass() + " carries " + item.carrying.getClass());
                }
                if (carriedByIndex != -1) {
                    item.carriedBy = items.elementAt(carriedByIndex + orgNumItems);
                    System.out.println(item.getClass() + " carriedBy " + item.carriedBy.getClass());
                }
                if (roomIndex != -1) {
                    item.room = rooms.elementAt(roomIndex + orgNumRooms);
                    System.out.println(item.getClass() + " is in room #" + roomIndex);
                }
                else {
                    if (gameCursor != null) {
                        item.room = gameCursor.room;
                    }
                    else {
                        System.out.println("gameCursor = null");
                    }
                }
                if (internalRoomIndex != -1) {
                    item.InternalRoom = rooms.elementAt(internalRoomIndex + orgNumRooms);
                    System.out.println(item.getClass() + " has InternalRoom #" + internalRoomIndex);
                    item.InternalRoom.portalItem = item;
                }
                if (item.isDevice()) {
                    Device device = (Device) item;
                    for (int b = 0; b < device.ports.length; b++) {
                        device.ports[b].myDevice = device;
                    }
                }
            }

            Item item = items.elementAt(orgNumItems);
            gameCursor.carrying = item;
            item.carriedBy = gameCursor;

            s.close();
            in.close();
            File f = new File(filename);
            f.delete();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            roomdisplay.timer.start();
            return;
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        roomdisplay.timer.start();

        // Remove all unnecessary Materials
        for (int a = 0; a < materials.size() - 1; a++) {
            for (int b = a + 1; b < materials.size(); b++) {
                Material mat1 = materials.elementAt(a);
                Material mat2 = materials.elementAt(b);
                if (mat1.equals(mat2)) {
                    materials.remove(mat2);
                    for (int c = 0; c < rooms.size(); c++) {
                        Room room = rooms.elementAt(c);
                        for (int Y = 0; Y < 12; Y++) {
                            for (int X = 0; X < 20; X++) {
                                if (room.RoomArray[Y][X] == b) {
                                    room.RoomArray[Y][X] = a;
                                }
                                if (room.RoomArray[Y][X] > b) {
                                    room.RoomArray[Y][X] -= 1;
                                }
                            }
                        }
                    }
                    b--;
                }
            }
        }

        for (int a = orgNumItems; a < items.size(); a++) {
            Item item = items.elementAt(a);
            item.GenerateIcons();
            if (item.getClass().toString().endsWith("SmallChip")) {
                SmallChip sc = (SmallChip) item;
                String chipfilename = "tmp" + (a - orgNumItems) + ".chip";
                sc.LoadChip(chipfilename);
                File f = new File(chipfilename);
                f.delete();
            }
        }


    }

    void InitSounds() {
        for (String soundFile : soundFiles) {
            sounds.put(soundFile, new SoundClip(soundFile));
        }
    }

    public void PlaySound(Room room, String soundname) {
        if (!roomdisplay.useSounds) {
            return;
        }

        boolean flag = true;
        if (currentViewer != null) {
            if (room != currentViewer.room) {
                flag = false;
            }
        }

        if (flag) {
            System.out.println("Playing sound " + soundname);
            SoundClip soundClip = sounds.get(soundname);
            if (soundClip != null) {
                soundClip.audioClip.play();
            }
            System.out.println("Done");
        }
    }

    public void Init() {
        // Generate all Room Material Arrays
        for (int a = 0; a < rooms.size(); a++) {
            Room room = rooms.elementAt(a);
            room.GenerateArray();
        }

        // Randomize the level
        Initializer initializer;

        for (Item item : items) {
            if (item.getClass().toString().endsWith("Init")) {
                initializer = (Initializer) item;
                initializer.Init();
            }
        }
    }

}

