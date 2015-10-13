package me.imsean.ptpbot.api.command;

import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

/**
 * Created by sean on 10/11/15.
 */
public abstract class Command {

    private final GroupUser.Role role;
    private final String[] names;

    public Command(GroupUser.Role role, String... names) {
        this.role = role;
        this.names = names;
    }

    public GroupUser.Role getRole() {
        return role;
    }

    public String[] getNames() {
        return names;
    }

    public abstract void onCommand(Message message, Group group, User user, String[] args);

}