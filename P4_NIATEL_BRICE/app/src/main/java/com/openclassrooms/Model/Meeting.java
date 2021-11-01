package com.openclassrooms.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Meeting implements Serializable {


    /**
     * Id of meeting
     */

    private int id;

    /**
     * Subject of meeting
     */
    private String subject;

    /**
     * Hour of meeting
     */
    private String hour;

    /**
     * Location of meeting
     */
    private String location;

    /**
     * List of people
     */
    private String listPeople;

    /**
     * Constructor
     *
     * @param subject
     * @param hour
     * @param location
     * @param listPeople
     * @param id
     */

    public Meeting(int id, String location, String hour, String subject, String listPeople) {
        this.id = id;
        this.location = location;
        this.hour = hour;
        this.subject = subject;
        this.listPeople = listPeople;
    }

    /**
     * Getters and Setters
     */
    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getListPeople() {
        return listPeople;
    }

    public void setListPeople(String listPeople) {
        this.listPeople = listPeople;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting neighbour = (Meeting) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
