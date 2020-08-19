package actors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Buyer_DAO implements DAO{
    private Buyer createBuyers(ResultSet rs){
        Buyer a = new Buyer();
        try {
            a.setId(rs.getLong("Buyer_id"));
            a.setName(rs.getString("Buyer_name"));
        } catch (SQLException ex) {
        }

        return a;
    }

    public List<Buyer> getContacts() {
        String sql = "Select * from Buyer order by Buyer_id";
        List<Buyer> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Buyer a = createBuyers(rs);
                list.add(a);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public void addBuyers(long buyer_id, String buyer_name){
        String insert = "INSERT INTO Buyer VALUES("+buyer_id+",\'"+buyer_name+"\')";
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
