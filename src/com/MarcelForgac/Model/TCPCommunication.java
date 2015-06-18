package com.MarcelForgac.Model;

import java.util.ArrayList;

/**
 * Created by marcelforgac on 21.3.15.
 */
public class TCPCommunication {

	private ArrayList<TCP> tcpList = new ArrayList<>();
	private boolean completeHand = false;
	private boolean completeTerminated = false;


	public TCPCommunication() {

	}
	private int handshakeCount =0,terminatedCount = 0;

	public void addToTcpList(TCP tcp) {
		this.tcpList.add(tcp);

		 if (tcp.flag.SYN() && tcp.flag.ACK()) {
				handshakeCount = 2;
			} else {
				if (!tcp.flag.SYN() && tcp.flag.ACK() && handshakeCount == 2) {
					completeHand = true;
					handshakeCount = 0;
				}
			}
		if (completeHand && !completeTerminated) {
			if ((tcp.flag.FYN() && tcp.flag.ACK())) {
				terminatedCount = 1;
				completeTerminated=true;
			}
			if (tcp.flag.RST()) {
				completeTerminated = true;
			}
		}
	}


	public boolean isValid (TCP tcp) {
		TCP first = tcpList.get(0);
		return (first.getDestinationPort().equals(tcp.getSourcePort())
				&& first.getDestinationIpAddress().equals(tcp.getSourceIpAddress())
				&& first.getSourcePort().equals(tcp.getDestinationPort())
				&& first.getSourceIpAddress().equals(tcp.getDestinationIpAddress()))
				|| (first.getDestinationIpAddress().equals(tcp.getDestinationIpAddress())
				&& first.getDestinationPort().equals(tcp.getDestinationPort())
				&& first.getSourcePort().equals(tcp.getSourcePort())
				&& first.getSourceIpAddress().equals(tcp.getSourceIpAddress()));
	}

	public boolean isCompleteTerminated() {
		return completeTerminated;
	}

	public boolean isCompleteHand() {
		return completeHand;
	}

	public boolean isCompletedCommunication() {
		return isCompleteHand() && isCompleteTerminated();
	}

	public ArrayList<TCP> getTcpList() {
		return tcpList;
	}

	public Integer getSize() {
		return tcpList.size();
	}
}
