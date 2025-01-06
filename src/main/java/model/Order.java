package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    String itemCode;
    String description;
    Integer qtyOnHand;
    Double unitPrice;
    Double total;

}
