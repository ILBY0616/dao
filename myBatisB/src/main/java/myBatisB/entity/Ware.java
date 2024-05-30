package myBatisB.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ware {
    private int id;
    private String name;
    private String brand;
    private String category;
    private double price;
    private int stock;
    private String picAddress;
}
