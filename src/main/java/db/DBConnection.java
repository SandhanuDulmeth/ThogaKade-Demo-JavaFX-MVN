package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static DBConnection INSTANCE;

private ArrayList<Customer> customerArrayList;
    private DBConnection(){
    customerArrayList=new ArrayList<>();
    }

    public static DBConnection getINSTANCE() {
        if (INSTANCE==null){
            return INSTANCE=new DBConnection();
        }
        return INSTANCE;
    }
    public List<Customer> getConnection(){
        return customerArrayList;
    }
}
