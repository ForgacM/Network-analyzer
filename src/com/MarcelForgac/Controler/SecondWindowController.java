package com.MarcelForgac.Controler;

import com.MarcelForgac.Model.*;
import com.MarcelForgac.Model.PacketAnalyzer;
import com.MarcelForgac.View.SecondWindow;
import org.pcap4j.core.PcapNativeException;

import javax.swing.*;

/**
 * Created by marcelforgac on 4.3.15.
 */
public class SecondWindowController {

    private SecondWindow view;
    private Communication model;

    public SecondWindowController(final SecondWindow view) {

        this.view = view;

        view.textArea1.setText("Click on \"File\" to chose .pcap file to analyze");

        view.fileButton.addActionListener(e -> {
        JFileChooser file = new JFileChooser();
        file.showDialog(null, "Open file");
        String pcap = String.valueOf(file.getSelectedFile());

        view.textArea1.setText("");
            new IPAddressComm();

        try {
            Communication.communicationAnalyzer(pcap);
            view.textArea1.setText("Choose some type of comunication");
        } catch (PcapNativeException e1) {
            e1.printStackTrace();
        }
        });

        view.clearButton.addActionListener(e -> {
            view.textArea1.setText("");
            view.textArea1.setText("Click on \"File\" to chose .pcap file to analyze");
        });

        view.comunicationButton.addActionListener(e -> {
            new IPAddressComm();
            view.textArea1.setText("");
            Communication.analyzer(1);

            for (String s : Communication.finalPacketList){
                view.textArea1.append(s);
            }
        });


        view.httpButton.addActionListener(e -> {
            Communication.analyzer(2);
            view.textArea1.setText(PacketAnalyzer.tcp("http"));

        });

        view.arpButton.addActionListener(e -> {
            Communication.analyzer(2);
            view.textArea1.setText("");
            view.textArea1.setText(PacketAnalyzer.arp());
        });

        view.icmpButton.addActionListener(e -> {
            Communication.analyzer(2);
            view.textArea1.setText(PacketAnalyzer.icmp());
        });

        view.tftpButton.addActionListener(e -> {
            Communication.analyzer(2);
            for (String s : PacketAnalyzer.tftp()) {
             view.textArea1.append(s);
            }
        });
        view.telnetButton.addActionListener(e -> {
            Communication.analyzer(2);
            view.textArea1.setText(PacketAnalyzer.tcp("telnet"));

        });
        view.sshButton.addActionListener(e -> {
            Communication.analyzer(2);
            view.textArea1.setText(PacketAnalyzer.tcp("ssh"));

        });
        view.ftpDatButton.addActionListener(e -> {
            Communication.analyzer(2);
            view.textArea1.setText(PacketAnalyzer.tcp("ftp-data"));

        });
        view.ftpRiadButton.addActionListener(e -> {
            Communication.analyzer(2);
            view.textArea1.setText(PacketAnalyzer.tcp("ftp-control"));

        });
        view.httpsButton.addActionListener(e -> {
            view.textArea1.setText(PacketAnalyzer.tcp("https"));
        });
    }


}
