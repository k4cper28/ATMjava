package caom.KS.component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Message extends javax.swing.JPanel {

    private MessageType messageType = MessageType.SUCCESS;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    private boolean show;
    private JLabel label;
    private JPanel panel;

    public Message(){
        setOpaque(false);
        setVisible(false);

        label = new JLabel();
        label.setFont(new Font("sanserif", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Border padding = BorderFactory.createEmptyBorder(30, 30, 60, 60);
        //label.setBorder(padding);
        add(label);
    }

    public void showMessage(MessageType messageType, String message){
        this.messageType = messageType;
        label.setText(message);
        if(messageType==messageType.SUCCESS){
            Icon icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\success.png");
            label.setIcon(icon);
        }else {
            Icon icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\error.png");
            label.setIcon(icon);
        }
    }

    protected void paintComponent(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs;
        if(messageType == messageType.SUCCESS){
            g2.setColor(new Color(15,174,37));
        }else{
            g2.setColor(new Color(240,52,53));
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(245,245,245));
        g2.drawRect(0,0,getWidth()-1,getHeight());
        super.paintComponent(grphcs);
    }



    public static enum MessageType{
        SUCCESS,ERROR;
    }

}
