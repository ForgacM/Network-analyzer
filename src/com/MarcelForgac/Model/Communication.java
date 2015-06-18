package com.MarcelForgac.Model;

import com.MarcelForgac.Model.XmlLogic.EthernetList;
import com.MarcelForgac.Model.XmlLogic.PortsList;
import com.MarcelForgac.Model.XmlLogic.ProtocolsList;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

import javax.xml.bind.DatatypeConverter;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by marcelforgac on 16.3.15.
 */
public abstract class Communication {

    public static List<String> packetList;// = new ArrayList<String>();
    public static ArrayList<String> finalPacketList;
    public static ArrayList<ARP> arpList;
    public static ArrayList<TCP> tcpList;
    public static ArrayList<ICMP> icmpList;
    public static ArrayList<UDP> udpList;

    static Frame frame = new Frame();

    public static void communicationAnalyzer(String PCAP_FILE) throws PcapNativeException {

        frame.Clear();
        PcapHandle handle = Pcaps.openOffline(PCAP_FILE);
        packetList = new ArrayList<>();
        arpList = new ArrayList<>();
        tcpList = new ArrayList<>();
        icmpList = new ArrayList<>();
        udpList = new ArrayList<>();
        int index=0;

        while (true) {
            try {

                Packet packet = handle.getNextPacketEx();
                String mainString = DatatypeConverter.printHexBinary(packet.getRawData());
                int length = packet.length();

                packetList.add(mainString);
                frame.FrameParser(mainString, length);
                if (mainString.substring(24,28).equals(EthernetList.get("ARP"))) {
                    arpList.add(new ARP(index, mainString));
                } else if (mainString.substring(24,28).equals(EthernetList.get("IP"))) {

                    if (mainString.substring(46, 48).equals(ProtocolsList.get("TCP"))) {
                        tcpList.add(new TCP(mainString,index,length));
                    } else if (mainString.substring(46,48).equals(ProtocolsList.get("ICMP"))) {
                        icmpList.add(new ICMP(mainString,index));
                    } else if (mainString.substring(46,48).equals(ProtocolsList.get("UDP"))) {
                        udpList.add(new UDP(mainString,index));
                    }
                }
                index++;


            } catch (TimeoutException e) {
            } catch (EOFException e) {
                break;
            }
        }
    }

    public static void analyzer(Integer type) {

        finalPacketList = new ArrayList<>();

        frame.TypeofFrame();
        String analyze="";

        int i = 0;

        for (String s : frame.frameList) {
            analyze = "";
            analyze = analyze + "Rámec č." + (i+1) + "\n";

            analyze = analyze + ("dĺžka rámca poskytnutá paketovým drajverom - " + frame.frameLength.get(i) + " B \n");

            if ((frame.frameLength.get(i) + 4) < 64) {
                analyze = analyze + ("dĺžka rámca prenášaného po médiu - " + "64" + " B \n");
            } else
                analyze = analyze + ("dĺžka rámca prenášaného po médiu - " + (frame.frameLength.get(i) + 4) + " B \n");

            analyze = analyze + frame.frameType.get(i);
            if (frame.frameType.get(i).equals("ETHERNET II\n")) {
                if ((packetList.get(i).substring(24,28)).equals("0800")) {
                    IPAddressComm ip = new IPAddressComm(packetList.get(i).substring(52,60), frame.frameLength.get(i));
                } else if ((packetList.get(i).substring(24,28)).equals("0806")) {
                    IPAddressComm ip = new IPAddressComm(packetList.get(i).substring(56,64), frame.frameLength.get(i));
                }
            }
            analyze = analyze + ("Zdrojova mac adresa:" + (s.substring(17,23)) + (s.substring(26,38)) + "\n");
            analyze = analyze + ("Cielova mac adresa: " + s.substring(0,17) + "\n");
            analyze = analyze + s + "\n\n";
            finalPacketList.add(analyze);
            i++;

        }

        if (type ==1) {
            i = 0;
            int max = 0;
            analyze = analyze + "IP adresy vysielajúcich uzlov:\n";
            for (String s : IPAddressComm.ipList) {
                analyze = analyze + s + "\n";
            }
            analyze = analyze + ("Adresa uzla s najväčším počtom odvysielaných bajtov: \n");
            String tmp = "";
            for (String s : IPAddressComm.ipList) {
                if (max < IPAddressComm.iplength.get(i)) {
                    tmp = "";
                    tmp = tmp + s + "\t\t" + IPAddressComm.iplength.get(i) + " Bajtov";
                }
                i++;
            }
            analyze = analyze + tmp;
            finalPacketList.add(analyze);
        }


    }

}
