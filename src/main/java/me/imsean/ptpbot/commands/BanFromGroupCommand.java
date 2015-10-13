package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.BotUser;
import me.imsean.ptpbot.exceptions.NotAdminException;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

/**
 * Created by sean on 10/11/15.
 */
public class BanFromGroupCommand extends Command {


    public BanFromGroupCommand() {
        super(GroupUser.Role.MASTER, "ban");
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if(!BotUser.isBotAdmin(user)) return;
        if(args.length == 0) {
            group.sendMessage(user.getUsername() + " - Usage: #ban (username)");
        }
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase(PTPBot.getOwner())) {
                group.sendMessage(user.getUsername() + " - You cannot ban this user!");
                return;
            }
            try {
                for(GroupUser gu : group.getClients()) {
                    if(gu.getUser().getUsername().equals(args[0])) {
                        BotUser.banFromGroup(group, args[0]);
                        if(!PTPBot.getSkype().getUserByUsername(args[0]).isContact()) {
                            PTPBot.getSkype().getUserByUsername(args[0]).sendContactRequest(PTPBot.getSkype());
                        }
                    }
                }
            } catch (NotAdminException e) {
                group.sendMessage(user.getUsername() + " - I am not ADMIN in this group");
            }
        }
    }

}
