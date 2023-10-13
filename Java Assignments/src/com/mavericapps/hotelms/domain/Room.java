package com.mavericapps.hotelms.domain;

import com.mavericapps.hotelms.common.RoomType;

public class Room {
    private final int roomNumber;
    private final int floor;
    private Guest checkedIn;
    private final RoomType roomType;

    public Room(int roomNumber, int floor, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Guest getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Guest checkedIn) {
        this.checkedIn = checkedIn;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", floor=" + floor +
                ", checkedIn=" + checkedIn +
                ", roomType='" + roomType + '\''+
                '}' + '\n';
    }
}
