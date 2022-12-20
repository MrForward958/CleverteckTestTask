package by.karzhou.clevertec.TestTaskClevertec.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Нужно задать имя товара.")
    @Size(min = 2, max = 100, message = "Имя товара должно быть в диапазоне от 2 до 100 символов.")
    private String name;

    @Column(name = "price")
    @Min(value = 1, message = "Минимальная цена товара должна быть не меньше 1 рубля")
    @Max(value = 1000000, message = "Максимальная цена не должна превышать 1000000 рублей")
    private double price;

    @OneToMany(mappedBy = "productId")
    private List<Purchases> purchasesList;


    public Product() {

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, List<Purchases> purchasesList) {
        this.name = name;
        this.price = price;
        this.purchasesList = purchasesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Purchases> getPurchasesList() {
        return purchasesList;
    }

    public void setPurchasesList(List<Purchases> purchasesList) {
        this.purchasesList = purchasesList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
