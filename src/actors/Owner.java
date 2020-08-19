package actors;

public class Owner {
    private long owner_id;
    private String name;
    public Owner(){
    }
    public long getId(){
        return owner_id;
    }
    public void setId(long id){
        this.owner_id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
