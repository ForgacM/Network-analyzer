package com.MarcelForgac.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by marcelforgac on 9.3.15.
 */
public abstract class PacketAnalyzer {

	public static String arp() {
		String pairs = "";

		ArrayList<ARP> requestList = new ArrayList<>();

		int index = 1;
		for (ARP ar : Communication.arpList) {

			ar.setFormattedData();
			if (ar.operation.equals("Request")) {
				requestList.add(ar);
				ar.complete = false;
			} else {
				for (ARP req : requestList) {
					if (req.getSourceAddress().equals(ar.getDestinationAddress()) && !req.complete) {
						pairs = String.format("%sKomunikácia č.%d\nARP-Request, " +
								"IP adresa:%s MAC adresa: ???\nZdrojová IP: %s Cieľová IP: %s\n%s"
								, pairs, index, ar.getDestinationAddress(), ar.getSourceAddress()
								, ar.getDestinationAddress(), ar.formattedData);
						pairs = String.format("%s\n\nKomunikácia č.%d\nARP-Request, " +
								"IP adresa:%s MAC adresa: %s\nZdrojová IP: %s Cieľová IP: %s\n%s"
								, pairs, index, req.getDestinationAddress(), req.getMacAddress()
								, req.getSourceAddress(), req.getDestinationAddress(), req.formattedData);
						index++;
						req.complete = true;
					}
				}

			}
		}
		return pairs;
	}

	public static String tcp(String type) {

		ArrayList<TCPCommunication> tcpCommunicationList = new ArrayList<>();

		for (TCP tcp : Communication.tcpList) {
			tcp.setTapFormattedData();

			if (tcp.getTypeOfPort().equals(type)) {
				if (tcp.flag.SYN() && !tcp.flag.ACK()) {
					TCPCommunication tcpCom = new TCPCommunication();
					tcpCom.addToTcpList(tcp);
					tcpCommunicationList.add(tcpCom);
				} else {
					for (TCPCommunication tcpCommunication : tcpCommunicationList) {
						if (tcpCommunication.isValid(tcp)){
							tcpCommunication.addToTcpList(tcp);
							break;
						}
					}
				}
			}
		}

		String outputComplete = "", outputUncomplete = "";
		int i = 0;

		for (TCPCommunication tcpComm : tcpCommunicationList) {
			if (tcpComm.isCompletedCommunication() && i == 0) {
				Statistic.createChart();
				i=1;
				outputComplete = outputComplete + "Komunikácia kompletná\n";
				outputComplete = outputComplete + "Klient: " + tcpComm.getTcpList().get(0).getSourceIpAddress() +
						":" + Formatter.toInteger(tcpComm.getTcpList().get(0).getSourcePort());
				outputComplete = outputComplete + "\t Server: " + tcpComm.getTcpList().get(0).getDestinationIpAddress() +
						":" + tcpComm.getTcpList().get(0).getTypeOfPort() + "(" + Formatter.toInteger(tcpComm.getTcpList().get(0).getDestinationPort()) + ")" + "\n\n";

				int index=0;
				int size = tcpComm.getSize();
				if (size>20) {
					for (TCP tcp : tcpComm.getTcpList()) {
							Statistic.setStatistics(tcp.getLenght());
						if (index>9 && index<(size-10)){
						}
						else outputComplete = outputComplete + (tcp.formattedData);
						index++;
					}
				} else {
				for (TCP tcp : tcpComm.getTcpList()) {
					Statistic.setStatistics(tcp.getLenght());
					outputComplete = outputComplete + (tcp.formattedData);
				}
				}
				outputComplete = outputComplete + Statistic.getStatistic();
			} else if (!tcpComm.isCompletedCommunication() && i!=2) {
				Statistic.createChart();
				i=2;
				outputUncomplete = outputUncomplete + "Komunikácia nekompletná\n";
				outputUncomplete = outputUncomplete + "Klient: " + tcpComm.getTcpList().get(0).getSourceIpAddress() +
						":" + Formatter.toInteger(tcpComm.getTcpList().get(0).getSourcePort());
				outputUncomplete = outputUncomplete + "\t Server: " + tcpComm.getTcpList().get(0).getDestinationIpAddress() +
						":" + tcpComm.getTcpList().get(0).getTypeOfPort() + "(" + Formatter.toInteger(tcpComm.getTcpList().get(0).getDestinationPort()) + ")" + "\n\n";

				int index=0;
				int size = tcpComm.getSize();
				if (size>20) {
					for (TCP tcp : tcpComm.getTcpList()) {
						Statistic.setStatistics(tcp.getLenght());
						if (index>9 && index<(size-10)){
						}
						else outputUncomplete = outputUncomplete + (tcp.formattedData);
						index++;
					}
				} else {
					for (TCP tcp : tcpComm.getTcpList()) {
						Statistic.setStatistics(tcp.getLenght());
						outputUncomplete = outputUncomplete + (tcp.formattedData);
					}
				}
				outputUncomplete = outputUncomplete + Statistic.getStatistic();
			}
		}
		return outputComplete + outputUncomplete;
	}


	public static String icmp() {

		String output = "";
		for (ICMP ic : Communication.icmpList) {
			ic.setFormattedData();
			output = output + "Typ ICMP správy: " + ic.getTypeOfIcmp() + "\t Source IP: " + ic.getSourceIpAddress()
					+ "  Destination IP: " + ic.getDestinationIpAddress() + "\n";
			output = output + ic.getFormattedData();
		}
		return output;
	}

	public static ArrayList<String> tftp() {

		ArrayList<String> finalOutput = new ArrayList<>();

		String sourcePort = "", output = "";

		for (UDP udp : Communication.udpList) {
			output="";
			udp.setFormattedData();
			if (udp.getDestinationPort().equals("0045")) {
				sourcePort = udp.getSourcePort();
				output = output + "\nNová komunikácia \t Source IP:" + udp.getSourceIpAddress()
						+ "  Destination IP:" + udp.getDestinationIpAddress() + "\n";
				output = output + udp.getFormattedData();
			} else if (sourcePort.equals(udp.getDestinationPort()) || sourcePort.equals(udp.getSourcePort())) {
				output = output + udp.getFormattedData();
			}
			finalOutput.add(output);
		}
		return finalOutput;
	}
}
