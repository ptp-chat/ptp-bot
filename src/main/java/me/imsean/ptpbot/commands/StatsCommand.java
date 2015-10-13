package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.StatsManager;
import me.imsean.ptpbot.api.mysql.UserManager;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

public class StatsCommand extends Command {

    private final UserManager userManager;
    private final StatsManager statsManager;

    public StatsCommand(UserManager userManager, StatsManager statsManager) {
        super(GroupUser.Role.USER, "stats", "statistics");
        this.userManager = userManager;
        this.statsManager = statsManager;
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if (this.userManager.isBotAdmin(user)) {
            StringBuilder stats = new StringBuilder();
            stats.append("PTPBot Statistics - ");
            stats.append("\n Received " + this.statsManager.getMessageCount() + " messages");
            stats.append("\n Added " + PTPBot.getSkype().getContacts().size() + " contacts");
            stats.append("\n Implemented " + this.statsManager.getCommandCount() + " commands");
            group.sendMessage(stats.toString());
        }
    }

}
