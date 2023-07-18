package FormsModule;

import FormsTools.FormsUtil;
import javax.swing.*;
import java.awt.*;


/**
 * Esta clase tiene la capacidad y uso de crear el formulario perteneciente a las unidades de temperatura.
 */
public class TemperaturaGUI extends FormsUtil {
    // Creación del panel y de la lista empleada.
    JPanel panelPrincipal = new JPanel();
    String[] UnidadesTemp = {"Celsius", "Kelvin", "Fahrenheit"};

    /**
     * Constructor de la clase, creará el formulario usando el método MakeGUIS, de la clase FormsUtil.
     * @param frame Frame sobre el cual se creara el formulario.
     *
     * @see FormsUtil#MakeGUIS(JFrame, JPanel, String, Color, String[], String[])
     */
    public TemperaturaGUI(JFrame frame){
        final String location = "C:/Users/123/Desktop/Escritorio/Universal-Convertor/UniversalConvertor/src/main/resources/BackGroundTemp.png";
        MakeGUIS(frame,panelPrincipal, location, Color.decode("#454C70"), UnidadesTemp, UnidadesTemp);

    }


}
