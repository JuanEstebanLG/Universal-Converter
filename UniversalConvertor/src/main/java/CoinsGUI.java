import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class CoinsGUI extends FormsUtil {
    private String location = "C:/Users/123/Desktop/Escritorio/Universal-Convertor/UniversalConvertor/src/main/resources/NewBackGround.png";
    JFrame frame = new JFrame();
    JPanel panelPrincipal = new JPanel();
    JPanel menuPrincipal = new JPanel();
    ImageIcon icon = new ImageIcon(location);
    JLabel labelBackground = new JLabel(icon);
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

        makePanel(frame, panelPrincipal, 0, 0, x, y, Color.WHITE);
        panelPrincipal.add(divisa_origen);
        panelPrincipal.add(divisa_destino);
        panelPrincipal.add(input_Usuario);
        panelPrincipal.add(send_button);
        panelPrincipal.add(menu_button);
        panelPrincipal.add(labelBackground);

        //SET COMPONENT SETTINGS

        divisa_origen.setBounds(100, 100, 150, 25);
        divisa_destino.setBounds(550, 100, 150, 25);

        setStyleComboBox(divisa_origen);
        setStyleComboBox(divisa_destino);

        input_Usuario.setBounds((x/2) - 150, 200, 300, 25);
        input_Usuario.setBackground(Color.LIGHT_GRAY);

        send_button.setBounds((x/2) - 75, 300, 150, 40 );
        send_button.setBackground(Color.WHITE);

        menu_button.setBounds(0,0, 70, 70);
        menu_button.setBackground(Color.WHITE);
        menu_button.setForeground(Color.decode("#FFF"));
        menu_button.setBorder(null);



        send_button.addActionListener(e -> {
            String firstCoin = (String) divisa_origen.getSelectedItem();
            String secondCoin = (String) divisa_destino.getSelectedItem();
            String text = input_Usuario.getText();
            sendApiRequest(text, firstCoin, secondCoin);
        });

        menu_button.addActionListener(e -> {
            showMenu = menuBar(showMenu, frame, menuPrincipal, panelPrincipal, x, y);
        });

        labelBackground.setBounds((x/2) - 50,20, 100,100);

        NumbersOnly(input_Usuario);
    }



    public void makeAlert(String result,String divisaFinal, JFrame frame){
        String response = String.format("Recuerde que la conversión puede variar según las fuentes \n La conversion es aproximadamente: %s %s", result, divisaFinal);
        JOptionPane.showMessageDialog(frame, response);
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



}

