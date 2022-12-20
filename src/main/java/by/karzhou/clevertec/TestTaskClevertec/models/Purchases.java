package by.karzhou.clevertec.TestTaskClevertec.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Purchases")
public class Purchases{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "check_id", referencedColumnName = "id")
    private MarketCheck checkId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;


    public Purchases(){

    }

    public Purchases(int amount, MarketCheck checkId, Product productId) {
        this.amount = amount;
        this.checkId = checkId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public MarketCheck getCheckId() {
        return checkId;
    }

    public void setCheckId(MarketCheck checkId) {
        this.checkId = checkId;
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
