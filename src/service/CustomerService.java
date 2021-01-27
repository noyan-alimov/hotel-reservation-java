package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService customerService;
    private CustomerService() {}
    public static CustomerService getCustomerService() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    private final Map<String, Customer> customersMap = new HashMap<String, Customer>();
    private final List<Customer> customersList = new ArrayList<Customer>();

    public void addCustomer(String firstName, String lastName, String email) {
        Customer newCustomer = new Customer(firstName, lastName, email);
        customersMap.put(newCustomer.getEmail(), newCustomer);
        customersList.add(newCustomer);
    }

    public Customer getCustomer(String customerEmail) throws Exception {
        Customer customer = customersMap.get(customerEmail);
        if (customer == null) {
            throw new Exception("Customer not found");
        }

        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return customersList;
    }

    public Boolean doesCustomerExist(String email) {
        Customer customer = customersMap.get(email);
        return customer != null;
    }
}
