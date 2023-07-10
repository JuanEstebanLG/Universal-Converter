import javax.swing.*;
import java.awt.*;

public class TemperaturaGUI extends makeForms {
    JFrame frame = new JFrame();
    JPanel panelPrincipal = new JPanel();

    public TemperaturaGUI(){
        int x = 800;
        int y = 500;

        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(x,y);
        frame.setResizable(false);
        makePanel(frame, panelPrincipal, 0,0, x, y, Color.decode("#454C70"));

        JComboBox<String> unidadOrigen = new JComboBox<>();
        JComboBox<String> unidadDestino = new JComboBox<>();

        setStyleComboBox(unidadOrigen);
        setStyleComboBox(unidadDestino);
    }
}
