import javax.swing.*;
import java.awt.*;

public class TemperaturaGUI extends FormsUtil {
    JPanel panelPrincipal = new JPanel();

    String[] UnidadesTemp = {"Celsius", "Kelvin", "Fahrenheit"};

    public TemperaturaGUI(JFrame frame){
        MakeGUIS(frame,panelPrincipal, Color.decode("#454C70"), UnidadesTemp, UnidadesTemp);

    }


}
