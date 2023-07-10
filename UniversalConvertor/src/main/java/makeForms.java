import javax.swing.*;
import java.awt.*;

public class makeForms {



    public static void makePanel(JFrame jFrame, JPanel panel, int positionX, int positionY, int width, int height, Color color){
        panel.setBounds(positionX, positionY, width, height);
        panel.setBackground(color);
        panel.setLayout(null);

        jFrame.add(panel);

    }

    public void setStyleComboBox(JComboBox comboBox){
        comboBox.setBackground(Color.decode("#E5E4E2"));
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
    }
}
