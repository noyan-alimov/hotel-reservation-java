import api.AdminResource;
import api.HotelResource;
import client.Admin;
import client.Client;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu.app(scanner);
    }

    public static void app(Scanner scanner) {
        System.out.println(
                "\nHotel Reservation App\n" +
                        "Please enter one of the following options:\n" +
                        "1. Find and reserve a room\n" +
                        "2. See my reservations\n" +
                        "3. Create an account\n" +
                        "4. Admin\n" +
                        "5. Exit\n"
        );
        String userInput = scanner.nextLine();
        HotelResource hotelResource = HotelResource.getHotelResource();
        Client client = Client.getClient();

        switch (userInput) {
            case "1":
                client.reserveARoom(scanner, hotelResource);
                MainMenu.app(scanner);
                break;
            case "2":
                client.seeCustomersReservations(scanner, hotelResource);
                MainMenu.app(scanner);
                break;
            case "3":
                client.createAccount(scanner, hotelResource);
                MainMenu.app(scanner);
                break;
            case "4":
                MainMenu.admin(scanner);
                break;
            case "5":
                scanner.close();
                break;
            default:
                System.out.println("Please enter a valid input");
                MainMenu.app(scanner);
        }
    }

    public static void admin(Scanner scanner) {
        System.out.println(
                "\nAdmin\n" +
                        "Please enter one of the following options:\n" +
                        "1. See all customers\n" +
                        "2. See all rooms\n" +
                        "3. See all reservations\n" +
                        "4. Add a room\n" +
                        "5. Back to Main Menu\n"
        );
        String userInput = scanner.nextLine();
        AdminResource adminResource = AdminResource.getAdminResource();
        Admin admin = Admin.getAdmin();

        switch (userInput) {
            case "1":
                admin.seeAllCustomers(adminResource);
                MainMenu.admin(scanner);
                break;
            case "2":
                admin.seeAllRooms(adminResource);
                MainMenu.admin(scanner);
                break;
            case "3":
                admin.seeAllReservations(adminResource);
                MainMenu.admin(scanner);
                break;
            case "4":
                admin.addARoom(scanner, adminResource);
                MainMenu.admin(scanner);
                break;
            case "5":
                MainMenu.app(scanner);
                break;
            default:
                System.out.println("Please enter a valid input");
                MainMenu.admin(scanner);
        }
    }
}
