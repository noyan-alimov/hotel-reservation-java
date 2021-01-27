package client;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class Client {
    private static Client client;
    private Client() {}
    public static Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void reserveARoom(Scanner scanner, HotelResource hotelResource) {
        System.out.println("Please enter your email");
        String email = scanner.nextLine();

        System.out.println("Please enter a check in date and check out date in the following format: day/month/year");
        System.out.println("Example: 30/12/2021");

        try {
            System.out.println("Check in date:");
            String checkInDateStr = scanner.nextLine();
            Date checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInDateStr);

            System.out.println("Check out date:");
            String checkOutDateStr = scanner.nextLine();
            Date checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDateStr);
            Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);

            if (availableRooms.isEmpty()) {
                System.out.println("Sorry, no rooms available, please choose different dates");
                return;
            }

            for (IRoom room : availableRooms) {
                System.out.println(room);
            }

            System.out.println("Please enter a room number you would like to reserve");
            String roomNumber = scanner.nextLine();

            IRoom roomToReserve = null;

            for (IRoom room : availableRooms) {
                if (room.getRoomNumber().equals(roomNumber)) {
                    roomToReserve = room;
                }
            }

            if (roomToReserve == null) {
                System.out.println("Please enter a valid room number");
                return;
            }

            Reservation reservation = hotelResource.bookARoom(email, roomToReserve, checkInDate, checkOutDate);
            System.out.println(reservation);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createAccount(Scanner scanner, HotelResource hotelResource) {
        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();

        System.out.println("Last name:");
        String lastName = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();

        hotelResource.createACustomer(firstName, lastName, email);
        System.out.println("Account successfully created");
    }

    public void seeCustomersReservations(Scanner scanner, HotelResource hotelResource) {
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        try {
            Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
