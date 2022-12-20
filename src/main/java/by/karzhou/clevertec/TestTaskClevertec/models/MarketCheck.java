package by.karzhou.clevertec.TestTaskClevertec.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Market_check")
public class MarketCheck{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Max(message = "Номер карты не должен быть больше 100000", value = 100000)
    @Min(message = "Номер карты должен быть больше 0", value = 0)
    @Column(name = "card_number")
    private int cardNumber;

    @Column(name = "market_name")
    private String marketName;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "open_status")
    private Boolean openStatus;

    @OneToMany(mappedBy = "marketCheck")
    private List<Purchases> purchasesList;

    public MarketCheck(){
    }

    public MarketCheck(int cardNumber, String marketName, Date createDate, Boolean openStatus, List<Purchases> purchasesList) {
        this.cardNumber = cardNumber;
        this.marketName = marketName;
        this.createDate = createDate;
        this.openStatus = openStatus;
        this.purchasesList = purchasesList;
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

    public List<Purchases> getPurchasesList() {
        return purchasesList;
    }

    public void setPurchasesList(List<Purchases> purchasesList) {
        this.purchasesList = purchasesList;
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
