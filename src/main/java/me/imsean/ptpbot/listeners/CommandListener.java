package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.api.command.CommandHandler;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserChatEvent;

public class CommandListener implements EventListener {

    CommandHandler ch = new CommandHandler();

    public void onCommand(UserChatEvent e) {
        if(!e.isEdited()) {
            if(e.getMsg().getMessage().startsWith(CommandHandler.prefix)) {
                ch.handleCommand(e.getChat(), e.getUser(), e.getMsg());
            }
        }
    }

}
