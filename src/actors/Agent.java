package actors;

public class Agent {
    private long agent_id;
    private String name;
    public Agent(){
    }
    public long getId(){
        return agent_id;
    }
    public void setId(long id){
        this.agent_id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
