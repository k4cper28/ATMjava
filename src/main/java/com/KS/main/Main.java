package com.KS.main;

import caom.KS.component.*;
import com.KS.model.ModelLogin;
import com.KS.model.ModleUser;
import com.KS.service.ServiceUser;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class Main extends JFrame {

    private JPanel bg;
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoading loading;
    private PanelVerifiyCode verifyCode;
    private PanelLoginAndRegister loginAndRegister;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private Boolean isLogin = false;
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));

    private ServiceUser service;

    public Main() {
        
        setSize(930, 530);
        setTitle("ATM");
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/img/bank.png").getImage());
        //setUndecorated(true);
        bg = new JPanel();
        add(bg);

        init();
    }


    private void init() {
        service = new ServiceUser();
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        verifyCode = new PanelVerifiyCode();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        };

        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        };
        loginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin);

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size  = coverSize;
                if(fraction <= 0.5f){
                    size += fraction * addSize;
                }else{
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if(fraction>=0.5f){
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
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

//        bg.add(loading, "pos 0 0 100% 100%");
//        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%");
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }


    private void  register(){
        ModleUser user = loginAndRegister.getUser();

        // Walidacja danych użytkownika
        if (!isValidUser(user)) {
            showMessage(Message.MessageType.ERROR, "Invalid user data!");
            return;
        }

        try{
            if (service.checkDuplicateUser(user.getEmail())){
                showMessage(Message.MessageType.ERROR,"The user already exists!");
            }else{
                service.insertUser(user);
                showMessage(Message.MessageType.SUCCESS,"You have been registered!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //loading.setVisible(true);
        //verifyCode.setVisible(true);
    }

//    private void login(){
//        ModelLogin data = loginAndRegister.getDataLogin();
//        //System.out.println("email " + data.getEmail() + " pass " + data.getPassword());
//        ServiceUser serviceUser = new ServiceUser();
//
//        try{
//            ModleUser user = serviceUser.login(data);
//            if(user != null){
//
//            }else {
//                showMessage(Message.MessageType.ERROR,"Email and password incorrect!");
//            }
//
//        }catch (SQLException e){
//            showMessage(Message.MessageType.ERROR,"Login error");
//        }
//    }

    private void login() {
        ModelLogin data = loginAndRegister.getDataLogin();
        try {
            ModleUser user = service.login(data);
            if (user != null) {
                this.dispose();
                MainSystem.main(user);
            } else {
                showMessage(Message.MessageType.ERROR, "Email and Password incorrect");
            }

        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Login");
            e.printStackTrace();
        }
    }

    private boolean isValidUser(ModleUser user) {
        // Sprawdzenie poprawności danych użytkownika
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty() || !isValidEmail(user.getEmail())) {
            return false;
        }
        // Tutaj można dodać inne warunki walidacji, np. sprawdzenie hasła itp.
        return true;
    }

    private boolean isValidEmail(String email) {
        // Prosta walidacja adresu e-mail, można zastosować bardziej zaawansowane metody
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    public static void main(String[] args) {

        try {
            Connector.getInstance().connectTODatavase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

}
