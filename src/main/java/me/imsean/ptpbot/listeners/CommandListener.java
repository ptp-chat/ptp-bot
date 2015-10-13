package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.api.command.CommandHandler;
import me.imsean.ptpbot.api.mysql.StatsManager;
import me.imsean.ptpbot.api.mysql.UserManager;
import xyz.gghost.jskype.SkypeAPI;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserChatEvent;

public class CommandListener implements EventListener {

    private final CommandHandler commandHandler;

    public CommandListener(UserManager userManager, StatsManager statsManager) {
        this.commandHandler = new CommandHandler(userManager, statsManager);
    }

    public void onCommand(UserChatEvent e) {
        if(!e.isEdited()) {
            if(e.getMsg().getMessage().startsWith(CommandHandler.prefix)) {
                this.commandHandler.handleCommand(e.getChat(), e.getUser(), e.getMsg());
            }
        }
    }

}
