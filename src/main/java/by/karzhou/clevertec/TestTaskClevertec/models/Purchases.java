package by.karzhou.clevertec.TestTaskClevertec.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "Purchases")
public class Purchases{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    @Min(value = 1,message = "Количество товара должно быть не меньше 1 ед.")
    @Max(value = 100, message = "Количество товара не может превышать 100 ед.")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "check_id", referencedColumnName = "id")
    private MarketCheck marketCheck;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;


    public Purchases(){

    }

    public Purchases(int amount, MarketCheck marketCheck, Product productId) {
        this.amount = amount;
        this.marketCheck = marketCheck;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public MarketCheck getMarketCheck() {
        return marketCheck;
    }

    public void setMarketCheck(MarketCheck marketCheck) {
        this.marketCheck = marketCheck;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
