package me.imsean.ptpbot.api.mysql;

import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.command.CommandHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sean on 10/12/15.
 */
public class Stats {

    private static Connection db = new Connection("root", "root");

    public static Integer getMessageCount() {
        int messages = 0;
        try {
            PreparedStatement stmt = db.query("SELECT `messages` FROM `stats`").getStatement();
            ResultSet result = db.execute();
            while(result.next()) {
                messages = result.getInt("messages");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static Integer getCommandCount() {
        CommandHandler ch = new CommandHandler();
        List<Command> commands = ch.getCommands();
        return commands.size();
    }

    public static void addMessages() {
        try {
            PreparedStatement stmt = db.query("INSERT INTO `stats` (`messages`) VALUES(?)").getStatement();
            stmt.setInt(1, (int)Math.random() * 1000);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
