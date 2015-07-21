package Helper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class HelperOrientationDialog extends JDialog implements ActionListener {

    private String message;
    private JRadioButton radioHorizontal;
    private JRadioButton radioVertical;
    private ButtonGroup group;
    private String result;
    private boolean orientation;

    /**
     * Konstuktor
     *
     * @param String message
     */
    public HelperOrientationDialog(String message) {
        super();

        this.message = message;

        //Setzt Titel des Windows
        setTitle("Ausrichtung");
        //Fenster kann nicht skaliert werden
        setResizable(false);
        //Fenster bleibt im Vordergrund
        setModal(true);
        //Setzt Layout
        this.setLayout(new GridLayout(3, 1));

        //Panels
        JPanel windowPanel = new JPanel();
        JPanel messagePanel = new JPanel();
        JPanel radioButtonsPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        //WindowPanel
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));

        // MessagePanel
        JLabel label = new JLabel(this.message);
        add(label);
        windowPanel.add(messagePanel);

        //RadioButtons
        this.radioHorizontal = new JRadioButton("horizontal");
        this.radioVertical = new JRadioButton("vertikal");

        //Gruppe für Radio Buttons
        this.group = new ButtonGroup();
        this.group.add(radioHorizontal);
        this.group.add(radioVertical);

        radioButtonsPanel.add(radioHorizontal);
        radioButtonsPanel.add(radioVertical);
        windowPanel.add(radioButtonsPanel);

        //Button
        JButton button = new JButton("OK");
        buttonPanel.add(button);
        button.addActionListener(this);
        windowPanel.add(buttonPanel);

        this.add(windowPanel);

        //Dialog location
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - getWidth()) / 2;
        final int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //Setzt Fenstergröße automatisch
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (this.radioHorizontal.isSelected()) {
//            System.out.println(e.getActionCommand());
            System.out.println("Das Schiff wurde " + this.radioHorizontal.getText() + " gesetzt!");
            this.result = this.radioHorizontal.getText();
            this.orientation = true;
            setVisible(false);
            dispose();
        } else if (this.radioVertical.isSelected()) {
//            System.out.println(e.getActionCommand());
            System.out.println("Das Schiff wurde " + this.radioVertical.getText() + " gesetzt!");
            this.result = this.radioHorizontal.getText();
            this.orientation = false;
            setVisible(false);
            dispose();
        }
    }

    public String getResult() {
        return this.result;
    }
    
    public boolean getOrientation() {
        return orientation;
    }
    

//    public static void main(String[] args) {
//        HelperOrientationDialog dialog = new HelperOrientationDialog("In welche Richtung willst du schiessen?");
//    }

}
