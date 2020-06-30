package clientfx.Commands.Utils.Readers.NotPrimitiveReaders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateReader {
    public static Date read(String messageForConsole, boolean canBeNull) {
        Date time;
        time = null;
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        String readString = in.nextLine().trim();

        while(!canBeNull && readString.equals("")) {
            System.out.print("Данное поле не может быть пустым. " + messageForConsole);
            readString = in.nextLine().trim();
        }
        //noinspection StatementWithEmptyBody
        if(canBeNull && readString.equals("")) {
        }
        else {
            while (true) {
                try {
                    if (readString.equals("")){
                        break;
                    }
                    else {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        time = format.parse(readString);
                        break;
                    }
                } catch (Exception ignored) {
                    System.out.print(messageForConsole);
                    readString = in.nextLine().trim();
                }
            }
        }

        return time;
    }
}
