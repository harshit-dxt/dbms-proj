package actors;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HousesDAO implements DAO{
    private Houses createHouses(ResultSet rs){
        Houses p = new Houses();
        try {
            p.setHouse_id(rs.getLong("House_id"));
            p.setType(rs.getString("Type"));
            p.setAgent_id(rs.getLong("Agent_id"));
            p.setStreet(rs.getString("Street"));
            p.setBedroom_no(rs.getLong("Bedroom_no"));
            p.setCity(rs.getString("City"));
            p.setZipcode(rs.getLong("Zipcode"));
            p.setBuyer_id(rs.getLong("Buyer_id"));
            p.setOwner_id(rs.getLong("Owner_id"));
            p.setListDate(rs.getDate("List_date"));
            p.setDealingDate(rs.getDate("Dealing_date"));
            p.setSquare_feet(rs.getLong("Square_feet"));
            p.setSellingPrice(rs.getLong("Selling_price"));
        } catch (SQLException ex) {
        }

        return p;
    }

    public List<Houses> getContacts() {
        String sql = "Select * from Houses order by House_id";
        List<Houses> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Houses p = createHouses(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Houses> getHousesForId(long id) {
        String sql = "Select * from Houses where Agent_id =" + id ;
        List<Houses> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Houses p = createHouses(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public void updateHouses(long house_id, long buyer_id, long sale_price, String type, LocalDate t){

        String update = "UPDATE Houses SET Buyer_id="+buyer_id+", Selling_price = "+sale_price+", Type = \'"+type+"\', Dealing_date= (SELECT STR_TO_DATE(\'"+t+"\',\'%Y-%m-%d\')) Where House_id="+house_id;
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection
                    (DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(update);

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
