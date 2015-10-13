package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.BotUser;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

public class AdminsCommand extends Command {

    public AdminsCommand() {
        super(GroupUser.Role.MASTER, "admins");
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if(BotUser.isBotAdmin(user) || user.getDisplayName().equals(PTPBot.getOwner())) {
            if(args.length == 0) {
                group.sendMessage(user.getDisplayName() + " - Usage: #admins (add/remove) (username)");
            }
            if(args.length > 0) {
                if(args[0].equalsIgnoreCase("add")) {

                }
                if(args[0].equalsIgnoreCase("remove")) {

                }
            }
        }
    }
}
