package com.mavericapps.hotelms.ui;

import com.mavericapps.hotelms.common.RoomType;
import com.mavericapps.hotelms.domain.*;
import com.mavericapps.hotelms.exceptions.*;
import com.mavericapps.hotelms.service.HotelServiceImpl;

import java.sql.SQLOutput;
import java.util.Arrays;

public class AppClient {
    private final HotelServiceImpl hotelService = new HotelServiceImpl();

    public static void main(String[] args) {

        AppClient app = new AppClient();
        app.runApp();
    }

    public void runApp() {

        //Add hotel feature
        System.out.println("\n******** Add Hotels ********");
        addHotel("Tea County");
        addHotel("Radisson");
        displayAllHotels();

        //Add room feature
        System.out.println("\n******** Add Rooms to Hotels ********");
        addRoom(1, 1, RoomType.Standard);
        addRoom(1, 1, RoomType.Deluxe);
        addRoom(1, 1, RoomType.Standard);
        addRoom(1, 1, RoomType.Standard);
        addRoom(1, 2, RoomType.Deluxe);
        addRoom(1, 2, RoomType.Standard);
        addRoom(1, 2, RoomType.Standard);
        addRoom(1, 2, RoomType.Standard);
        addRoom(3,2,RoomType.Standard);//Hotel not found
        addRoom(0,2,RoomType.Standard);//Invalid hotelId
        addRoom(1,2,RoomType.Deluxe);//Cannot add more rooms
        System.out.println("\n****** After adding rooms ******");
        displayAllHotels();

        //Guest checkin feature
        System.out.println("\n******** Check In Guests ********");
        checkInGuest(1, 2, "Sarah", "McCall");
        checkInGuest(1, 3, "Elena", "Jones");
        checkInGuest(1, 5, "Caroline", "Green");
        checkInGuest(1, 8, "Rachel", "Adams");
        checkInGuest(1, 6, "Selena", "Washer");
        checkInGuest(0, 1, "Selena", "Washer");//Invalid hotel id exception
        checkInGuest(9, 2, "Selena", "Washer");//Hotel not found exception
        checkInGuest(1, 12, "Selena", "Washer");//
        System.out.println("\n***** After adding guests ******");
        displayAllHotels();

        //find all guests feature;
        System.out.println("\n******** All Guests in Hotel ********");
        displayAllGuests(1);
        displayAllGuests(5);//Hotel not found exception
        displayAllGuests(-1);// invalid hotel id exception

        //Find guest in room feature
        System.out.println("\n******** Find guest in room ********");
        findGuestInRoom(1, 6);

        //Total number of guests feature
        System.out.println("\n******* Total Number of Guests ********");
        totalGuests(1);

        //Earnings today feature
        System.out.println("\n******** Total Earnings today ********");
        totalEarnings(1);

        //Guest Checkout feature
        System.out.println("\n******** Check out a guest ********");
        checkOutGuest(1, 1004);
        checkOutGuest(3,1001);//Hotel not found exception
        checkOutGuest(1, 1010);//Guest id not found exception
        System.out.println("Guest list after checkout:");
        displayAllGuests(1);

    }

    private void totalEarnings(long hotelId) {
     try {
           double totalEarnings = hotelService.earningsToday(hotelId);
            System.out.println("The total earnings for the hotel today is Rs " + totalEarnings);
        } catch (InvalidHotelIdException e) {
            System.out.println("e.getMessage()");
        } catch (HotelNotFoundException e) {
            System.out.println("e.getMessage()");
        }
    }

    public void addHotel(String hotelName) {
        try {
            Hotel newHotel = hotelService.addHotel(hotelName);
        } catch (InvalidHotelNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRoom(long hotelId, int floor, RoomType roomType) {
        try {
            hotelService.addRoom(hotelId, floor, roomType);
            System.out.println("Room added to hotel!");
        } catch (InvalidHotelIdException e) {
            System.out.println(e.getMessage());
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (CannotAddRoomException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkInGuest(long hotelId, int roomNumber, String firstName, String lastName) {
        try {
            hotelService.guestCheckIn(hotelId, roomNumber, firstName, lastName);
            System.out.println(firstName+" "+lastName+" checked into hotel");
        } catch (InvalidHotelIdException e) {
            System.out.println(e.getMessage());
        } catch (InvalidRoomNoException e) {
            System.out.println(e.getMessage());
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public void displayAllGuests(long hotelId) {
        Guest[] allGuests = new Guest[8];
        int index = 0;
        try {
            for (Guest guest : hotelService.findAllGuests(hotelId)) {
                if (guest != null) System.out.println(guest);
            }
        } catch (InvalidHotelIdException e) {
            System.out.println(e.getMessage());
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findGuestInRoom(long hotelId, int roomNo) {

        try {
            Guest guestInRoom = hotelService.findGuestCheckedInRoom(hotelId, roomNo);
            System.out.println("The guest in the room is " + guestInRoom.getFirstName() + " " + guestInRoom.getLastName());
        } catch (InvalidHotelIdException e) {
            System.out.println(e.getMessage());
        } catch (InvalidRoomNoException e) {
            System.out.println(e.getMessage());
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (GuestNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void totalGuests(long hotelId) {

        try {
            int count = hotelService.guestsCount(hotelId);
            if(count ==0)
                System.out.println("There are no guests in the hotel");
            else
                System.out.println("The total number of guests in the hotel is " + count);
        } catch (InvalidHotelIdException e) {
            System.out.println(e.getMessage());
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkOutGuest(long hotelId, long guestId) {
        try {
            hotelService.guestCheckOut(hotelId, guestId);
            System.out.println("Guest "+guestId+ " checked out...");
        } catch (InvalidHotelIdException e) {
            System.out.println(e.getMessage());
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidGuestIdException e) {
            System.out.println(e.getMessage());
        } catch (GuestNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    void displayAllHotels()
    {
        System.out.println("Details of all hotels...\n"+Arrays.toString(hotelService.getHotels()));
    }

}
