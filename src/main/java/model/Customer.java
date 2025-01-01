package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String CustID;
    private String CustTitle;
    private String CustName;
    private Date DOB;
    private Double salary;
    private String CustAddress;
    private String City;
    private String Province ;
    private String PostalCode;
}
