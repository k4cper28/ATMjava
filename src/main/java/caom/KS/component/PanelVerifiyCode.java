package caom.KS.component;

import com.KS.swing.MyTextField;
import com.KS.swing.PanelRound;
import javafx.scene.layout.Pane;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class PanelVerifiyCode extends JPanel {

    private PanelRound pd;
    private JPanel panel1;
    private JLabel label;
    private JLabel label2;

    public PanelVerifiyCode(){
        setOpaque(false);
        setFocusable(true);
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

//        pd = new PanelRound();
//        pd.setSize(400,500);
//        add(pd);
//        label = new JLabel("Verify Code");
//        label.setFont(new Font("sanserif",Font.BOLD,36));
//        label.setForeground(Color.BLACK);
//        pd.add(label, "wrap");
//
//        label2 = new JLabel("Check your maul to get verify code");
//        label2.setFont(new Font("sanserif",Font.BOLD,16));
//        label2.setForeground(Color.BLACK);
//        pd.add(label2, "wrap");

        pd = new PanelRound();
        pd.setLayout(new BoxLayout(pd, BoxLayout.Y_AXIS)); // Ustawienie BoxLayout
        pd.setSize(400,500); // Ustawienie preferowanego rozmiaru pd

        add(pd);

        pd.add(Box.createVerticalStrut(40)); // 10 pikseli odstępu


        label = new JLabel("Verify Code");
        label.setFont(new Font("sanserif", Font.BOLD, 36));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie etykiety
        pd.add(label); // Dodanie etykiety do pd


        label2 = new JLabel("Check your mail to get verify code");
        label2.setFont(new Font("sanserif", Font.BOLD, 16));
        label2.setForeground(Color.BLACK);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie etykiety
        Border padding = BorderFactory.createEmptyBorder(30, 30, 60, 60);
        label2.setBorder(padding);
        pd.add(label2); // Dodanie etykiety do pd

        MyTextField txtCode = new MyTextField();
        txtCode.setHint("Code");
        pd.add(txtCode);

        JLabel label3 = new JLabel("Check your mail to get verify code");
        label3.setFont(new Font("sanserif", Font.BOLD, 16));
        label3.setForeground(Color.BLACK);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT); // Wyśrodkowanie etykiety
        padding = BorderFactory.createEmptyBorder(30, 30, 60, 60);
        label3.setBorder(padding);
        pd.add(label3); // Dodanie etykiety do pd



    }


    @Override
    protected void paintComponent(Graphics grpgcs){
        Graphics2D g2 = (Graphics2D) grpgcs;
        g2.setColor(new Color(50,50,50));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(grpgcs);
    }
}
