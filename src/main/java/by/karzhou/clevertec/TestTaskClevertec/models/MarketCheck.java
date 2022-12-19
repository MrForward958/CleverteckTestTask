package by.karzhou.clevertec.TestTaskClevertec.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Market_check")
public class MarketCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Max(message = "Номер карты не должен быть больше 100000", value = 100000)
    @Min(message = "Номер карты должен быть больше 0", value = 1)
    @Column(name = "card_number")
    private int cardNumber;

    @NotEmpty(message = "Название магазина не может быть пустым")
    @Column(name = "market_name")
    @Size(min = 2, max = 50, message = "Название магазина должно быть в диапазоне от 2 до 50 символов")
    private String marketName;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "open_status")
    private Boolean openStatus;


    @OneToMany(mappedBy = "marketCheck")
    private List<Product> products;

    public MarketCheck(){
    }

    public MarketCheck(int cardNumber, String marketName, Date createDate, Boolean openStatus, List<Product> products) {
        this.cardNumber = cardNumber;
        this.marketName = marketName;
        this.createDate = createDate;
        this.openStatus = openStatus;
        this.products = products;
    }

    public MarketCheck(int cardNumber, String marketName, Date createDate, Boolean openStatus) {
        this.cardNumber = cardNumber;
        this.marketName = marketName;
        this.createDate = createDate;
        this.openStatus = openStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(Boolean openStatus) {
        this.openStatus = openStatus;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "MarketCheck{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", marketName='" + marketName + '\'' +
                ", createDate=" + createDate +
                ", openStatus=" + openStatus +
                '}';
    }
}
