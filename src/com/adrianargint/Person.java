package com.adrianargint;

import java.util.ArrayList;

/**
 *      Adrian Argint
 *          323CB
 */
public class Person {
    private String groupName;
    private ArrayList<HumanBeing> groupMembers;
    private Ticket ticket;

    public Person(String ticketType, boolean priorityBoarding, String groupName) {
        this.groupName = groupName;
        this.groupMembers = new ArrayList<>();
        this.ticket = new Ticket(ticketType, priorityBoarding);
    }

    public String getGroupName() {
        return this.groupName;
    }

    public ArrayList<HumanBeing> getGroupMembers() {
        return this.groupMembers;
    }
    /**
     * @param name name of the person searched
     * @return  the person information(name, age, specialNeeds)
     */
    public HumanBeing hasHumanBeing(String name){
        for(HumanBeing hb : groupMembers){
            if(hb.getName().compareTo(name) == 0){
                return hb;
            }
        }
        return null;
    }


    public void add(HumanBeing hb){
        groupMembers.add(hb);
    }

    /**
     * @return priority of the group
     */
    public int getPriority(){
        int priority = 0;
        if (groupName.charAt(0) == 'f') priority = 10;
        if (groupName.charAt(0) == 'g') priority = 5;
        if(this.ticket.isPriority_boarding())
            priority += 30 * groupMembers.size();

        if(this.ticket.getTicket_type().compareTo("p") == 0) priority += 20 * groupMembers.size();
        if(this.ticket.getTicket_type().compareTo("b") == 0) priority += 35 * groupMembers.size();

        for(HumanBeing hb : groupMembers){
            if(hb.isSpecialNeeds()) priority += 100;

            if(hb.getAge() < 2) {
                priority += 20;
            } else if(hb.getAge() < 5) {
                priority += 10;
            } else if(hb.getAge() < 10) {
                priority += 5;
            }
            if(hb.getAge() >= 60) {
                priority += 15;
            }
        }

        return priority;
    }
}
