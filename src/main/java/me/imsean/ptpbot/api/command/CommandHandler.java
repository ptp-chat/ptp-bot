package me.imsean.ptpbot.api.command;

import me.imsean.ptpbot.commands.BanFromGroupCommand;
import me.imsean.ptpbot.commands.JoinCommand;
import me.imsean.ptpbot.commands.StatsCommand;
import me.imsean.ptpbot.commands.UnbanFromGroupCommand;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.User;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {

    public static final String prefix = "#";

    private List<Command> commands;

    public CommandHandler() {
        this.commands = new ArrayList<Command>();

        addCommands(
                new BanFromGroupCommand(),
                new UnbanFromGroupCommand(),
                new JoinCommand(),
                new StatsCommand()
                );
    }

    public List<Command> getCommands() {
        return commands;
    }

    private void addCommands(Command... commands) {
        for (Command c : commands) {
            this.commands.add(c);
        }
    }


    public void handleCommand(Group group, User user, Message message) {
        String input = message.getMessage();
        String id = group.getId();

        if (!input.startsWith(prefix)) return;

        String noPrefix = input.substring(prefix.length());
        String[] args = noPrefix.split(" ");
        String command = args[0];

        for (Command c : commands) {
            for (String name : c.getNames()) {
                if (name.equalsIgnoreCase(args[0])) {
                    List<String> newArgs = new ArrayList<String>();
                    for (String s : args) if (!args[0].equalsIgnoreCase(s)) newArgs.add(s);
                    String[] arrayArgs = newArgs.toArray(new String[newArgs.size()]);

                    c.onCommand(message, group, user, arrayArgs);
                    break;
                }
            }
        }
    }

}