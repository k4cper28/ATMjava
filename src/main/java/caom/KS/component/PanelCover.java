package caom.KS.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCover extends javax.swing.JPanel {

    private JButton button1;
    private JPanel panel1;

    private ActionListener event;

    public PanelCover(){
        setOpaque(false);
        button1 = new JButton("Button1");
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
        });
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
}
