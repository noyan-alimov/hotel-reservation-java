package model;

import java.util.Objects;

public class Room implements IRoom {
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType enumeration;
    private Boolean isFree;

    public Room(String roomNumber, Double roomPrice, RoomType enumeration, Boolean isFree) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.enumeration = enumeration;
        this.isFree = isFree;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", enumeration=" + enumeration +
                ", isFree=" + isFree +
                '}';
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public Boolean getIsFree() {
        return this.isFree;
    }

    @Override
    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) && Objects.equals(roomPrice, room.roomPrice) && enumeration == room.enumeration && Objects.equals(isFree, room.isFree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, roomPrice, enumeration, isFree);
    }
}
