package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.api.command.Command;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

public class JoinCommand extends Command {

    public JoinCommand() {
        super(GroupUser.Role.USER, "join");
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if(true) {
            group.sendMessage("This command is disabled at the moment!");
            return;
        }
        if(args.length == 0) {
            group.sendMessage(user.getUsername() + " - Usage: #join (link)");
        }
        if(args.length > 0) {
            System.out.println(message.getMessage().split(" ")[0]);
//            if(UrlValidator.getInstance().isValid(href)) {
//                PTPBot.getSkype().joinInviteLink(href);
//            } else {
//                group.sendMessage(user.getUsername() + " - Invalid Join Link");
//            }
        }
    }

}
