package ir.mohammadno3ratii.app.dev.digicoinshow.base;

import android.util.Log;

import java.text.DecimalFormat;

public class GeneralHelper {

    public static String setNumberBySimicalman(String value) {
        long longVal;
        String formattedString = null;
        DecimalFormat formatter;

        if (value.contains(",")) {
            value = value.replaceAll(",", "");
        }

        if (value.contains(".")) {
            try {
                String[] arrayValue = value.split("\\.");

                longVal = Long.parseLong(arrayValue[0]);
                formatter = new DecimalFormat("#,###,###");
                formattedString = formatter.format(longVal);
                formattedString += "." + arrayValue[1];
            } catch (Exception e) {
                Log.i("LOG", e.getMessage());
            }
        } else {
            longVal = Long.parseLong(value);
            formatter = new DecimalFormat("#,###,###");
            formattedString = formatter.format(longVal);
        }
        return formattedString;
    }

    public static String setNumberWithoutSimicalman(String value) {
        if (value.contains(",")) {
            return value.replaceAll(",", "");
        }
        return value;
    }

}
