package com.KS.main;

import caom.KS.component.Message;
import com.KS.model.ModleUser;
import com.KS.service.ServiceUser;
import com.KS.swing.MyTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainSystem extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ModleUser user;

    private MigLayout layout;
    private ServiceUser service;

    public MainSystem(ModleUser user) {
        service = new ServiceUser();
        this.user = user;
        setTitle("ATM");
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/img/bank.png").getImage());
        layout = new MigLayout("fill, insets 0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(930, 530);
        setResizable(false);
        setBackground(new Color(255, 228, 178));

        // Inicjalizacja CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Dodajemy panele do cardPanel
        cardPanel.add(createMainPanel(), "mainPanel");
        cardPanel.add(createWithdrawPanel(), "withdrawPanel");

        // Ustawiamy cardPanel jako główny panel ramki
        setContentPane(cardPanel);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2", "push[center]push", "push[]10[]80[]20[]20[]20[]push"));

        JLabel tytul = new JLabel("HELLO! " + user.getName() + " " + user.getLastname());
        tytul.setFont(new Font("sanserif", Font.BOLD, 36));
        tytul.setForeground(new Color(7, 164, 121));
        mainPanel.add(tytul, "span 2, align center, wrap");

        JLabel podTytul = new JLabel("Select one of the options:");
        podTytul.setFont(new Font("sanserif", Font.BOLD, 18));
        podTytul.setForeground(new Color(7, 164, 121));
        mainPanel.add(podTytul, "span 2, align center, wrap");

        // Dodaj przyciski do panelu głównego
        // Przycisk Withdraw
        com.KS.swing.Button cmd1 = new com.KS.swing.Button();
        cmd1.setBackground(new Color(7, 164, 121));
        cmd1.setForeground(new Color(250,250,250));
        cmd1.setText("Withdraw");
        cmd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToWithdrawPanel();
            }
        });
        mainPanel.add(cmd1, "w 40%, h 10%");

        // Pozostałe przyciski SING UP (do dodania)

        com.KS.swing.Button cmd2 = new com.KS.swing.Button();
        cmd2.setBackground(new Color(7, 164, 121));
        cmd2.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd2.setText("SING UP");
        mainPanel.add(cmd2,"w 40%, h 10%, wrap");

        return mainPanel;
    }

    private JPanel createWithdrawPanel() {
        JPanel withdrawPanel = new JPanel(new MigLayout("wrap 2", "push[center]push", "push[]80[]50[]push"));



        JLabel label = new JLabel("Please enter the amount to be withdraw:");
        label.setFont(new Font("sanserif", Font.BOLD, 32));
        label.setForeground(new Color(7, 164, 121));
        withdrawPanel.add(label, "span 2 , align center, wrap");

        MyTextField txtWithdraw = new MyTextField();
        txtWithdraw.setHint("Price");
        Icon icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\user.png");
        txtWithdraw.setPrefixIcon(icon);
        withdrawPanel.add(txtWithdraw, "span 2, width label:30%, wrap");

        com.KS.swing.Button cmd1 = new com.KS.swing.Button();
        cmd1.setBackground(new Color(7, 164, 121));
        cmd1.setForeground(new Color(250,250,250));
        cmd1.setText("Withdraw");
        cmd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                double withdrawPrice = Double.parseDouble(txtWithdraw.getText());

                if(withdrawPrice > user.getBalance()){
                    JOptionPane.showMessageDialog(null, "funds unavailable!");
                }else{

                    user.setBalance(user.getBalance() - withdrawPrice);
                    try {
                        service.updateBalance(user);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    JOptionPane.showMessageDialog(null, "Take yout money!");
                }
            }
        });
        withdrawPanel.add(cmd1, "w 40%, h 10%");

        com.KS.swing.Button cmd2 = new com.KS.swing.Button();
        cmd2.setBackground(new Color(7, 164, 121));
        cmd2.setForeground(new Color(250,250,250));
        //cmd.addActionListener(eventRegister);
        cmd2.setText("Back");
        cmd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToMainPanel();
            }
        });
        withdrawPanel.add(cmd2,"w 40%, h 10%, wrap");


        return withdrawPanel;
    }

    public void switchToWithdrawPanel() {
        cardLayout.show(cardPanel, "withdrawPanel");
    }

    public void switchToMainPanel() {
        cardLayout.show(cardPanel, "mainPanel");
    }

    // Reszta kodu

    public static void main(ModleUser user) {
        java.awt.EventQueue.invokeLater(() -> new MainSystem(user).setVisible(true));
    }
}
