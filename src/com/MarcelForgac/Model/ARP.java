package com.MarcelForgac.Model;

/**
 * Created by marcelforgac on 19.3.15.
 */
public class ARP {

    public String data;
    public String formattedData;
    public String destinationAddress;
    public String sourceAddress;
    public Integer index;
    public String operation;
    public String macAddress;
    public Boolean complete = false;

    public ARP(Integer index, String data) {
        this.data = data;
        this.index = index;

        if (data.substring(40,44).equals("0001")) {
            operation = "Request";
        } else if (data.substring(40,44).equals("0002")) {
            operation = "Replay";
        }
    }

    public void setFormattedData() {
        this.formattedData = Communication.finalPacketList.get(index);
    }

    public String getDestinationAddress() {
        return destinationAddress = Formatter.ipFormat(data.substring(76, 84));
    }

    public String getSourceAddress() {
        return sourceAddress = Formatter.ipFormat(data.substring(56, 64));
    }

    public String getMacAddress() {
        return macAddress = Formatter.macFormat(data.substring(44, 52));
    }

}
