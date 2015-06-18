package com.MarcelForgac.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelforgac on 9.3.15.
 */
public class IPAddressComm {

    public static List<String> ipList;
    public static List<Integer> iplength;

    private static int count=0;
    public IPAddressComm(String hexAdress, Integer length) {

        String ip = Formatter.ipFormat(hexAdress);

        if (!ipList.contains(ip)) {
            ipList.add(ip);
            iplength.add(length);
            count++;
        } else {
            for (int i =0; i<= count-1 ; i++){
                if (ipList.get(i).equals(ip)) {
                    int val = iplength.get(i) + length;
                    iplength.remove(i);
                    iplength.add(i, val);
                }
            }
        }
    }

    public IPAddressComm() {
        count=0;
        iplength = new ArrayList<Integer>();
        ipList = new ArrayList<String>();
    }
}
