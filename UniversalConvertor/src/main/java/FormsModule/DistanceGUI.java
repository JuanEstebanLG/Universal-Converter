package FormsModule;

import javax.swing.*;
import java.awt.*;
import FormsTools.FormsUtil;

/**
 * Esta clase tiene la capacidad y uso de crear el formulario perteneciente a las unidades de distancia.
 */
public class DistanceGUI extends FormsUtil {
    //Creacion del panel y de la lista empleada.
    JPanel panelPrincipal = new JPanel();
    String [] unidades = {"Millas", "Kilometro", "Metro"};
    final String location = "C:/Users/123/Desktop/Escritorio/Universal-Convertor/UniversalConvertor/src/main/resources/BackGroundDst.png";
    /**
     * Constructor de la clase, creará el formulario usando el método MakeGUIS, de la clase FormsUtil.
     * @param frame Frame sobre el cual se creara el formulario.
     *
     * @see FormsUtil#MakeGUIS(JFrame, JPanel, String, Color, String[], String[])
     */

    public DistanceGUI(JFrame frame){MakeGUIS(frame,panelPrincipal, location, Color.decode("#393B54"), unidades, unidades );}


}
