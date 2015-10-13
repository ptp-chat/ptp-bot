package me.imsean.ptpbot.api.mysql;

import java.sql.*;

public class Connection {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://127.0.0.1/ptpbot";

    private String username = null;
    private String password = null;

    public static java.sql.Connection conn = null;

    private PreparedStatement stmt = null;


    public Connection(String username, String password) {
        this.username = username;
        this.password = password;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, this.username, this.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection query(String sql) {
        try {
            this.stmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ResultSet execute() throws SQLException {
        return this.stmt.executeQuery();
    }

    public PreparedStatement getStatement() {
        return this.stmt;
    }

}
