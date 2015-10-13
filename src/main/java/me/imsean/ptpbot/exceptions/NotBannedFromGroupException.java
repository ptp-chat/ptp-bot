package me.imsean.ptpbot.exceptions;

/**
 * Created by sean on 10/12/15.
 */
public class NotBannedFromGroupException extends Exception {

    public NotBannedFromGroupException() {
        super("This user is banned from this group");
    }

}
