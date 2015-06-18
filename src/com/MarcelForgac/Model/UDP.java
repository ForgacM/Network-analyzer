package com.MarcelForgac.Model;

/**
 * Created by marcelforgac on 19.3.15.
 */
public class UDP {

	private String formattedData;
	private String sourcePort;
	private String destinationPort;
	private String sourceIpAddress;
	private String destinationIpAddress;
	private String data;
	private Integer index;

	public UDP(String data, int index) {
		this.data = data;
		this.index = index;
	}

	public void setFormattedData() {
		this.formattedData = Communication.finalPacketList.get(index);
	}

	public String getFormattedData() {
		return formattedData;
	}

	public String getDestinationPort() {
		return destinationPort = data.substring(72,76);
	}

	public String getSourcePort() {
		return sourcePort = data.substring(68,72);
	}

	public String getSourceIpAddress() {
		return sourceIpAddress = Formatter.ipFormat(data.substring(52,60));
	}

	public String getDestinationIpAddress() {
		return destinationIpAddress = Formatter.ipFormat(data.substring(60, 68));
	}
}
