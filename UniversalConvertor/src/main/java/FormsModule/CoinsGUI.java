package FormsModule;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import FormsTools.FormsUtil;
import ApiModule.ApiClass;
import ExceptionsModule.InvalidCharacterException;
/**
 * Esta clase tiene la capacidad y uso de crear el formulario perteneciente a las divisas
 * CoinsGUI también es el formulario más complejo y diferente de sus hermanos, esto se debe a su uso de Apis y otros métodos
 *
 * Para poder hacer uso de las apis, esta clase hace uso de la clase ApiClass.
 *
 * @see ApiClass
 */

public class CoinsGUI extends FormsUtil {
    /*
        Se procede con la creación de los componentes del formulario
     */
    private final String location = "C:/Users/123/Desktop/Escritorio/Universal-Convertor/UniversalConvertor/src/main/resources/NewBackGround.png";
    private final JFrame frame = new JFrame();
    private final JPanel panelPrincipal = new JPanel();
    private final JPanel menuPrincipal = new JPanel();
    private final ImageIcon icon = new ImageIcon(location);
    private final JLabel labelBackground = new JLabel(icon);
    private final ApiClass apiFetch = new ApiClass();
    private static boolean showMenu = false;


    /**
     * Este es el constructor de la clase, encargado de la creación del formulario, para ello hace uso de diferentes métodos ubicados
     * en otras clases.
     * @param DIVISAS_ORIGEN lista de divisas origen.
     * @param DIVISAS_DESTINO lista de divisas destino.
     *
     * @see FormsUtil#setButtonStyle(JButton, int, int, int, int, Color)
     * @see FormsUtil#setStyleComboBox(JComboBox)
     * @see FormsUtil#NumbersOnly(JTextField)
     * @see FormsUtil#menuBar(boolean, JFrame, JPanel, JPanel, JComboBox, JComboBox, int, int)
     *
     */
    public CoinsGUI(String[] DIVISAS_ORIGEN, String[] DIVISAS_DESTINO){
        //Creación de las listas, el input y los botones.
        JComboBox<String> divisa_origen = new JComboBox<>(DIVISAS_ORIGEN);
        JComboBox<String> divisa_destino = new JComboBox<>(DIVISAS_DESTINO);
        JTextField input_Usuario = new JTextField();
        JButton send_button = new JButton("Convertir");
        JButton menu_button = new JButton("Menu");

        //Estableciendo la configuración del frame
        frame.setVisible(true);
        int x = 800;
        int y = 500;
        frame.setSize(x, y);
        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Estableciendo la configuración del panel

        FormsUtil.makePanel(frame, panelPrincipal, 0, 0, x, y, Color.WHITE);
        panelPrincipal.add(divisa_origen);
        panelPrincipal.add(divisa_destino);
        panelPrincipal.add(input_Usuario);
        panelPrincipal.add(send_button);
        panelPrincipal.add(menu_button);
        panelPrincipal.add(labelBackground);

        //Estableciendo la configuración de los componentes del panel.

        divisa_origen.setBounds(100, 100, 150, 25);
        divisa_destino.setBounds(550, 100, 150, 25);
        input_Usuario.setBounds((x/2) - 150, 200, 300, 25);
        input_Usuario.setBackground(Color.LIGHT_GRAY);
        send_button.setBounds((x/2) - 75, 300, 150, 40 );
        send_button.setBackground(Color.WHITE);
        menu_button.setBounds(0,0, 70, 70);
        menu_button.setBackground(Color.WHITE);
        menu_button.setForeground(Color.decode("#FFF"));
        menu_button.setBorder(null);
        // Filtrando el input del usuario
        NumbersOnly(input_Usuario);

        setStyleComboBox(divisa_origen);
        setStyleComboBox(divisa_destino);

        //Añadiendo las respectivas acciones a los botones.
        send_button.addActionListener(e -> {
            String firstCoin = (String) divisa_origen.getSelectedItem();
            String secondCoin = (String) divisa_destino.getSelectedItem();
            String text = input_Usuario.getText();
            sendApiRequest(text, firstCoin, secondCoin);
        });

        menu_button.addActionListener(e -> {
            showMenu = menuBar(showMenu, frame, menuPrincipal, panelPrincipal,divisa_origen,divisa_destino, x, y);
        });

        labelBackground.setBounds((x/2) - 50,20, 100,100);
    }


    /**
     * Este metodo creará una alerta usando JOptionpane.
     * @param result Mensaje principal del mensaje de la alerta, generalmente un resultado aritmético.
     * @param divisaFinal Divisa destino a la cual ha sido convertido el valor del usuario.
     * @param frame Frame donde se origina la alerta.
     */
    public void makeAlert(String result,String divisaFinal, JFrame frame){
        String response = String.format("Recuerde que la conversión puede variar según las fuentes \n La conversion es aproximadamente: %s %s", result, divisaFinal);
        JOptionPane.showMessageDialog(frame, response);
    }


    /**
     * Este método es el encargado de filtrar el input y las elecciones del usuario para enviarlas a la api.
     * La respuesta generada por la api es filtrada acá también, es ademas la responsable de invocar la alerta de respuesta
     * usando makeAlert().
     * Para hacer esto este método hace uso de la clase apiFetch
     *
     * @param text Input del usuario.
     * @param firstCoin Moneda origen seleccionada por el usuario.
     * @param secondCoin Moneda destino seleccionada por el usuario.
     *
     * @see ApiClass#Requestapi(String, String, String)
     * @see #makeAlert(String, String, JFrame)
     */
    public void sendApiRequest(String text, String firstCoin, String secondCoin){
        try {
            // Cambiando el input del usuario de formato textual a formato double.
            double value = Double.parseDouble(text);
            double tasaMoneda = apiFetch.Requestapi("3ddbcda2c715f3d01c3f41a4", firstCoin, secondCoin);
            double getConverstion = value * tasaMoneda;

            //Se usa DecimalFormat para darle formato al double generado por la multiplicación entre value y tasaMoneda.

            DecimalFormat decimalFormat = new DecimalFormat("0.##");
            String result = decimalFormat.format(getConverstion);

            //Sé crea la alerta usando makeAlert()
            makeAlert(result,secondCoin, frame);
        } catch (InvalidCharacterException ex) {
            String message = ex.getMessage();
            String messageAlert = String.format("El carácter ingresado no es un numero o contiene símbolos no permitidos\n $s", message);
            JOptionPane.showMessageDialog(frame, messageAlert);

        }
    }



}

