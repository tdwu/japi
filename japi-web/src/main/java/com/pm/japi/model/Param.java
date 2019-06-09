package com.pm.japi.model;


import com.pm.japi.annotations.From;

import java.util.ArrayList;
import java.util.List;

public class Param {

    private String name;
    private String title;
    private String note;
    private String type;
    private From from;
    private List<Param> properties=new ArrayList<Param>();

    public Param() {
    }
    public Param(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public List<Param> getProperties() {
        return properties;
    }

    public void setProperties(List<Param> properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
