package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.BotUser;
import me.imsean.ptpbot.exceptions.NotBannedFromGroupException;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

/**
 * Created by sean on 10/12/15.
 */
public class UnbanFromGroupCommand extends Command {

    public UnbanFromGroupCommand() {
        super(GroupUser.Role.MASTER, "unban");
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if(BotUser.isBotAdmin(user)) {
            if(args.length == 0) {
                group.sendMessage(user.getUsername() + " - Usage: #unban (username)");
            }
            if(args.length > 0) {
                try {
                    BotUser.unbanFromGroup(group, args[0]);
                } catch (NotBannedFromGroupException e) {
                    group.sendMessage(user.getUsername() + " - This user is not banned from this group");
                }
            }
        }
    }

}
