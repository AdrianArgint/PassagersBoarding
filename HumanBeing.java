package com.adrianargint;

/**
 *       Adrian Argint
 *          323CB
 */
public class HumanBeing {
    private String name;
    private int age;
    private boolean specialNeeds;

    public HumanBeing(String name, int age, boolean specialNeeds) {
        this.name = name;
        this.age = age;
        this.specialNeeds = specialNeeds;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isSpecialNeeds() {
        return this.specialNeeds;
    }
}
