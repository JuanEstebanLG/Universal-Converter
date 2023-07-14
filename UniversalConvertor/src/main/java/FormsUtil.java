import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FormsUtil extends ChangeForm {

    /**
     * Esta clase tiene como función contener todas las operaciones que permiten
     * el correcto funcionamiento a la hora de crear todos los GUI.
     * Aca se almacenan métodos, tanto para agilizar la creación de paneles y la estilación de componentes
     * como para filtrar caracteres en los inputs.
     *
     * */

    private static boolean showMenu = false;

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


    public static void makePanel(JFrame jFrame, JPanel panel, int positionX, int positionY, int width, int height, Color color){
        panel.setBounds(positionX, positionY, width, height);
        panel.setBackground(color);
        panel.setLayout(null);
        jFrame.add(panel);

    }


    public boolean menuBar(boolean state, JFrame frame ,JPanel menu, JPanel mainView, int x, int y){
        if(!state){
            JButton changeToTemp = new JButton("Temperaturas");
            JButton changeToLY = new JButton("Distancias");
            JButton changeToCoin = new JButton("Divisas");


            mainView.setBounds(100, 0, x - 100, y);
            makePanel(frame, menu, 0, 0, 100, y, Color.orange);

            menu.add(changeToTemp);
            menu.add(changeToLY);

            menu.add(changeToCoin);

            setButtonStyle(changeToCoin, 0, 80, 100, 30);
            setButtonStyle(changeToTemp, 0, 125,100, 30);
            setButtonStyle(changeToLY, 0, 165,100, 30);

            changeToCoin.addActionListener(e -> changeState(frame, menu, mainView, 0));

            changeToTemp.addActionListener(e -> changeState(frame, menu, mainView, 1));

            changeToLY.addActionListener(e -> changeState(frame, menu, mainView, 2));

            state = true;
        }else {
            frame.remove(menu);
            mainView.setBounds(0,0,x,y);
            state = false;
        }
        return state;
    }

    public double sendPeticion(String type, String coeficiente, String unitFrom, String unitTo) {
        /**
         * Este método es el encargado de hacer la conversión, tanto para Temperatura como para Años luz
         * para ello recibirá un tipo (String), este tipo define que tipo de conversión se debe de hacer
         *
         * */
        double inputUser = Double.parseDouble(coeficiente);
        String conversionKey = unitFrom.toLowerCase() + "_" +  unitTo.toLowerCase();
        try{

        if(type.equals("temperatura")){

            Map<String, Double> conversionFactors = new HashMap<>();
            conversionFactors.put("celsius_fahrenheit", 9.0/5.0);
            conversionFactors.put("celsius_kelvin", 1.0);
            conversionFactors.put("fahrenheit_celsius", 5.0/9.0);
            conversionFactors.put("fahrenheit_kelvin", 5.0/9.0);
            conversionFactors.put("kelvin_celsius", 1.0);
            conversionFactors.put("kelvin_fahrenheit", 9.0 / 5.0);

            if(conversionFactors.containsKey(conversionKey)){
                double factorDeConversion = conversionFactors.get(conversionKey);
                return (inputUser * factorDeConversion ) + getOffset(unitFrom, unitTo);
            }

        }else if(type.equals("distancias")){
            Map<String, Double> conversionFactors = new HashMap<>();
            conversionFactors.put("millas_kilometro", 1.60934);
            conversionFactors.put("millas_metro", 1609.34);
            conversionFactors.put("kilometro_millas", 0.621371);
            conversionFactors.put("kilometro_metro", 1000.0);
            conversionFactors.put("metro_millas", 0.000621371);
            conversionFactors.put("metro_kilometro", 0.001);

            if(conversionFactors.containsKey(conversionKey)){
                double factorDeConversion = conversionFactors.get(conversionKey);
                return inputUser * factorDeConversion;
            }
        }
        return  inputUser;
    }catch (IllegalArgumentException err){
        System.out.println(err.getMessage() + " " + err.getCause());
         err.printStackTrace();
    }

        return inputUser;
    }

    private static double getOffset(String unitFrom, String unitTo) {
        String unidadOrigen = unitFrom.toLowerCase();
        String unidadDestino = unitTo.toLowerCase();
        if (unidadOrigen.equals("celsius") && unidadDestino.equals("fahrenheit")) {
            return 32.0;
        }else if(unidadOrigen.equals("celsius") && unidadDestino.equals("kelvin")){
            return 273.15;
        } else if (unidadOrigen.equals("fahrenheit") && unidadDestino.equals("celsius")) {
            return -32.0;
        } else if (unidadOrigen.equals("fahrenheit") && unidadDestino.equals("kelvin")) {
            return 459.67;
        } else if (unidadOrigen.equals("kelvin") && unidadDestino.equals("fahrenheit")) {
            return -459.67;
        }else if(unidadOrigen.equals("kelvin") && unidadDestino.equals("celsius")){
            return -273.15;
        } else {
            return 0.0;
        }
    }






    public void setStyleComboBox(JComboBox comboBox){
        comboBox.setBackground(Color.decode("#3b3e46"));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
    }

    public void setButtonStyle(JButton button, int x, int y, int w, int h){

        button.setBounds(x,y,w,h);
        button.setBackground(Color.ORANGE);
        button.setForeground(Color.decode("#454C70"));
        button.setBorder(null);
    }

    public void MakeGUIS(JFrame frame,JPanel panelPrincipal, Color color, String[] Origen, String[] Destino){

        int x = 800;
        int y = 500;

        frame.setSize(x,y);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

        makePanel(frame, panelPrincipal, 0,0, x, y, color);

        JComboBox<String> unidadOrigen = new JComboBox<>(Origen);
        JComboBox<String> unidadDestino = new JComboBox<>(Destino);

        JTextField inputUser = new JTextField();
        JButton menu_button = new JButton("Menu");
        JButton send_button = new JButton("Transformar");

        JPanel menuPrincipal = new JPanel();

        setStyleComboBox(unidadOrigen);
        setStyleComboBox(unidadDestino);

        unidadOrigen.setBounds(10, 100, 150, 25);
        unidadDestino.setBounds(640, 100, 150, 25);

        inputUser.setBounds((x/2) - 100, y/2, 200, 25 );
        NumbersOnly(inputUser);

        setButtonStyle(send_button, (x/2) - 75, (y/2) + 20, 150, 30);
        menu_button.setBounds(0,0, 70, 70);
        menu_button.setBackground(Color.darkGray);
        menu_button.setForeground(Color.decode("#EAF58F"));


        menu_button.addActionListener(e -> showMenu = menuBar(showMenu, frame, menuPrincipal, panelPrincipal, x, y));

        send_button.addActionListener(e ->{
            String unidadPrimaria = (String) unidadOrigen.getSelectedItem();
            String unidadSecundaria = (String) unidadDestino.getSelectedItem();
            String  userInput = inputUser.getText();

            String unidadOriginal = unidadPrimaria.toLowerCase();
            String unidadFinal = unidadSecundaria.toLowerCase();

            if(unidadOriginal.equals("kilometro") || unidadOriginal.equals("metro") || unidadOriginal.equals("millas")){
                double respuesta = sendPeticion("distancias", userInput, unidadOriginal, unidadFinal);
                String message = String.format("La conversion de %s %s a %s es, aproximadamente, de: %s %s", userInput, unidadOriginal, unidadFinal, respuesta, unidadFinal );
                JOptionPane.showMessageDialog(frame, message );

            }else{
                double respuesta = sendPeticion("temperatura", userInput, unidadOriginal, unidadFinal);
                String message = String.format("La conversion de %s %s a %s es, aproximadamente, de: %s %s ", userInput, unidadOriginal, unidadFinal, respuesta, unidadFinal );
                JOptionPane.showMessageDialog(frame, message );
            }

        });

        panelPrincipal.add(unidadOrigen);
        panelPrincipal.add(unidadDestino);
        panelPrincipal.add(inputUser);
        panelPrincipal.add(menu_button);
        panelPrincipal.add(send_button);

    }
}
