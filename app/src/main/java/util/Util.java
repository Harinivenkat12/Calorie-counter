package util;

import java.text.DecimalFormat;

/**
 * Created by achitu on 6/8/16.
 */
public class Util {

    public static String formatNumber(int value){
        DecimalFormat decimalFormat=  new DecimalFormat("#,###,###");
        String decimalFormattedValue= decimalFormat.format(value);

        return  decimalFormattedValue;
    }
}
