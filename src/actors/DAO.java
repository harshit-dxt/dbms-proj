package actors;

public interface DAO {
    public static final String DB_URL =
            "jdbc:mysql://localhost/"+
                    "dbms_proj?zeroDateTimeBehavior=convertToNull";
    public static final String DRIVER =
            "com.mysql.cj.jdbc.Driver";
    public static final String USER = "Mi_Casa";
    public static final String PASS = "Brahmaputra";
}
