package com.KS.main;

import com.KS.model.ModleUser;
import com.KS.swing.Button;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MainSystem extends JFrame {

    private ModleUser user;
    private JPanel MainPanel;
    private JLabel Tytul;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JLabel PodTytul;
    private JPanel Maingora;
    private JPanel MainDol;

    public MainSystem(ModleUser user){
//        setContentPane(MainPanel);
//        setTitle("ATM");
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(930,530);
//        setVisible(true);
//        setIconImage(new ImageIcon("src/img/bank.png").getImage());
//        this.user = user;
//        getContentPane().setBackground(new Color(255,255,255));
//
//        MainPanel.setBackground(new Color(7, 164, 121));
//        MainDol.setBackground(new Color(7, 164, 121));
//        Maingora.setBackground(new Color(7, 164, 121));
//
//        Tytul.setText("HELLO! " + user.getName() + " " + user.getLastname());
//        Tytul.setFont(new Font("sanserif",Font.BOLD,52));
//        Tytul.setForeground(Color.WHITE);
//        PodTytul.setText("Select one of the options:");
//        PodTytul.setFont(new Font("sanserif",Font.BOLD,28));
//        PodTytul.setForeground(Color.WHITE);
//
//        button1.setText("Withdraw");
//        button1.setPreferredSize(new Dimension(50, 50));
//        button2.setText("Quick Withdraw");
//        button2.setSize(120,60);
//        button3.setText("Change Pin");
//        button3.setSize(140,70);
//        button4.setText("Balance");
//        button4.setSize(160,80);
//        button5.setText("transfer");
//        button5.setSize(180,90);
//        button6.setText("Data Change");
//        button6.setSize(200,100);
//
//        setLocationRelativeTo(null);

        setTitle("ATM");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(930,530);
        setIconImage(new ImageIcon("src/img/bank.png").getImage());
        setResizable(false);
        setBackground(new Color (255, 228, 178));
        MainPanel = new JPanel(new MigLayout("wrap 2", "push[center]push", "push[]10[]80[]20[]20[]20[]push"));
        JLabel tytul = new JLabel("HELLO! " + user.getName() + " " + user.getLastname());
        tytul.setFont(new Font("sanserif", Font.BOLD, 36));
        tytul.setForeground(new Color(7, 164, 121));
        MainPanel.add(tytul, "span 2, align center, wrap");

        JLabel podTytul = new JLabel("Select one of the options:");
        podTytul.setFont(new Font("sanserif", Font.BOLD, 18));
        podTytul.setForeground(new Color(7, 164, 121));
        MainPanel.add(podTytul, "span 2, align center, wrap");

        com.KS.swing.Button cmd1 = new Button();
        cmd1.setBackground(new Color(7, 164, 121));
        cmd1.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd1.setText("SING UP");
        MainPanel.add(cmd1,"w 40%, h 10%");

        com.KS.swing.Button cmd2 = new Button();
        cmd2.setBackground(new Color(7, 164, 121));
        cmd2.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd2.setText("SING UP");
        MainPanel.add(cmd2,"w 40%, h 10%, wrap");

        com.KS.swing.Button cmd3 = new Button();
        cmd3.setBackground(new Color(7, 164, 121));
        cmd3.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd3.setText("SING UP");
        MainPanel.add(cmd3,"w 40%, h 10%");

        com.KS.swing.Button cmd4 = new Button();
        cmd4.setBackground(new Color(7, 164, 121));
        cmd4.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd4.setText("SING UP");
        MainPanel.add(cmd4,"w 40%, h 10%, wrap");

        com.KS.swing.Button cmd5 = new Button();
        cmd5.setBackground(new Color(7, 164, 121));
        cmd5.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd5.setText("SING UP");
        MainPanel.add(cmd5,"w 40%, h 10%");

        com.KS.swing.Button cmd6 = new Button();
        cmd6.setBackground(new Color(7, 164, 121));
        cmd6.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd6.setText("SING UP");
        MainPanel.add(cmd6,"w 40%, h 10%, wrap");


        setVisible(true);
        setContentPane(MainPanel);
    }


    public static void main(ModleUser user) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainSystem(user).setVisible(true);
            }
        });
    }

}
