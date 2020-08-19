package actors;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agent_DAO implements DAO{
    private Agent createAgents(ResultSet rs){
        Agent a = new Agent();
        try {
            a.setId(rs.getLong("Agent_id"));
            a.setName(rs.getString("Agent_name"));
        } catch (SQLException ex) {
        }

        return a;
    }

    public List<Agent> getContacts() {
        String sql = "Select * from Agent order by Agent_id";
        List<Agent> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Agent p = createAgents(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public void addAgents(long agent_id, String agent_name){
        String insert = "INSERT INTO Agent VALUES("+agent_id+",\'"+agent_name+"\')";
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(insert);
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
