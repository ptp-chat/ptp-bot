package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.BotUser;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

import java.sql.SQLException;

public class Testcommand extends Command {

    public Testcommand() {
        super(GroupUser.Role.USER, "test");
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        group.sendMessage("hey");
        try {
            group.sendMessage(BotUser.test());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
