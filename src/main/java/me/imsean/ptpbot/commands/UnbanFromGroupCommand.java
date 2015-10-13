package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.UserManager;
import me.imsean.ptpbot.exceptions.NotBannedFromGroupException;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

public class UnbanFromGroupCommand extends Command {

    private final UserManager userManager;

    public UnbanFromGroupCommand(UserManager userManager) {
        super(GroupUser.Role.MASTER, "unban");
        this.userManager = userManager;
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if (this.userManager.isBotAdmin(user)) {
            if(args.length == 0) {
                group.sendMessage(user.getUsername() + " - Usage: #unban (username)");
            }
            if(args.length > 0) {
                try {
                    this.userManager.unban(group, args[0]);
                } catch (NotBannedFromGroupException e) {
                    group.sendMessage(user.getUsername() + " - This user is not banned from this group");
                }
            }
        }
    }

}
