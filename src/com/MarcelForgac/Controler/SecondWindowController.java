package com.MarcelForgac.Controler;

import com.MarcelForgac.Model.*;
import com.MarcelForgac.Model.PacketAnalyzer;
import com.MarcelForgac.View.SecondWindow;
import org.pcap4j.core.PcapNativeException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by marcelforgac on 4.3.15.
 */
public class SecondWindowController {

    private SecondWindow view;
    private Communication model;

    public SecondWindowController(final SecondWindow view) {

        this.view = view;

        view.textArea1.setText("Click on \"File\" to chose .pcap file to analyze");

        view.fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JFileChooser file = new JFileChooser();
            file.showDialog(null, "Open file");
            String pcap = String.valueOf(file.getSelectedFile());

            view.textArea1.setText("");
                IPAddressComm ip = new IPAddressComm();

            try {
                Communication.communicationAnalyzer(pcap);
                view.textArea1.setText("Choose some type of comunication");
            } catch (PcapNativeException e1) {
                e1.printStackTrace();
            }
            }
        });

        view.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.textArea1.setText("");
                view.textArea1.setText("Click on \"File\" to chose .pcap file to analyze");
            }
        });

        view.comunicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IPAddressComm ip = new IPAddressComm();
                view.textArea1.setText("");
                Communication.analyzer(1);

                for (String s : Communication.finalPacketList){
                    view.textArea1.append(s);
                }
            }
        });


        view.httpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                view.textArea1.setText(PacketAnalyzer.tcp("http"));

            }
        });

        view.arpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                view.textArea1.setText("");
                view.textArea1.setText(PacketAnalyzer.arp());
            }
        });

        view.icmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                view.textArea1.setText(PacketAnalyzer.icmp());
            }
        });

        view.tftpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                for (String s : PacketAnalyzer.tftp()) {
                 view.textArea1.append(s);
                }
            }
        });
        view.telnetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                view.textArea1.setText(PacketAnalyzer.tcp("telnet"));

            }
        });
        view.sshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                view.textArea1.setText(PacketAnalyzer.tcp("ssh"));

            }
        });
        view.ftpDatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                view.textArea1.setText(PacketAnalyzer.tcp("ftp-data"));

            }
        });
        view.ftpRiadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.analyzer(2);
                view.textArea1.setText(PacketAnalyzer.tcp("ftp-control"));

            }
        });
        view.httpsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.telnetButton.setText(PacketAnalyzer.tcp("https"));
            }
        });
    }


}
