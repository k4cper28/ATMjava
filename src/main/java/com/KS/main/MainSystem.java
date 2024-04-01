package com.KS.main;

import com.KS.model.ModleUser;
import com.KS.service.ServiceUser;
import com.KS.swing.MyTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;

public class MainSystem extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ModleUser user;
    private ServiceUser service;

    private JLabel balanceLabel = new JLabel();

    public MainSystem(ModleUser user) {
        service = new ServiceUser();
        this.user = user;
        setTitle("ATM");
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/img/bank.png").getImage());
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
        cardPanel.add(createBalancePanel(),"balancePanel");
        cardPanel.add(createQuickWithdrawPanel(), "quickWithdrawPanel");


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
        cmd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBalancePanel();
            }
        });
        cmd2.setText("Balance");
        mainPanel.add(cmd2,"w 40%, h 10%, wrap");

        com.KS.swing.Button cmd3 = new com.KS.swing.Button();
        cmd3.setBackground(new Color(7, 164, 121));
        cmd3.setForeground(new Color(250,250,250));
        cmd3.setText("Quick Withdraw");
        cmd3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToQuickWithdrawPAnel();
            }
        });
        mainPanel.add(cmd3, "w 40%, h 10%");

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

                String withdrawText = txtWithdraw.getText();
                if (withdrawText.matches("^\\d+(\\.\\d{1,2})?$")) {
                    BigDecimal withdrawPriceBD = new BigDecimal(txtWithdraw.getText());

                    withdraw(withdrawPriceBD);


                } else {
                    JOptionPane.showMessageDialog(null, "Invalid price format!");
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

    private JPanel createBalancePanel(){
        JPanel balancePanel = new JPanel(new MigLayout("wrap", "push[center]push", "push[]80[]50[]push"));

        JLabel label = new JLabel("This is yout balance:");
        label.setFont(new Font("sanserif", Font.BOLD, 32));
        label.setForeground(new Color(7, 164, 121));
        balancePanel.add(label, "align center, wrap");

        balanceLabel.setFont(new Font("sanserif", Font.BOLD, 26));
        balanceLabel.setForeground(new Color(7, 164, 121));
        balancePanel.add(balanceLabel, "align center, wrap");

        com.KS.swing.Button cmd = new com.KS.swing.Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("Back");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToMainPanel();
            }
        });
        balancePanel.add(cmd,"w 40%, h 10%, wrap");


        return balancePanel;
    }

    private JPanel createQuickWithdrawPanel(){
        JPanel quickWithdrawPanel = new JPanel(new MigLayout("wrap 2", "push[center]push", "push[]80[]40[]40[]push"));

        JLabel label = new JLabel("Please select the amount to be withdraw:");
        label.setFont(new Font("sanserif", Font.BOLD, 32));
        label.setForeground(new Color(7, 164, 121));
        quickWithdrawPanel.add(label, "span 2 , align center, wrap");

        com.KS.swing.Button cmd = new com.KS.swing.Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("50");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(new BigDecimal(50));
            }
        });
        quickWithdrawPanel.add(cmd,"w 30%, h 10%");

        com.KS.swing.Button cmd2 = new com.KS.swing.Button();
        cmd2.setBackground(new Color(7, 164, 121));
        cmd2.setForeground(new Color(250,250,250));
        cmd2.setText("100");
        cmd2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(new BigDecimal(100));
            }
        });
        quickWithdrawPanel.add(cmd2,"w 30%, h 10%, wrap");

        com.KS.swing.Button cmd3 = new com.KS.swing.Button();
        cmd3.setBackground(new Color(7, 164, 121));
        cmd3.setForeground(new Color(250,250,250));
        cmd3.setText("200");
        cmd3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(new BigDecimal(200));
            }
        });
        quickWithdrawPanel.add(cmd3,"w 30%, h 10%");

        com.KS.swing.Button cmd4 = new com.KS.swing.Button();
        cmd4.setBackground(new Color(7, 164, 121));
        cmd4.setForeground(new Color(250,250,250));
        cmd4.setText("500");
        cmd4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(new BigDecimal(500));
            }
        });
        quickWithdrawPanel.add(cmd4,"w 30%, h 10%, wrap");


        com.KS.swing.Button back = new com.KS.swing.Button();
        back.setBackground(new Color(7, 164, 121));
        back.setForeground(new Color(250,250,250));
        back.setText("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToMainPanel();
            }
        });
        quickWithdrawPanel.add(back,"span 2, w 40%, h 10%, wrap");

        return quickWithdrawPanel;
    }

    public void withdraw(BigDecimal withdrawPriceBD){
        try {
            if (withdrawPriceBD.compareTo(user.getBalance()) > 0) {
                JOptionPane.showMessageDialog(null, "funds unavailable!");
            } else {
                BigDecimal balanceUser = user.getBalance();

                BigDecimal resault = balanceUser.subtract(withdrawPriceBD);

                user.setBalance(resault);

                service.updateBalance(user);
                JOptionPane.showMessageDialog(null, "Take yout money!");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void switchToWithdrawPanel() {
        cardLayout.show(cardPanel, "withdrawPanel");
    }

    public void switchToMainPanel() {
        cardLayout.show(cardPanel, "mainPanel");
    }

    public void switchToBalancePanel(){
        updateBalancePanel();
        cardLayout.show(cardPanel,"balancePanel");
    }

    public void switchToQuickWithdrawPAnel(){
        cardLayout.show(cardPanel,"quickWithdrawPanel");
    }
    private void updateBalancePanel(){
        try {
            balanceLabel.setText("balance: " + service.getBalance(user) + " PLN");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(ModleUser user) {
        java.awt.EventQueue.invokeLater(() -> new MainSystem(user).setVisible(true));
    }
}
