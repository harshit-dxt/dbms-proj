package actors;

import java.util.Date;
public class Houses {
    private long house_id;
    private String type;
    private long agent_id;
    private long bedroom_no;
    private long square_feet;
    private String street;
    private String city;
    private long zipcode;
    private long owner_id;
    private long buyer_id;
    private long sellingPrice;
    private Date listDate;
    private Date dealingDate;

    public long getHouse_id(){
        return house_id;
    }

    public long getAgent_id() {
        return agent_id;
    }

    public long getBedroom_no() {
        return bedroom_no;
    }

    public long getSquare_feet() {
        return square_feet;
    }

    public String getType() {
        return type;
    }

    public Date getDealingDate() {
        return dealingDate;
    }

    public Date getListDate() {
        return listDate;
    }

    public long getBuyer_id() {
        return buyer_id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public long getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public long getSellingPrice() {
        return sellingPrice;
    }

    public void setAgent_id(long agent_id) {
        this.agent_id = agent_id;
    }

    public void setBedroom_no(long bedroom_no) {
        this.bedroom_no = bedroom_no;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHouse_id(long house_id) {
        this.house_id = house_id;
    }

    public void setBuyer_id(long buyer_id) {
        this.buyer_id = buyer_id;
    }

    public void setSquare_feet(long square_feet) {
        this.square_feet = square_feet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDealingDate(Date dealingDate) {
        this.dealingDate = dealingDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public void setSellingPrice(long sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setZipcode(long zipcode) {
        this.zipcode = zipcode;
    }
}
