package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.BotUser;
import me.imsean.ptpbot.api.mysql.Stats;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

/**
 * Created by sean on 10/12/15.
 */
public class StatsCommand extends Command {

    public StatsCommand() {
        super(GroupUser.Role.USER, "stats", "statistics");
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if(BotUser.isBotAdmin(user)) {
            StringBuilder stats = new StringBuilder();
            stats.append("PTPBot Statistics - ");
            stats.append("\n Received " + Stats.getMessageCount() + " messages");
            stats.append("\n Added " + PTPBot.getSkype().getContacts().size() + " contacts");
            stats.append("\n Implemented " + Stats.getCommandCount() + " commands");
            group.sendMessage(stats.toString());
        }
    }

}
