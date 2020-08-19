package actors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Owner_DAO implements DAO{
    private Owner createOwners(ResultSet rs){
        Owner a = new Owner();
        try {
            a.setId(rs.getLong("Owner_id"));
            a.setName(rs.getString("Owner_name"));
        } catch (SQLException ex) {
        }

        return a;
    }

    public List<Owner> getContacts() {
        String sql = "Select * from Owner order by Owner_id";
        List<Owner> list = new ArrayList<Owner>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Owner a = createOwners(rs);
                list.add(a);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public void addOwners(long owner_id, String owner_name){
        String insert = "INSERT INTO Owner VALUES("+owner_id+",\'"+owner_name+"\')";
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
