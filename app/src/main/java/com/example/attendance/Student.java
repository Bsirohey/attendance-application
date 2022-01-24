package com.example.attendance;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private boolean selected;


    public Student(String name) {
        super();
        this.name = name;
        selected = false;
    }


    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void changeSelected() {
        this.selected = !selected;
    }

    public void setSelected(boolean value)
    {
        this.selected = value;
    }

}
