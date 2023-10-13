package com.mavericapps.hotelms.domain;

import java.util.Arrays;

public class Hotel {
    private final long id;
    private final String name;
    private Room[] rooms = new Room[8];

    public Hotel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Room[] getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + Arrays.toString(rooms) +
                '}';
    }


}
