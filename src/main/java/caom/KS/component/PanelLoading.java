package caom.KS.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelLoading extends javax.swing.JPanel {

    private JLabel gif;
    private JPanel panel;

    public PanelLoading(){
        setOpaque(false);
        setFocusCycleRoot(true);
        setVisible(false);

        addMouseListener(new MouseAdapter() {

        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ustawienie domyślnych ograniczeń dla GridBagConstraints
        gbc.gridx = 0; // Pozycja X komponentu (ale w GridBagLayout to mniej ważne, kiedy chcemy wyśrodkować)
        gbc.gridy = 0; // Pozycja Y komponentu
        gbc.weightx = 1; // Jak bardzo komponent ma się rozciągać w poziomie
        gbc.weighty = 1; // Jak bardzo komponent ma się rozciągać w pionie
        gbc.fill = GridBagConstraints.BOTH; // Jak komponent ma się rozciągać, tutaj w obie strony
        gbc.anchor = GridBagConstraints.CENTER; // Gdzie komponent ma być umieszczony, gdy nie wypełnia całej przestrzeni komórki

        gif = new JLabel();
        ImageIcon icon = new ImageIcon("src\\main\\java\\com\\KS\\img\\loading.gif");
        gif = new JLabel(icon);
        gif.setHorizontalAlignment(JLabel.CENTER);
        add(gif);
    }

    protected void paintComponent(Graphics grpgcs){
        Graphics2D g2 = (Graphics2D) grpgcs;
        g2.setColor(new Color(255,255,255));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(grpgcs);
    }

}
