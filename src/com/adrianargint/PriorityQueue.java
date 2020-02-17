package com.adrianargint;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *       Adrian Argint
 *          323CB
 */
public class PriorityQueue {
    private Node head = null;
    private int size = 0;
    private ArrayList<Person> people = new ArrayList<>();

    public PriorityQueue() {
    }

    public Person newFamily(String ticket_type, String priority_boarding, String groupName) {
        Person g = new Person(ticket_type, Boolean.parseBoolean(priority_boarding), groupName);
        this.people.add(g);
        return g;
    }

    public Person newGroup(String ticket_type, String priority_boarding, String groupName) {
        return newFamily(ticket_type, priority_boarding, groupName);
    }


    public Person newSolo(String ticket_type, String priority_boarding, String groupName) {
        return newFamily(ticket_type, priority_boarding, groupName);
    }

    /**
     *
     * @param name name of HumanBeing to be checked
     *
     * @return the "group" of type Person if it <br>
     *          exists in the group named as the parameter <br>
     *          or null otherwise
     */
    public Person contains(String name) {
        for (Person g : this.people) {
            if (g.getGroupName().compareTo(name) == 0)
                return g;
        }
        return null;
    }

    /**
     * @param p the family/group/single that is inserted
     * @param priority its priority
     */
    public void insert(Person p, int priority) {

        if (head == null) {
            Node newNode = new Node(p, priority, ++size);
            head = newNode;
            return;
        }
        insert_newNode(head, p, priority, size + 1);

    }

    /**
     * private method so don't worry about it!
     * also, it is auxiliary method so it matches the damands :)
     * @param node the node created by the method above
     * @param data information about the family/group/single that will be added in the heap
     * @param priority  data's priority
     * @param newIndex  Number on the "queue"
     */
    private void insert_newNode(Node node, Person data, int priority, int newIndex) {
        if (node == null) return;
        if (node.getIndex() == parent(newIndex)) {
            Node newNode = new Node(data, priority, ++size);
            if (node.getLeftNode() == null) {
                node.setLeftNode(newNode);
                newNode.setParentNode(node);
            } else {
                node.setRightNode(newNode);
                newNode.setParentNode(node);
            }
            repare_heap(newNode);
            return;
        }

        insert_newNode(node.getLeftNode(), data, priority, newIndex);
        insert_newNode(node.getRightNode(), data, priority, newIndex);
    }

    //After inserting, check if it is in the right position
    //if not, get it upper

    /**
     * After inserting, check if it is in the right position<br>
     * if not, get it upper
     * @param node the starting node
     */
    private void repare_heap(Node node) {
        while (node.getParentNode() != null) {
            if (node.compareTo(node.getParentNode()) > 0) {
                swap(node, node.getParentNode());
                node = node.getParentNode();
            }else
                break;
        }
    }

    /**
     * swap the information in 2 nodes
     * @param node1 First node
     * @param node2 Second node
     */
    public void swap(Node node1, Node node2){
        Person auxPerson = node2.getData();
        node2.setData(node1.getData());
        node1.setData(auxPerson);
        node1.setPriority(node1.getData().getPriority());
        node2.setPriority(node2.getData().getPriority());
    }


    /**
     * @throws IOException Throws exceptions catched by reading/writing from/in files
     */
    public void list() throws IOException {
        FileWriter output = new FileWriter(new File("queue.out"), true);
        print_maxHeap(head, output);
        output.close();

    }

    /**
     * auiliary method not to have parameters at the previous one :)
     * @param node it will be the head of the node as it will print preorder
     * @param file  file to be written in
     * @throws IOException  we do not like exceptions :)
     */
    private void print_maxHeap(Node node, FileWriter file) throws IOException {
        if (node == null) return;
        if(node.getIndex() == 1)
            file.write(node.getData().getGroupName());
        else
            file.write(" " + node.getData().getGroupName());
        print_maxHeap(node.getLeftNode(), file);
        print_maxHeap(node.getRightNode(), file);
    }

    public void embark(){
        if(this.head == null) return;
        if(this.size == 1) {
            this.head = null;
            size--;
            return;
        }
        embark_help(this.head);
        heapify(this.head);
        size--;

    }

    private void embark_help(Node node){
        if(node == null) return;

        if(node.getIndex() == this.size) {
            try{
                if (node == node.getParentNode().getRightNode()) {
                    node.getParentNode().setRightNode(null);
                } else if (node == node.getParentNode().getLeftNode()) {
                    node.getParentNode().setLeftNode(null);
                }
            }
            catch(Exception e){}
            node.setParentNode(null);
            swap(node, this.head);
        }

        embark_help(node.getLeftNode());
        embark_help(node.getRightNode());


    }


    /**
     *
     *  after deleting 1 node in tree the maxHeap is not respected<br>
     *      so we have do restore the order
     * @param node  node to start with
     */
    private void heapify(Node node) {
        if (node.getLeftNode() == null)
            return;

        if(node.getRightNode() != null) {
            if (node.compareTo(node.getLeftNode()) < 0 || node.compareTo(node.getRightNode()) < 0) {
                if (node.getLeftNode().compareTo(node.getRightNode()) >= 0) {
                    swap(node, node.getLeftNode());
                    heapify(node.getLeftNode());
                } else {
                    swap(node, node.getRightNode());
                    heapify(node.getRightNode());
                }
            }
        }
        else{
            if(node.compareTo(node.getLeftNode()) < 0){
                swap(node,node.getLeftNode());
            }
        }
    }

    private Node findNode(Person p, Node node){
        if(node == null) return null;

        if(node.getData().getGroupName().compareTo(p.getGroupName()) == 0){
            return node;
        }

        return findNode(p, node.getRightNode()) == null ? findNode(p, node.getLeftNode()):  null;
    }

    public void delete(Person p){
        Node nodeP = findNode(p, this.head);
        this.people.remove(p);
        swap(this.head, nodeP);
        embark();
    }

    public void delete(Person p, String name){
        p.getGroupMembers().remove(p.hasHumanBeing(name));
        Node node = findNode(p, this.head);
        node.setPriority(node.getData().getPriority());
        heapify(node);
    }
    private int parent(int index) {
        return index / 2;
    }
}
