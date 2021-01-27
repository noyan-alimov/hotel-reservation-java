package client;

import api.AdminResource;
import model.*;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    private static Admin admin;
    private Admin() {}
    public static Admin getAdmin() {
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }

    public void seeAllCustomers(AdminResource adminResource) {
        Collection<Customer> customers = adminResource.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void seeAllRooms(AdminResource adminResource) {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        for (IRoom room : rooms) {
            System.out.println(room);
        }
    }

    public void seeAllReservations(AdminResource adminResource) {
        adminResource.displayAllReservations();
    }

    public void addARoom(Scanner scanner, AdminResource adminResource) {
        System.out.println("Enter room number:");
        String roomNumber = scanner.nextLine();

        System.out.println("Price:");
        String price = scanner.nextLine();
        Double roomPrice = Double.parseDouble(price);

        System.out.println("Room type:");
        System.out.println("1 for SINGLE");
        System.out.println("2 for DOUBLE");
        String roomType = scanner.nextLine();
        System.out.println(roomType);

        RoomType type;
        if (roomType.equals("1")) {
            type = RoomType.SINGLE;
        } else if (roomType.equals("2")) {
            type = RoomType.DOUBLE;
        } else {
            System.out.println("Please enter 1 or 2");
            return;
        }

        IRoom newRoom = new FreeRoom(roomNumber, roomPrice, type);
        adminResource.addRoom(newRoom);
        System.out.println("Room successfully created");
    }
}
