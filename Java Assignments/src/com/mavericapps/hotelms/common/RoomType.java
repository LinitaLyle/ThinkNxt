package com.mavericapps.hotelms.common;

public enum RoomType {
    Standard(5000),
    Deluxe(3500);
    private final double pricePerDay;
    RoomType(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
}
