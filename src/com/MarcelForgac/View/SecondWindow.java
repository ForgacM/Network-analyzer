package com.MarcelForgac.View;

import org.pcap4j.core.PcapNativeException;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marcelforgac on 4.3.15.
 */
public class SecondWindow extends JFrame {

    private JPanel panel2;
    public JButton clearButton;
    public JButton fileButton;
    public JScrollPane jScrollPane;
    public JTextArea textArea1;
    public JButton arpButton;
    public JButton tftpButton;
    public JButton ftpDatButton;
    public JButton ftpRiadButton;
    public JButton sshButton;
    public JButton telnetButton;
    public JButton httpsButton;
    public JButton httpButton;
    public JButton icmpButton;
    public JButton comunicationButton;

    public SecondWindow() {

        super("Zadanie_1");
        textArea1.setEditable(false);
        setContentPane(panel2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setMinimumSize(new Dimension(800,400));
        setLocationRelativeTo(null);
    }
}
