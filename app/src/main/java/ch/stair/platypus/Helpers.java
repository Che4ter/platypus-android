package ch.stair.platypus;

import java.util.Date;

public final class Helpers {
    public static String getReadableDate(Date uglyDate) {
        int diffInDays;

        //todo: use utc time
        Date currentDate = new Date();

        String readableDate;

        long diff = currentDate.getTime() - uglyDate.getTime();

        diffInDays = (int) (diff / (1000 * 60 * 60 * 24));
        if (diffInDays > 0) {
            if (diffInDays == 1) {
                readableDate = diffInDays + " day ago";
            } else {
                readableDate = diffInDays + " days ago";
            }
        } else {
            int diffHours = (int) (diff / (60 * 60 * 1000));
            if (diffHours > 0) {
                if (diffHours == 1) {
                    readableDate = diffHours + " hr ago";
                } else {
                    readableDate = diffHours + " hrs ago";
                }
            } else {

                int diffMinutes = (int) ((diff / (60 * 1000) % 60));
                if (diffMinutes == 1) {
                    readableDate = diffMinutes + " min ago";
                } else {
                    readableDate = diffMinutes + " mins ago";
                }
            }
        }

        return readableDate;
    }
}
