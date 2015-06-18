package com.MarcelForgac.Model;

import com.MarcelForgac.Model.XmlLogic.IcmpList;

/**
 * Created by marcelforgac on 21.3.15.
 */
public class ICMP {

	private String formattedData;
	private String sourceIpAddress;
	private String destinationIpAddress;
	private String data;
	private Integer index;
	private int type;

	public ICMP(String data,Integer index) {
		this.data = data;
		this.index = index;

		type = Formatter.toInteger(data.substring(68,70));
	}

	public void setFormattedData() {
		this.formattedData = Communication.finalPacketList.get(index);
	}

	public String getFormattedData() {
		return formattedData;
	}

	public String getTypeOfIcmp() {
		int i = 0;
		return IcmpList.getNameBydec(type);
	}

	public String getSourceIpAddress() {
		return sourceIpAddress = Formatter.ipFormat(data.substring(52,60));
	}

	public String getDestinationIpAddress() {
		return destinationIpAddress = Formatter.ipFormat(data.substring(60, 68));
	}
}
