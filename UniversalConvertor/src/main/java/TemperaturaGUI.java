import javax.swing.*;
import java.awt.*;

public class TemperaturaGUI extends makeForms {



    public TemperaturaGUI(JFrame frame){
        TemperaturaGenerated(frame);
    }

    public void TemperaturaGenerated(JFrame frame){
        int x = 800;
        int y = 500;
        JPanel panelPrincipal = new JPanel();
        frame.setResizable(false);
        makePanel(frame, panelPrincipal, 0,0, x, y, Color.decode("#454C70"));

        JComboBox<String> unidadOrigen = new JComboBox<>();
        JComboBox<String> unidadDestino = new JComboBox<>();

        setStyleComboBox(unidadOrigen);
        setStyleComboBox(unidadDestino);
    }
}
