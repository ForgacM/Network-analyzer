package com.MarcelForgac.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelforgac on 16.3.15.
 */
public class Frame {

    public List<String> frameList ;//= new ArrayList<String>();
    public List<Integer> frameLength; // = new ArrayList<Integer>();
    public List<String> frameType; // = new ArrayList<String>();


    public void FrameParser (String hexString,Integer length) {
        String parseFrame = "";

        parseFrame = S32(hexString);
        parseFrame = S16(parseFrame);
        parseFrame = S2(parseFrame);

        frameList.add(parseFrame);
        frameLength.add(length);
    }

    private static String S32(String hexAddress) {
        return hexAddress.replaceAll("(.{32})","$1\n");
    }
    private static String S16(String hexAddress) {
        return hexAddress.replaceAll("(.{16})","$1  ");
    }
    private static String S2(String hexAddress){
        return hexAddress.replaceAll("(.{2})","$1 ");
    }

    public void TypeofFrame () {
        int i=0;
        for (String frame : Communication.packetList) {
            String tmp =frame.substring(24,28);
            String ieee = frame.substring(28,32);

            Integer size = Integer.parseInt(tmp, 16);

            if (size>1500) {
                frameType.add(i,"ETHERNET II\n");
            } else {
                if (ieee.equals("FFFF")) {
                    frameType.add(i,"IEEE 802.3 - raw\n");
                }else if (ieee.equals("AAAA")) {
                    frameType.add(i,"IEEE 802.3 - SNAP\n");
                }else {
                    frameType.add(i,"IEEE 802.3 - LLC\n");
                }
            }
            i++;
        }
    }

    public void Clear() {

        frameList = new ArrayList<String>();
        frameLength = new ArrayList<Integer>();
        frameType = new ArrayList<String>();
    }

}
