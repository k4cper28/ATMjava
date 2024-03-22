package com.KS.main;

import caom.KS.component.PanelCover;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class Main extends javax.swing.JFrame {

    private JPanel bg;
    private MigLayout layout;
    private PanelCover cover;
    private final double addSize = 30;
    private final double coverSize = 40;
    private Boolean isLogin = false;
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));

    public Main() {
        setSize(930, 530);
        setTitle("ATM");
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/img/bank.png").getImage());
        bg = new JPanel();
        add(bg);

        init();
    }


    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                if (isLogin) {
                    fractionCover = 1f - fraction;
                } else {
                    fractionCover = fraction;
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                layout.setComponentConstraints(cover, "width " + coverSize + "%, pos " + fractionCover + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };

        Animator animator = new Animator(1000, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
