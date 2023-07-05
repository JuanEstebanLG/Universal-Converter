import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MakeWindow extends JFrame {
    int x = 800;
    int y = 800;
    JFrame frame = new JFrame();
    JPanel panelPrincipal = new JPanel();





    public MakeWindow(String DIVISAS_ORIGEN[], String DIVISAS_DESTINO[]){
        JComboBox divisa_origen = new JComboBox(DIVISAS_ORIGEN);
        JComboBox divisa_destino = new JComboBox(DIVISAS_DESTINO);
        JTextField input_Usuario = new JTextField();
        JButton send_button = new JButton("Convertir");
        //SET JFRAME SETTINGS
        this.add(panelPrincipal);
        this.setVisible(true);
        this.setSize(x,y);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //SET PRINCIPAL PANEL SETTINGS
        panelPrincipal.setSize(x,y);
        panelPrincipal.setBackground(Color.darkGray);
        panelPrincipal.add(divisa_origen);
        panelPrincipal.add(divisa_destino);
        panelPrincipal.add(input_Usuario);
        panelPrincipal.add(send_button);

        panelPrincipal.setLayout(null);

        //SET COMPONENT SETTINGS

        divisa_origen.setBounds(10, 100, 150, 20);
        divisa_origen.setBackground(Color.WHITE);
        divisa_destino.setBounds(650, 100, 150, 20);
        divisa_origen.setBackground(Color.WHITE);

        input_Usuario.setBounds(250, 200, 300, 20);
        input_Usuario.setBackground(Color.LIGHT_GRAY);
        send_button.setBounds(325, 300, 150, 40 );
        send_button.setBackground(Color.WHITE);


        NumbersOnly(input_Usuario);
    }

    public static void NumbersOnly(JTextField textField){
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        DocumentFilter documentFilter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                if (text.matches("[0-9.,]+")) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[0-9.,]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        document.setDocumentFilter(documentFilter);
    }
}
