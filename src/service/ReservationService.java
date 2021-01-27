package service;

import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    private ReservationService() {}
    public static ReservationService getReservationService() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    private final Set<IRoom> roomsSet = new HashSet<IRoom>();
    private final Set<Reservation> reservationsSet = new HashSet<Reservation>();

    public void addRoom(IRoom room) {
        roomsSet.add(room);
    }

    public IRoom getARoom(String roomId) throws Exception {
        IRoom result = null;
        for (IRoom room : roomsSet) {
            if (!room.getRoomNumber().equals(roomId)) {
                continue;
            }
            result = room;
        }

        if (result == null) {
            throw new Exception("Room not found");
        }

        return result;
    }

    public Collection<IRoom> getAllRooms() {
        return roomsSet;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("invalid dates, check in date must be before the check out date");
        }

        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationsSet.add(newReservation);
        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<IRoom>(roomsSet);

        for (Reservation reservation : reservationsSet) {
            IRoom reservationRoom = reservation.getRoom();

            if (reservation.getCheckInDate().before(checkInDate) && reservation.getCheckOutDate().after(checkInDate)) {
                availableRooms.remove(reservationRoom);
            }

            if (reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkOutDate)) {
                availableRooms.remove(reservationRoom);
            }

            if (reservation.getCheckInDate().after(checkInDate) && reservation.getCheckOutDate().before(checkOutDate)) {
                availableRooms.remove(reservationRoom);
            }
        }

        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservations(Customer customer) {
        List<Reservation> customersReservations = new ArrayList<Reservation>();
        for (Reservation reservation : reservationsSet) {
            if (!reservation.getCustomer().equals(customer)) {
                continue;
            }

            customersReservations.add(reservation);
        }

        return customersReservations;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservationsSet) {
            System.out.println(reservation);
        }
    }
}
