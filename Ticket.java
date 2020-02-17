package com.adrianargint;
/**
 *       Adrian Argint
 *          323CB
 */
public class Ticket {

    private String ticket_type;
    private boolean priority_boarding;

    public Ticket(String ticket_type, boolean priority_boarding) {
        this.ticket_type = ticket_type;
        this.priority_boarding = priority_boarding;
    }

    /**
     * returns the ticket type
     * @return String
     */
    public String getTicket_type() {
        return this.ticket_type;
    }

    public boolean isPriority_boarding() {
        return this.priority_boarding;
    }
}