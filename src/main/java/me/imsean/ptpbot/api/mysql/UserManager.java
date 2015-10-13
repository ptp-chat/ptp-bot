package me.imsean.ptpbot.api.mysql;

import me.imsean.ptpbot.exceptions.NotAdminException;
import me.imsean.ptpbot.exceptions.NotBannedFromGroupException;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.SkypeAPI;
import xyz.gghost.jskype.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    private final SkypeAPI skype;
    private final Connection connection;

    public UserManager(SkypeAPI skype, Connection connection) {
        this.skype = skype;
        this.connection = connection;
    }

//    public boolean isBanned(User user, Group group) {
//        // TODO: Implement
//        throw new NotImplementedException("isBanned isn't implemented yet!");
//    }

    public void ban(Group group, String username) throws NotAdminException {
        if (group.isAdmin(this.skype.getUsername())) {
            try {
                PreparedStatement stmt = this.connection.query("INSERT INTO `banned` (`username`, `group`) VALUES(?, ?)").getStatement();
                stmt.setString(1, username);
                stmt.setString(2, group.getId());
                this.connection.getStatement().executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            group.kick(username);
        } else {
            throw new NotAdminException();
        }
    }

    public void unban(Group group, String username) throws NotBannedFromGroupException {
        try {
            if (isBanned(group, username)) {
                PreparedStatement stmt = this.connection.query("DELETE FROM `banned` WHERE `username`=? AND `group`=?").getStatement();
                stmt.setString(1, username);
                stmt.setString(2, group.getId());
                this.connection.getStatement().executeUpdate();
                group.add(username);
            } else {
                throw new NotBannedFromGroupException();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query!", e);
        }
    }

    public boolean isBanned(Group group, String username) {
        try {
            PreparedStatement stmt = this.connection.query("SELECT * FROM `banned` WHERE `username`=?").getStatement();
            stmt.setString(1, username);
            ResultSet result = this.connection.execute();
            while (result.next()) {
                if (username.equals(result.getString("username"))
                        && group.getId().equalsIgnoreCase(result.getString("group"))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query!", e);
        }
    }

    public boolean isBotAdmin(User user) {
        try {
            PreparedStatement stmt = this.connection.query("SELECT * FROM `admins` WHERE `username`=?").getStatement();
            stmt.setString(1, user.getUsername());
            ResultSet result = this.connection.execute();
            while(result.next()) {
//                System.out.println(result.getString("username"));
                if (user.getUsername().equals(result.getString("username"))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query!", e);
        }
    }

}
