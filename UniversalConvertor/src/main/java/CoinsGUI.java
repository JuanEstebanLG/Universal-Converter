import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CoinsGUI extends  makeForms {

    JFrame frame = new JFrame();
    JPanel panelPrincipal = new JPanel();
    JPanel menuPrincipal = new JPanel();
    ApiClass apiFetch = new ApiClass();

    private static boolean showMenu = false;



    public CoinsGUI(String[] DIVISAS_ORIGEN, String[] DIVISAS_DESTINO){

        JComboBox<String> divisa_origen = new JComboBox<>(DIVISAS_ORIGEN);
        JComboBox<String> divisa_destino = new JComboBox<>(DIVISAS_DESTINO);
        JTextField input_Usuario = new JTextField();

        JButton send_button = new JButton("Convertir");
        JButton menu_button = new JButton("Menu");

        //SET JFRAME SETTINGS
        frame.setVisible(true);
        int x = 800;
        int y = 500;

        frame.setSize(x, y);
        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //SET PRINCIPAL PANEL SETTINGS

        makePanel(frame, panelPrincipal, 0, 0, x, y, Color.darkGray);
        panelPrincipal.add(divisa_origen);
        panelPrincipal.add(divisa_destino);
        panelPrincipal.add(input_Usuario);
        panelPrincipal.add(send_button);
        panelPrincipal.add(menu_button);

        //SET COMPONENT SETTINGS

        divisa_origen.setBounds(10, 100, 150, 25);
        divisa_destino.setBounds(540, 100, 150, 25);
        setStyleComboBox(divisa_origen);
        setStyleComboBox(divisa_destino);

        input_Usuario.setBounds(200, 200, 300, 25);
        input_Usuario.setBackground(Color.LIGHT_GRAY);
        send_button.setBounds(275, 300, 150, 40 );
        send_button.setBackground(Color.WHITE);

        menu_button.setBounds(0,0, 70, 70);
        menu_button.setBackground(Color.darkGray);
        menu_button.setForeground(Color.decode("#EAF58F"));

        send_button.addActionListener(e -> {
            String firstCoin = (String) divisa_origen.getSelectedItem();
            String secondCoin = (String) divisa_destino.getSelectedItem();
            String text = input_Usuario.getText();
            sendApiRequest(text, firstCoin, secondCoin);

        });

        menu_button.addActionListener(e -> {
            showMenu = menuBar(showMenu, frame, menuPrincipal, panelPrincipal, x, y);
        });

        NumbersOnly(input_Usuario);
    }

    public void NumbersOnly(JTextField textField){
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        DocumentFilter documentFilter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                if (text.matches("[0-9.]+")) {
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[0-9.]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        document.setDocumentFilter(documentFilter);
    }

    public void makeAlert(String result,String divisaFinal, JFrame frame){
        String response = String.format("Recuerde que la conversión puede variar según las fuentes \n La conversion es aproximadamente: %s %s", result, divisaFinal);
        JOptionPane.showMessageDialog(frame, response);
    }

    public boolean menuBar(boolean state, JFrame frame ,JPanel menu, JPanel mainView, int x, int y){
        if(!state){
            JButton changeToTemp = new JButton("Temperatura");



            mainView.setBounds(100, 0, x - 100, y);
            makePanel(frame, menu, 0, 0, 100, y, Color.orange);

            menu.add(changeToTemp);
            changeToTemp.setBounds(0, 225, 100, 30);
            changeToTemp.setBackground(Color.ORANGE);
            changeToTemp.setForeground(Color.decode("#454C70"));
            changeToTemp.setBorder(null);

            changeToTemp.addActionListener(e -> {
                changeState();
            });

            state = true;
        }else {
            frame.remove(menu);
            mainView.setBounds(0,0,x,y);
            state = false;
        }
        return state;
    }

    public void sendApiRequest(String text, String firstCoin, String secondCoin){
        try {
            // change the text to a double value
            double value = Double.parseDouble(text);
            double tasaMoneda = apiFetch.Requestapi("3ddbcda2c715f3d01c3f41a4", firstCoin, secondCoin);
            double getConverstion = value * tasaMoneda;
            double getConversionRound = Math.round(getConverstion);

            DecimalFormat decimalFormat = new DecimalFormat("0.#####");
            String result = decimalFormat.format(getConversionRound);
            makeAlert(result,secondCoin, frame);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid double value.");
        }
    }

    public void changeState(){
        new TemperaturaGUI();
    }

}

