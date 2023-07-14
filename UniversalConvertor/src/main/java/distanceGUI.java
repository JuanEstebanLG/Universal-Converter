import javax.swing.*;
import java.awt.*;

public class distanceGUI extends FormsUtil {
    JPanel panelPrincipal = new JPanel();
    private static boolean showMenu = false;
    String [] unidades = {"Millas", "Kilometro", "Metro"};

    public distanceGUI(JFrame frame){MakeGUIS(frame,panelPrincipal, Color.decode("#393B54"), unidades, unidades );}


}
