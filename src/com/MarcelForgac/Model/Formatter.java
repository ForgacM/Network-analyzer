package com.MarcelForgac.Model;

/**
 * Created by marcelforgac on 19.3.15.
 */
public abstract class Formatter {

    public static String ipFormat(String hexAddress) {
        return toInteger(hexAddress.substring(0, 2)) + "." + Integer.parseInt(hexAddress.substring(2,4),16) + "." +
                + Integer.parseInt(hexAddress.substring(4,6),16) + "." + Integer.parseInt(hexAddress.substring(6, 8), 16);
    }

    public  static String macFormat(String hexAddress) {
        return hexAddress.replaceAll("(.{2})","$1 ");
    }

    public static int toInteger(String hex) {
        return Integer.parseInt(hex,16);
    }
}
