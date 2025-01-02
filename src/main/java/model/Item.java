package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    String itemCode;
    String description;
    String packSize;
    Double unitPrice;
    Integer qtyOnHand;

}
