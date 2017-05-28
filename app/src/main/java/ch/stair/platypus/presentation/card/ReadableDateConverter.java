package ch.stair.platypus.presentation.card;

import java.util.Date;

import javax.inject.Inject;

public class ReadableDateConverter {

    @Inject
    ReadableDateConverter() {}

    public String convert(Date uglyDate) {
        int diffInDays;

        //todo: use utc time
        Date currentDate = new Date();

        String readableDate;

        long diff = currentDate.getTime() - uglyDate.getTime();

        diffInDays = (int) (diff / (1000 * 60 * 60 * 24));
        if (diffInDays > 0) {
            if (diffInDays == 1) {
                readableDate = diffInDays + " d";
            } else {
                readableDate = diffInDays + " d";
            }
        } else {
            int diffHours = (int) (diff / (60 * 60 * 1000));
            if (diffHours > 0) {
                if (diffHours == 1) {
                    readableDate = diffHours + " h";
                } else {
                    readableDate = diffHours + " h";
                }
            } else {

                int diffMinutes = (int) ((diff / (60 * 1000) % 60));
                if (diffMinutes == 1) {
                    readableDate = diffMinutes + " m";
                } else {
                    readableDate = diffMinutes + " m";
                }
            }
        }

        return readableDate;
    }
}
