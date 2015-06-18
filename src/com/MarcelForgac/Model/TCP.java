package com.MarcelForgac.Model;

import com.MarcelForgac.Model.XmlLogic.PortsList;

/**
 * Created by marcelforgac on 19.3.15.
 */
public class TCP {

    private String data;
    public String formattedData;
    public Flag flag;
    private String sourcePort;
    private String destinationPort;
    private String sourceIpAddress;
    private String destinationIpAddress;
    private String typeOfPort;
    private int index;
    private int lenght;

    public TCP(String data, int index, int lenght) {
        this.data = data;
        this.index = index;
        this.lenght = lenght;

        flag = new Flag(data.substring(92,96));
        if (PortsList.getNameByHex(getDestinationPort()).equals("")){
            typeOfPort = PortsList.getNameByHex(getSourcePort());
        } else typeOfPort = PortsList.getNameByHex(getDestinationPort());
    }

    public void setTapFormattedData() {
        this.formattedData = Communication.finalPacketList.get(index);
    }

    public String getFormattedData() {
        return formattedData;
    }

    public String getSourcePort() {
        return sourcePort = data.substring(68,72);
    }

    public String getDestinationPort() {
        return destinationPort = data.substring(72,76);
    }

    public String getSourceIpAddress() {
        return sourceIpAddress = Formatter.ipFormat(data.substring(52,60));
    }

    public String getDestinationIpAddress() {
        return destinationIpAddress = Formatter.ipFormat(data.substring(60, 68));
    }

    public int getIndex() {
        return index;
    }

    public String getTypeOfPort() {
        return typeOfPort;
    }

    public int getLenght() {
        return lenght;
    }
}
