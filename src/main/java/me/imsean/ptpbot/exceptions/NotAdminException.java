package me.imsean.ptpbot.exceptions;

/**
 * Created by sean on 10/11/15.
 */
public class NotAdminException extends Exception {

    public NotAdminException() {
        super("I am not ADMIN in this group");
    }

}
