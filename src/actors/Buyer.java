package actors;

public class Buyer {
    private long buyer_id;
    private String name;
    long phone[];
    public Buyer(){
    }
    public long getId(){
        return buyer_id;
    }
    public void setId(long id){
        this.buyer_id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public long[] getPhone() {
        return phone;
    }

    public void setPhone(long[] phone) {
        this.phone = phone;
    }
}
