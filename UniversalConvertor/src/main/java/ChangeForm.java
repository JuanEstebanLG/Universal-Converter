import javax.swing.*;

public class ChangeForm {
    /**
     * Esta clase almacena los métodos que permiten el cambio entre formularios.
     * */



    public void changeState(JFrame frame, JPanel menuPrincipal, JPanel panelPrincipal, int type){
        /**
         * Cuando ChangeState es llamado, este recibe los datos del formulario desde el cual es llamado
         * además de una variable de tipo int, que contiene un número del 0 al 3.
         * */

        //Se procede con la eliminación de todo el formulario

        frame.remove(menuPrincipal);
        frame.remove(panelPrincipal);

        /**
         * Con el formulario eliminado, ChangeState da paso a la creación y suplantación de un nuevo form
         * utilizando parámetros internos y las clases de los respectivos formularios (Clases GUI)
         * */

        //Parametros internos requeridos por cada formulario


        //CoinsGUI:

        String[] DIVISAS = {
                "COP", "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK",
                "NZD", "MXN", "SGD", "HKD", "NOK", "KRW", "TRY", "RUB", "INR", "BRL", "GBP"
        };

        //TemperaturaGUI:



        //distanceGUI:



        //PesoPorPlanetaGUI:


        /**
         * La variable de tipo entero, que contiene el número entre 0 y 3, es empleada
         * para saber a qué tipo de clase se debe hacer la petición.
         * */

        if(type == 1){
            new TemperaturaGUI(frame);
        }else if(type == 2){
            new distanceGUI(frame);
        }else if (type == 0){
            frame.dispose();
            new CoinsGUI(DIVISAS,DIVISAS);

        }


    }
}
