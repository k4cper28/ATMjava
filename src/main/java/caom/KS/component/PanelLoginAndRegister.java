package caom.KS.component;

import com.KS.swing.MyPasswordField;
import com.KS.swing.MyTextField;
import com.KS.swing.Button;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
public class PanelLoginAndRegister extends javax.swing.JPanel {

    private JPanel panel;
    private JPanel login;
    private JPanel register;

    public PanelLoginAndRegister() {
        setOpaque(false);
        setLayout(new GridBagLayout()); // Ustawienie układu na GridBagLayout dla panelu głównego
        initLogin();
        initRegister();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register = new JPanel(new MigLayout("wrap", "push[center]push", "push[]40[]15[]15[]15[]15[]40[]push")); // Pusta kolumna i wiersze, aby odpowiednio rozmieścić komponenty
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
        //username
        MyTextField txtUsername = new MyTextField();
        txtUsername.setPrefixIcon(icon);
        txtUsername.setHint("username");
        register.add(txtUsername, "width label:100%, wrap");
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
        login = new JPanel();
        // Dodaj panel login do panelu głównego z użyciem GridBagConstraints, jeśli jest wymagane
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        add(login, gbc);
    }
}