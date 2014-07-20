package com.droidquest.pathfinder;

import com.droidquest.materials.Material;

/**
 * Represents a Node in a path, using the A* algorithm.
 */
public class Node implements Cloneable {
    private int x = -1;
    private int y = -1;
    private Material material = null;

    private int gCost = 0;
    private int hCost = 0;
    private int fCost = 0;

    Node parent = null;

    /**
     * Create a new, empty Node
     */
    public Node() {
        this(-1, -1, null, null);
    }

    /**
     * Create a new Node with given coordinates
     * @param x Coordinate (of the room array, not screen location)
     * @param y Coordinate (of the room array, not screen location)
     */
    public Node(int x, int y) {
        this(x, y, null, null);
    }

    /**
     * Create a new room Node with the given coordinates and material
     * @param x Coordinate (of the room array, not screen location)
     * @param y Coordinate (of the room array, not screen location)
     * @param mat Material at the Node location
     */
    public Node(int x, int y, Material mat) {
        this(x, y, null, mat);
    }

    /**
     * Create a new room Node with a given parent Node (for pathing)
     * @param x Coordinate (of the room array, not screen location)
     * @param y Coordinate (of the room array, not screen location)
     * @param parent current Parent node
     */
    public Node(int x, int y, Node parent) {
        this(x, y, parent, null);
    }

    /**
     * Create a new room node with a given parent Node and Material
     * @param x Coordinate (of the room array, not screen location)
     * @param y Coordinate (of the room array, not screen location)
     * @param parent current Parent node
     * @param mat Material at the Node location
     */
    public Node(int x, int y, Node parent, Material mat) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.material = mat;
    }

    /**
     * Retrieve the current room location X coordinate (room array, not screen location)
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Set the current room location X coordinate (room array, not screen location)
     * @param x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieve the current room location Y coordinate (room array, not screen location)
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Set the current room Y coordinate (room array, not screen location)
     * @param y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieve the currently set Material for the Node (or null if not set)
     * @return Material
     * @see com.droidquest.materials.Material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Set the Material for the Node
     * @param material Material
     * @see com.droidquest.materials.Material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Create a clone of this Node
     * @return Node clone (as Object)
     */
    public Object clone() {
        try {
            return super.clone();
        }
        catch(CloneNotSupportedException cnse) {
            System.out.println("Clone not supported!");
            return this;
        }
    }

    /**
     * Set the gCost (cost to the current Node from the origin)
     * @return int cost
     */
    public int getgCost() {
        return gCost;
    }

    /**
     * Set the gCost (cost to the current Node from the origin)
     * @param gCost int cost
     */
    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    /**
     * Retrieves the hCost (heuristic cost) from the current Node to the end Node
     * @return int cost
     */
    public int gethCost() {
        return hCost;
    }

    /**
     * Sets the hCost (heuristic cost) from the current Node to the end Node
     * @param hCost int cost
     */
    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    /**
     * Retrieves the fCost (full cost) for the Node
     * @return int cost
     */
    public int getfCost() {
        return fCost;
    }

    /**
     * Sets the fCost (full cost) for the Node
     * @param fCost int cost
     */
    public void setfCost(int fCost) {
        this.fCost = fCost;
    }
}
