package com.mavericapps.hotelms.service;

import com.mavericapps.hotelms.common.RoomType;
import com.mavericapps.hotelms.domain.*;
import com.mavericapps.hotelms.exceptions.*;

public interface IHotelService {

    Guest findGuestCheckedInRoom(long hotelId, int roomId) throws InvalidHotelIdException, InvalidRoomNoException, HotelNotFoundException, RoomNotFoundException, GuestNotFoundException;

    int guestsCount(long hotelId) throws InvalidHotelIdException, HotelNotFoundException;

    double earningsToday(long hotelId) throws InvalidHotelIdException, HotelNotFoundException;

    Guest[] findAllGuests(long hotelId) throws InvalidHotelIdException, HotelNotFoundException;

    Hotel addHotel(String hotelName) throws InvalidHotelNameException;

    void addRoom(long hotelId, int floor, RoomType roomType) throws InvalidHotelIdException, HotelNotFoundException, CannotAddRoomException;

    Guest guestCheckIn(long hotelId, int roomNo, String firstName, String lastName) throws InvalidHotelIdException, InvalidRoomNoException, HotelNotFoundException, RoomNotFoundException;

    void guestCheckOut(long hotelId, long guestId) throws InvalidHotelIdException, HotelNotFoundException, InvalidGuestIdException, GuestNotFoundException;

}
