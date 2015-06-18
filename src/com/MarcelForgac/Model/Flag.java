package com.MarcelForgac.Model;

/**
 * Created by marcelforgac on 19.3.15.
 */
public class Flag {

    private String data;

    public Flag(String data) {
        this.data = new StringBuilder(Integer.toBinaryString(Formatter.toInteger(data))).reverse().toString();
    }


    public boolean ACK() {
        return isTrue(4);
    }

    public boolean SYN() {
        return isTrue(1);
    }

    public boolean RST() {
        return isTrue(2);
    }

    public boolean FYN() {
        return isTrue(0);
    }

    public boolean isTrue(int position) {
        return data.charAt(position) == '1';
    }

}
