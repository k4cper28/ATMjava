package caom.KS.component;

import com.KS.swing.ButtonOutLine;
import net.miginfocom.swing.MigLayout;
import com.KS.swing.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PanelCover extends javax.swing.JPanel {

    private JPanel panel1;

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;
    public PanelCover(){
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();
    }

    private void init(){
        title = new JLabel("Welcome back!");
        title.setFont(new Font("sansserif",1,30));
        title.setForeground(new Color(245,245,245));
        add(title);
        description = new JLabel("To keep connected with us please");
        description.setForeground(new Color(245,245,245));
        add(description);
        description1 = new JLabel("login with your personal info");
        description1.setForeground(new Color(245,245,245));
        add(description1);
        button = new ButtonOutLine();
        button.setForeground(new Color(255,255,255));
        button.setBackground(new Color(255,255,255));
        button.setText("Sing In");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
        });
        add(button,"w 60%, h 8%");
    }

    private void jButtonActionPerformed(java.awt.event.ActionEvent ev){
        event.actionPerformed(ev);
    }



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0, 0, new Color(35, 166, 97), 0, getHeight(), new Color(22, 116, 66));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void addEvent(ActionListener event){
        this.event = event;
    }

    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                title.setText("Welcome!");
                description.setText("Enter your personal details");
                description1.setText("and start journey with us");
                button.setText("SIGN UP");
            } else {
                title.setText("Welcome Back!");
                description.setText("To keep connected with us please");
                description1.setText("login with your personal info");
                button.setText("SIGN IN");
            }
            this.isLogin = login;
        }
    }
}
