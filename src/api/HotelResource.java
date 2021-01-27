package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private HotelResource() {
        this.customerService = CustomerService.getCustomerService();
        this.reservationService = ReservationService.getReservationService();
    }
    public static HotelResource getHotelResource() {
        if (hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) throws Exception {
        try {
            return customerService.getCustomer(email);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void createACustomer(String firstName, String lastName, String email) {
        customerService.addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber) throws Exception {
        try {
            return reservationService.getARoom(roomNumber);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        Customer customer = this.getCustomer(customerEmail);
        try {
            return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) throws Exception {
        if (!customerService.doesCustomerExist(customerEmail)) {
            throw new Exception("Customer does not exist");
        }

        Customer customer = this.getCustomer(customerEmail);
        return reservationService.getCustomersReservations(customer);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }
}
