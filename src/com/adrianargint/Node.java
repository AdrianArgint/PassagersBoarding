package com.adrianargint;

/**
 *       Adrian Argint
 *          323CB
 */
public class Node implements Comparable<Node> {
    /**
     * family, group or single information
     */
    private Person data;
    /**
     * priority of the family, group or single
     */
    private int priority;
    /**
     * the number in "queue" for boarding
     */
    private int index;
    private Node rightNode = null;
    private Node leftNode = null;
    private Node parentNode = null;

    public Node(Person data, int priority, int index) {
        this.data = data;
        this.priority = priority;
        this.index = index;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @param data The information of the node is 'Person' type
     *             which means group name, priority, priority
     *             boarding, ticket type, age, special needs
     *             and an array with members of the group
     */
    public void setData(Person data) {
        this.data = data;
    }

    public Person getData() {
        return data;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public int getPriority() {
        return priority;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return the difference beetween the priority of the current node
     *          and the priority of the node as parameter
     *
     */
    @Override
    public int compareTo(Node node) {
        return this.priority - node.getPriority();
    }
}
