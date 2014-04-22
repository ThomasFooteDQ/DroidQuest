package com.droidquest.pathfinder;

import com.droidquest.Room;
import com.droidquest.items.Item;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Finds a path between two places on the map, using the A* algorithm
 */
public class Pathfinder {
    private ArrayList<ArrayList<Node>> nodeList = new ArrayList<ArrayList<Node>>();

    private ArrayList<Node> openList = new ArrayList<Node>();

    private ArrayList<Node> closedList = new ArrayList<Node>();

    /**
     * Begin a search for a path. Returns an empty ArrayList if no path found.
     * @param startX int starting X coordinate (not screen location)
     * @param startY int starting Y coordinate (not screen location)
     * @param endX int ending X coordinate (not screen location)
     * @param endY int ending Y coordinate (not screen location)
     * @param player Item player used to verify whether a Node is passable.
     * @return ArrayList of Nodes representing the path, or empty list
     */
    public ArrayList<Node> search(int startX, int startY, int endX, int endY, Item player) {
        ArrayList<Node> results = new ArrayList<Node>();
        if(startY < 0 || startY > nodeList.size() || startX < 0 || startX > nodeList.get(startY).size()) {
            System.out.println("Starting node outside of room bounds!");
            return results;
        }

        if(endY < 0 || endY > nodeList.size() || endX < 0 || endX > nodeList.get(endY).size()) {
            System.out.println("Ending node outside of room bounds!");
            return results;
        }

        if(!nodeList.get(startY).get(startX).getMaterial().Passable(player)) {
            System.out.println("Starting node isn't passable!");
            return results;
        }

        if(!nodeList.get(endY).get(endX).getMaterial().Passable(player)) {
            System.out.println("Ending node isn't passable!");
            return results;
        }

        Node endNode = nodeList.get(endY).get(endX);

        // Add nodes adjacent to starting node
        Node startNode = nodeList.get(startY).get(startX);
        startNode.setgCost(0);
        startNode.sethCost(manhattanDistance(startNode, endNode));
        startNode.setfCost(startNode.gethCost());
        startNode.parent = null;
        openList.add(startNode);

        findLowestCost(results, endNode, player);

        return results;
    }

    private void findLowestCost(ArrayList<Node> results, Node endNode, Item player) {

        while(openList.size() > 0) {
            Node lowestCost = null;
            for (Node node : openList) {
                if (lowestCost == null || node.getfCost() < lowestCost.getfCost()) {
                    lowestCost = node;
                }
            }

            openList.remove(lowestCost);
            closedList.add(lowestCost);

            if (lowestCost != null && lowestCost == endNode) {
                Node pathNode = lowestCost;
                while (pathNode.parent != null) {
                    results.add(new Node(pathNode.getX() * 28, pathNode.getY() * 32));
                    pathNode = pathNode.parent;
                }
                Collections.reverse(results);
                return;

            }

            addSurroundingNodes(lowestCost, endNode, player);
        }

        System.out.println("Could not find any nodes to process, no path.");

    }




    private void addSurroundingNodes(Node parent, Node endNode, Item player) {
        if(parent == null) {
            return;
        }

        int x = parent.getX();
        int y = parent.getY();
        for(int deltaY = -1; deltaY <= 1; deltaY++) {
            for(int deltaX = -1; deltaX <=1; deltaX++) {
                if(deltaX != 0 || deltaY != 0) {
                    int checkX = x + deltaX;
                    int checkY = y + deltaY;
                    if(checkY >= 0 && checkY < nodeList.size() && checkX >= 0 && checkX < nodeList.get(checkY).size() && nodeList.get(checkY).get(checkX).getMaterial().Passable(player)) {
                        Node currentNode = nodeList.get(checkY).get(checkX);
                        if(closedList.contains(currentNode)) {
                            continue;
                        }
                        int diagonalCost = 0;
                        if(deltaX != 0 && deltaY != 0) {

                            // Diagonal, check to make sure other squares are passable
                            if(!nodeList.get(y).get(checkX).getMaterial().Passable(player) || !nodeList.get(checkY).get(x).getMaterial().Passable(player)) {
                                diagonalCost = 500; // don't make impassible, just really expensive
                            }
                        }
                        if(openList.contains(currentNode)) {
                            // Set gCost (10 for orthoginal, 14 for diagonal)
                            int newgCost = parent.getgCost() + ((deltaX == 0 || deltaY == 0) ? 10 : 14) + diagonalCost;
                            if(newgCost < currentNode.getgCost()) {
                                currentNode.parent = parent;
                                currentNode.setgCost(newgCost);
                                currentNode.setfCost(newgCost + manhattanDistance(currentNode, endNode));
                            }
                        }
                        else {
                            openList.add(currentNode);
                            currentNode.parent = parent;

                            // Set gCost (10 for orthoginal, 14 for diagonal)
                            currentNode.setgCost(parent.getgCost() + ((deltaX == 0 || deltaY == 0) ? 10 : 14) + diagonalCost);
                            currentNode.sethCost(manhattanDistance(currentNode, endNode));
                            currentNode.setfCost(currentNode.getgCost() + currentNode.gethCost());
                        }
                    }
                }
            }
        }

    }

    /**
     * Create a new Pathfinder object and initialize with a Room
     * @param room Room for path-finding.
     * @see com.droidquest.Room
     */
    public Pathfinder(Room room) {
        // Generate node array for room
        generate(room);

    }

    /**
     * Generate the Node list for a Room. Generally should use new Pathfinder(room) instead of this method
     * @param room Room for path-finding
     */
    public void generate(Room room) {
        for(int y = 0; y < room.RoomArray.length;y++) {
            nodeList.add(new ArrayList<Node>());
            for(int x=0;x<room.RoomArray[y].length;x++) {
                nodeList.get(y).add(new Node(x, y, Room.level.materialAt(x, y, room)));
            }
        }
    }

    // Use the Manhattan distance as the heuristic
    private int manhattanDistance(Node current, Node end) {
        return 10 * Math.abs(end.getX()-current.getX()) + Math.abs(end.getY()-current.getY());
    }

}
