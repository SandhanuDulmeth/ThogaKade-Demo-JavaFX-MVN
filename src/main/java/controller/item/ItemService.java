package controller.item;

import model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAll();

    boolean saveCustomer(Item item);

    boolean updateCustomer(Item item);

    boolean dateleCustomer(String Id);

    Item searchCustomer(String Id);
}
