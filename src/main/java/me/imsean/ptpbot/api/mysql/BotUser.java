package me.imsean.ptpbot.api.mysql;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.exceptions.NotAdminException;
import me.imsean.ptpbot.exceptions.NotBannedFromGroupException;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sean on 10/11/15.
 */
public class BotUser {

    public static Connection db = new Connection("root", "root");

    public static boolean isBanned(User user, Group group) throws SQLException {
        //TODO
        return false;
    }

    public static String test() throws SQLException {
        ResultSet result = db.query("SELECT * FROM `banned`").execute();
        String res = null;
        while(result.next()) {
            res = result.getString("username");
        }
        return res;
    }

    public static void banFromGroup(Group group, String username) throws NotAdminException {
        if(group.isAdmin(PTPBot.getSkype().getUsername())) {
            try {
                PreparedStatement stmt = db.query("INSERT INTO `banned` (`username`, `group`) VALUES(?, ?)").getStatement();
                stmt.setString(1, username);
                stmt.setString(2, group.getId());
                db.getStatement().executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            group.kick(username);
        } else {
            throw new NotAdminException();
        }
    }

    public static void unbanFromGroup(Group group, String username) throws NotBannedFromGroupException {
        try {
            if (isBannedFromGroup(group, username)) {
                PreparedStatement stmt = db.query("DELETE FROM `banned` WHERE `username`=? AND `group`=?").getStatement();
                stmt.setString(1, username);
                stmt.setString(2, group.getId());
                db.getStatement().executeUpdate();
                group.add(username);
            } else {
                throw new NotBannedFromGroupException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isBannedFromGroup(Group group, String username) {
        boolean isBanned = false;
        try {
            PreparedStatement stmt = db.query("SELECT * FROM `banned` WHERE `username`=?").getStatement();
            stmt.setString(1, username);
            ResultSet result = db.execute();
            while(result.next()) {
                if(username.equals(result.getString("username")) && group.getId().equals(result.getString("group"))) {
                    isBanned = true;
                } else {
                    isBanned = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isBanned;
    }

    public static boolean isBotAdmin(User user) {
        boolean isBotAdmin = false;
        try {
            PreparedStatement stmt = db.query("SELECT * FROM `admins` WHERE `username`=?").getStatement();
            stmt.setString(1, user.getUsername());
            ResultSet result = db.execute();
            while(result.next()) {
                System.out.println(result.getString("username"));
                if(user.getUsername().equals(result.getString("username"))) {
                    isBotAdmin = true;
                } else {
                    isBotAdmin = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isBotAdmin;
    }

    public static boolean isAdmin(Group group) {
        return group.isAdmin();
    }

}
