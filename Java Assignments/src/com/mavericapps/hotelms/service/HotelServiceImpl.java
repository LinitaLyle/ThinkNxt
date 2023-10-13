package com.mavericapps.hotelms.service;

import com.mavericapps.hotelms.common.RoomType;
import com.mavericapps.hotelms.domain.*;
import com.mavericapps.hotelms.exceptions.*;

public class HotelServiceImpl implements IHotelService {
    Hotel[] hotels = new Hotel[5];
    int hotelIndex;
    private long generatedHotelId;
    private int generatedRoomNo;
    private long generatedGuestId = 1000;
    public Hotel[] getHotels() {
        return hotels;
    }

    @Override
    public Guest findGuestCheckedInRoom(long hotelId, int roomNo) throws InvalidHotelIdException, InvalidRoomNoException, HotelNotFoundException, RoomNotFoundException, GuestNotFoundException {
        validateHotelId(hotelId);
        validateRoomNo(roomNo);
        Hotel hotel = findHotel(hotelId);
        Room room = findRoom(hotel, roomNo);
        Guest guestInRoom = room.getCheckedIn();
        if(guestInRoom == null)
            throw new GuestNotFoundException("There is no guest in room "+roomNo);
        return guestInRoom;
    }

    @Override
    public int guestsCount(long hotelId) throws InvalidHotelIdException, HotelNotFoundException {
        validateHotelId(hotelId);
        Hotel hotel = findHotel(hotelId);
        if (hotel == null)
            throw new HotelNotFoundException("Hotel with id " + hotelId + " not found");
        int count = 0;
        for (Room room : hotel.getRooms())
            if (room.getCheckedIn() != null)
                count++;

        return count;
    }

    @Override
    public double earningsToday(long hotelId) throws InvalidHotelIdException, HotelNotFoundException {
        validateHotelId(hotelId);
        Hotel hotel = findHotel(hotelId);
        if (hotel == null)
            throw new HotelNotFoundException("Hotel with id " + hotelId + " not found");
        double totalEarnings=0;
        for(Room room:hotel.getRooms())
        {
            if(room.getCheckedIn()!=null)
                totalEarnings+=room.getRoomType().getPricePerDay();
        }
        return totalEarnings;
    }

    @Override
    public Guest[] findAllGuests(long hotelId) throws InvalidHotelIdException, HotelNotFoundException {
        validateHotelId(hotelId);
        Guest[] allGuests = new Guest[8];
        int index = 0;
        Hotel hotel = findHotel(hotelId);
        if (hotel == null) throw new HotelNotFoundException("Hotel with id " + hotelId + " not found");
        for (Room room : hotel.getRooms()) {
            Guest guestInRoom = room.getCheckedIn();
            if (guestInRoom != null) allGuests[index++] = guestInRoom;
        }
        return allGuests;
    }

    @Override
    public Hotel addHotel(String hotelName) throws InvalidHotelNameException {
        if (hotelName == null) throw new InvalidHotelNameException("Hotel name cannot be empty!");
        long hotelId = generateHotelId();
        Hotel newHotel = new Hotel(hotelId, hotelName);
        hotels[hotelIndex++] = newHotel;
        return newHotel;
    }

    @Override
    public void addRoom(long hotelId, int floor, RoomType roomType) throws InvalidHotelIdException, HotelNotFoundException, CannotAddRoomException {
        validateHotelId(hotelId);
        Hotel hotel = findHotel(hotelId);

        int roomNumber = getGeneratedRoomNo();
        Room room = new Room(roomNumber, floor, roomType);

        Room[] rooms = hotel.getRooms();
        if (rooms.length > 8)//maximum number of rooms in hotel
            throw new CannotAddRoomException("Cannot add more rooms to hotel");

        for (int i = 0; i < rooms.length; i++)
            if (rooms[i] == null) {
                rooms[i] = room;
                break;
            }
    }

    @Override
    public Guest guestCheckIn(long hotelId, int roomNo, String firstName, String lastName) throws InvalidHotelIdException, InvalidRoomNoException, HotelNotFoundException, RoomNotFoundException {
        validateHotelId(hotelId);
        validateRoomNo(roomNo);
        Hotel hotel = findHotel(hotelId);
        long newGuestId = getGeneratedGuestId();
        Guest newGuest = new Guest(newGuestId, firstName, lastName);
        Room roomtoCheckIn = findRoom(hotel, roomNo);
        roomtoCheckIn.setCheckedIn(newGuest);
        return newGuest;
    }

    @Override
    public void guestCheckOut(long hotelId, long guestId) throws InvalidHotelIdException, HotelNotFoundException, InvalidGuestIdException, GuestNotFoundException {
        validateHotelId(hotelId);
        validateGuestId(guestId);
        Hotel hotel = findHotel(hotelId);
        if (hotel == null) throw new HotelNotFoundException("Hotel with id " + hotelId + " does not exist!!");
        Guest guest = findGuest(hotel,guestId);
        if(guest == null) throw new GuestNotFoundException("Guest "+guestId+" not found in hotel");

        for(Room room:hotel.getRooms())
        {
            if(room.getCheckedIn()==null)
                continue;
            if(room.getCheckedIn().getId()==guestId)
                room.setCheckedIn(null);
        }
    }

    public long generateHotelId() {
        return ++generatedHotelId;
    }

    public int getGeneratedRoomNo() {
        return ++generatedRoomNo;
    }

    public long getGeneratedGuestId() {
        return ++generatedGuestId;
    }

    public void validateHotelId(long hotelId) throws InvalidHotelIdException {
        if (hotelId <= 0) throw new InvalidHotelIdException("Hotel Id "+hotelId+" is invalid");
    }

    public void validateRoomNo(int roomNumber) throws InvalidRoomNoException {
        if (roomNumber <= 0) throw new InvalidRoomNoException("Room number "+roomNumber+" is invalid");
    }

    public void validateGuestId(long guestId)throws InvalidGuestIdException {
        if(guestId<=0) throw new InvalidGuestIdException("Guest id "+guestId+" is invalid");
    }

    public Hotel findHotel(long hotelId) throws HotelNotFoundException {
        for (Hotel hotel : hotels) {
            if (hotel == null) continue;
            if (hotel.getId() == hotelId) return hotel;
        }
        throw new HotelNotFoundException("Hotel with id "+hotelId+" does not exist");
    }

    public Room findRoom(Hotel hotel, int roomNo) throws RoomNotFoundException {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNo) return room;
        }
        throw new RoomNotFoundException("Room "+roomNo+" not found");
    }

    public Guest findGuest(Hotel hotel, long guestId) throws GuestNotFoundException {
        for(Room room:hotel.getRooms())
        {
            Guest guest = room.getCheckedIn();
            if(guest==null)
                continue;
            if(guest.getId()==guestId)
                return guest;
        }
        throw new GuestNotFoundException("Guest with id "+guestId+" not found");
    }
}

