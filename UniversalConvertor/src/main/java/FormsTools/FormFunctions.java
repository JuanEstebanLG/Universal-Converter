package FormsTools;


import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import FormsModule.*;
import ExceptionsModule.IncorrectTransformTypeException;

    /**
     * Esta clase almacena los métodos y funciones que se utilizaran para el correcto funcionamiento de
     * todos los formularios y sus peticiones.
     * */
public abstract class FormFunctions {


        /**
         * Cuando ChangeState es llamado, este recibe los datos del formulario desde el cual es llamado.
         * Posteriormente procede a eliminar todos los elementos sobre el frame de ese formulario 
         * y a invocar una nueva instancia según un entero "type".
         * @param frame Frame donde se posicionara el menu.
         * @param menuPrincipal  (JPanel) menu actual en el frame
         * @param panelPrincipal (JPanel) Panel actual en el frame
         * @param type entero decidido automáticamente según la elección de formulario del usuario.
         *             
         */
        
    public void changeState(JFrame frame, JPanel menuPrincipal, JPanel panelPrincipal, int type){
        /*


            Se procede con la eliminación de todos los objetos sobre el formulario.
         */

        frame.remove(menuPrincipal);
        frame.remove(panelPrincipal);

        /*
         * Con el formulario eliminado, ChangeState da paso a la creación y suplantación de un nuevo form
         * utilizando parámetros internos y las clases de los respectivos formularios (Clases GUI)
         * */




        //Forms.CoinsGUI:

        String[] DIVISAS = {
                "COP", "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK",
                "NZD", "MXN", "SGD", "HKD", "NOK", "KRW", "TRY", "RUB", "INR", "BRL", "GBP"
        };



        /*
         * La variable de tipo entero, que contiene el número entre 0 y 2, es empleada
         * para saber a qué tipo de clase se debe hacer la petición.
         * */

        switch (type){
            case 0:
                frame.dispose();
                new CoinsGUI(DIVISAS,DIVISAS);
                break;
            case 1:
                new TemperaturaGUI(frame);
                break;

            case 2:
                new DistanceGUI(frame);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

    }

        /**
         * Este método es el encargado de hacer la conversión, tanto para Temperatura como para distancias
         * para ello recibirá un tipo (String), este tipo define que tipo de conversión se debe de hacer.
         * Este método usará el metodo getOffSet() para obtener el valor a sumar para cada conversión.
         * 
         * @see #getOffset(String, String) 
         * @param type  Definirá el tipo de conversión que debe usarse.
         * @param coeficiente  Es un valor ingresado por el usuario.
         * @param  unitFrom Es la unidad de origen.
         * @param  unitTo Es la unidad destinó.
         *
         *
         * */
    public double sendPeticion(String type, String coeficiente, String unitFrom, String unitTo) {

        /*
        Dado que ambos campos de conversión son unidades matemáticas, que pueden o no ser decimales,
        el input del usuario será pasado a tipo double
        */

        double inputUser = Double.parseDouble(coeficiente);

        /*
        Se creara un String que combinara las dos unidades elegidas por el usuario, con un guion como intermediario.
        De esta manera se logrará establecer un patron para todas las posibilidades, que luego usaremos como llave
        en un diccionario
        */

        String conversionKey = unitFrom.toLowerCase() + "_" +  unitTo.toLowerCase();

        try{
        /*Si el tipo es "Temperatura*/
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
                    return Math.round((inputUser * factorDeConversion ) + getOffset(unitFrom, unitTo));
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
        }catch (IncorrectTransformTypeException err){
            System.out.println(err.getMessage() + " " + err.getCause());

        }

        return inputUser;
    }

    /**
     * getOffSet es una función implementada por el método sendPeticion.
     * Esta función tiene como finalidad identificar las unidades requeridas y usadas para una conversión
     * y, tras hacerlo, regresara un valor double, este valor será utilizado por la clase sendPeticion como parte de
     * una operación de suma.
     *
     * @see #sendPeticion(String, String, String, String) 
     *
     * @param unitFrom unidad de origen.
     * @param unitTo unidad destinó.
     * @return un double, implementado por sendPeticion como parte de una suma.
     */

    private static double getOffset(String unitFrom, String unitTo ) {
        /*
        *  Primero, los dos String serán pasados por un TolowerCase()
        * con el objetivo de reducir la probabilidad de un error.
        * */

        String unidadOrigen = unitFrom.toLowerCase();
        String unidadDestino = unitTo.toLowerCase();

        /*
            Se procede con el algoritmo enargado de identificar y regresar el double a partir de las unidades
         */
        if (unidadOrigen.equals("celsius") && unidadDestino.equals("fahrenheit")) {
            return 32.0;
        }else if(unidadOrigen.equals("celsius") && unidadDestino.equals("kelvin")){
            return 273.15;
        } else if (unidadOrigen.equals("fahrenheit") && unidadDestino.equals("celsius")) {
            return -17.78;
        } else if (unidadOrigen.equals("fahrenheit") && unidadDestino.equals("kelvin")) {
            return 255.82;
        } else if (unidadOrigen.equals("kelvin") && unidadDestino.equals("fahrenheit")) {
            return -459.67;
        }else if(unidadOrigen.equals("kelvin") && unidadDestino.equals("celsius")){
            return -273.15;
        } else {
            return 0.0;
        }
    }

    /**
     * sendData se encargará de implementar el método sendPeticion(), para ello recibirá las dos unidades elegidas por el usuario
     * además del input, posteriormente procederá a generar un cuadro de diálogo en el frame actual.
     *
     * @see #sendPeticion(String, String, String, String) 
     * @param unidadOrigen Esta unidad es la unidad a convertir.
     * @param unidadDestino Esta unidad es la unidad destino a la cual se convertirá el input.
     * @param inputUser El input dado por el usuario.
     * @param frame El frame actual donde se generará el JOptionPane.
     *              
     */
    public void sendData(JComboBox unidadOrigen, JComboBox unidadDestino, JTextField inputUser, JFrame frame){
        /*
            Al recibir las dos listas de unidades y el input, se substrae la selección de unidades del usuario, se obtiene
            el texto del input.
         */
        String unidadPrimaria = (String) unidadOrigen.getSelectedItem();
        String unidadSecundaria = (String) unidadDestino.getSelectedItem();
        String  userInput = inputUser.getText();

        /*
            Por precaución y para evitar errores, las dos unidades son pasadas por un toLoweCase()
         */
        String unidadOriginal = unidadPrimaria.toLowerCase();
        String unidadFinal = unidadSecundaria.toLowerCase();

        /*
            Gracias a solo tener dos posibles tipos de unidades, se emplea un if/else, el cual determinara que String type enviar al método 
            sendPeticion()
         */
        if(unidadOriginal.equals("kilometro") || unidadOriginal.equals("metro") || unidadOriginal.equals("millas")){
            double respuesta = sendPeticion("distancias", userInput, unidadOriginal, unidadFinal);
            String message = String.format("La conversion de %s %s a %s es, aproximadamente, de: %s %s", userInput, unidadOriginal, unidadFinal, respuesta, unidadFinal );
            JOptionPane.showMessageDialog(frame, message );

        }else{
            double respuesta = sendPeticion("temperatura", userInput, unidadOriginal, unidadFinal);
            String message = String.format("La conversion de %s %s a %s es, aproximadamente, de: %s %s ", userInput, unidadOriginal, unidadFinal, respuesta, unidadFinal );
            JOptionPane.showMessageDialog(frame, message );
        }

    }
}
