package caom.KS.component;

import com.KS.swing.MyPasswordField;
import com.KS.swing.MyTextField;
import com.KS.swing.Button;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class PanelLoginAndRegister extends javax.swing.JPanel {

    private JPanel panel;
    private JPanel login;
    private JPanel register;

    public PanelLoginAndRegister(ActionListener eventRegister) {
        setOpaque(false);
        setLayout(new GridBagLayout()); // Ustawienie układu na GridBagLayout dla panelu głównego
        initLogin();
        initRegister(eventRegister);
        login.setVisible(false);
        register.setVisible(true);
    }


    private void initRegister(ActionListener eventRegister) {
        register = new JPanel(new MigLayout("wrap", "push[center]push", "push[]40[]15[]15[]15[]55[]40[]push")); // Pusta kolumna i wiersze, aby odpowiednio rozmieścić komponenty
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sanserif", Font.BOLD, 36));
        label.setForeground(new Color(7, 164, 121));
        register.add(label, "wrap"); // Umieść etykietę i przejdź do nowego wiersza


        //name
        MyTextField txtUser = new MyTextField();
        txtUser.setHint("Name");
        Icon icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\user.png");
        txtUser.setPrefixIcon(icon);
        register.add(txtUser, "width label:100%, wrap");
        //lastnameQ
        MyTextField txtLastName = new MyTextField();
        txtLastName.setHint("LastName");
        txtLastName.setPrefixIcon(icon);
        register.add(txtLastName, "width label:100%, wrap");
        //email
        MyTextField txtEmail = new MyTextField();
        icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\mail.png");
        txtEmail.setPrefixIcon(icon);
        txtEmail.setHint("Email");
        register.add(txtEmail, "width label:100%, wrap");
        //passwd
        MyPasswordField txtPassword = new MyPasswordField();
        txtPassword.setHint("Password");
        icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\key.png");
        txtPassword.setPrefixIcon(icon);
        register.add(txtPassword, "width label:100%, wrap");
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250,250,250));
        cmd.addActionListener(eventRegister);
        cmd.setText("SING UP");
        register.add(cmd,"w 60%, h 60%");


        // Dodaj panel register do panelu głównego z użyciem GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Ustawienie pozycji X na 0
        gbc.gridy = 0; // Ustawienie pozycji Y na 0
        gbc.weightx = 1.0; // Elastyczność w poziomie
        gbc.weighty = 1.0; // Elastyczność w pionie
        gbc.fill = GridBagConstraints.CENTER; // Wyrównanie do środka
        add(register, gbc);
    }

    private void initLogin(){
        login = new JPanel(new MigLayout("wrap", "push[center]push", "push[]40[]15[]15[]15[]15[]40[]push"));
        JLabel label = new JLabel("Sing In");
        label.setFont(new Font("sanserif", Font.BOLD, 52));
        label.setForeground(new Color(7, 164, 121));
        login.add(label, "wrap"); // Umieść etykietę i przejdź do nowego wiersza



        //email
        MyTextField txtEmail = new MyTextField();
        Icon icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\mail.png");
        txtEmail.setPrefixIcon(icon);
        txtEmail.setHint("Email");
        login.add(txtEmail, "width label:100%, wrap");
        //passwd
        MyPasswordField txtPassword = new MyPasswordField();
        txtPassword.setHint("Password");
        icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\key.png");
        txtPassword.setPrefixIcon(icon);
        login.add(txtPassword, "width label:100%, wrap");
        //forgot passwd
        JButton cmdForget = new JButton("Forgot your password?");
        cmdForget.setForeground(new Color(100,100,100));
        cmdForget.setFont(new Font("sansserif",1,12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdForget.setBorderPainted(false);
        login.add(cmdForget,"width label:100%, wrap");
        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("SING UP");
        login.add(cmd,"w 60%, h 60%");


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        add(login, gbc);
    }

    public void showRegister(boolean show){
        if(show){
            register.setVisible(true);
            login.setVisible(false);
        }else{
            register.setVisible(false);
            login.setVisible(true);
        }
    }
}
