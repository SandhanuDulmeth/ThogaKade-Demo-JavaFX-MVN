package controller.placeOrder;

import model.Customer;
import model.Item;

import java.util.List;

public interface PlaceOrderService {
    List<Item> getAllItem();
    List<Customer> getAllCustomer();
}
