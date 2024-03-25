package com.KS.main;

import com.KS.model.ModleUser;

import javax.swing.*;
import java.awt.*;

public class MainSystem extends JFrame {

    private ModleUser user;
    public MainSystem(ModleUser user){
        this.user = user;
        getContentPane().setBackground(new Color(255,255,255));
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
