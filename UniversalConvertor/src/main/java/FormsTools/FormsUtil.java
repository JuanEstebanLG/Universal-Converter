package FormsTools;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;



    /**
     * Esta clase tiene como función contener todas las operaciones que permiten
     * el correcto funcionamiento a la hora de crear todos los GUI.
     * Aca se almacenan métodos, tanto para agilizar la creación de paneles y la estilación de componentes
     * como para filtrar caracteres en los inputs.
     *
     * */
public abstract class FormsUtil extends FormFunctions {

    private static boolean showMenu = false;

        /**
         * Este metodo es utilizado en todos los JTextField a lo largo del proyecto.
         * La función que desempeña es la de filtrar y evitar el uso de caracteres, eliminando todos los símbolos a excepción de los números
         * y del punto.
         * @param textField JTextField a filtrar.
         */

    public void NumbersOnly(JTextField textField){
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        DocumentFilter documentFilter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                // Este método se llama al insertar texto en el campo de texto.

                // Verifica si el texto contiene solo números y puntos decimales.
                if (text.matches("[0-9]+")) {
                    // Si el texto es válido, se llama al método insertString de la superclase DocumentFilter.
                    super.insertString(fb, offset, text, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                // Este método se llama al reemplazar texto existente en el campo de texto.

                // Verifica si el texto contiene solo números y puntos decimales.
                if (text.matches("[0-9]+")) {
                    // Si el texto es válido, se llama al método replace de la superclase DocumentFilter.
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };

// Se establece el DocumentFilter personalizado en el documento del campo de texto.
        document.setDocumentFilter(documentFilter);
    }

        /**
         * Este método es utilizado para la creacion de paneles personalizados de manera rápida.
         *
         * @param jFrame Frame sobre el cual se creara el panel.
         * @param panel Panel a editar.
         * @param positionX Posición en el eje x desde donde se creara el panel.
         * @param positionY Posición en el eje y desde donde se creara el panel.
         * @param width Ancho del panel.
         * @param height Largo del panel.
         * @param color Color de fondo del panel.
         *
         *
         */
    public static void makePanel(JFrame jFrame, JPanel panel, int positionX, int positionY, int width, int height, Color color){
        /*
            Sé instancia el panel con las posiciones, el tamaño y el color definidos.
         */

        panel.setBounds(positionX, positionY, width, height);
        panel.setBackground(color);

        //Se define el layout del panel en null

        panel.setLayout(null);

        //Se añade el panel al frame

        jFrame.add(panel);

    }

        /**
         * Este método tiene la tarea de crear el menu desplegable común entre todos los Formularios, para ello utilizando los métodos makePanel(), para la
         * creación del menu y setButtonStyle(), para estilizar los botones del menu.
         *
         * Para poder cambiar entre formularios, el método usará chageState(), de la clase FormsFunctions
         *
         * @param state Este booleano tiene la tarea de controlar el estado del menu, haciéndolo visible o invisible.
         * @param frame Frame en el cual se creara el panel.
         * @param menu Menu a editar.
         * @param mainView Panel principal, Este es el panel con los formularios es decir, el panel principal .
         * @param origen Este es un JComboBox, perteneciente al panel principal, cambiará de posición según el estado del menu.
         * @param destino Este es un JComboBox, perteneciente al panel principal, cambiará de posición según el estado del menu.
         * @param x Tamaño en el eje x del frame.
         * @param y Tamaño en el eje y del frame.
         * @return Devolverá el dato State, para controlar el esto del menu.
         *
         * @see FormFunctions#changeState(JFrame, JPanel, JPanel, int)
         * @see #makePanel(JFrame, JPanel, int, int, int, int, Color)
         * @see #setButtonStyle(JButton, int, int, int, int, Color)
         */
    public boolean menuBar(boolean state, JFrame frame ,JPanel menu, JPanel mainView, JComboBox origen, JComboBox destino, int x, int y){
        //Si el dato State es falso, se procederá con la creación del panel, para ello editando el tamaño del panel principal y la posición de sus componentes.
        if(!state){

            //Creación de los 3 botones encargados de redirigir al usuario a alguno de los otros formularios
            JButton[] buttonsChange = new JButton[3];

            buttonsChange[0] = new JButton("Divisas");
            buttonsChange[1] = new JButton("Distancias");
            buttonsChange[2] = new JButton("Temperaturas");


            /*
                Cambio en el tamaño del panel principal y la posición de sus componentes; Creación del menu.
             */

            mainView.setBounds(100, 0, x - 100, y);
            makePanel(frame, menu, 0, 0, 100, y, Color.WHITE);

            origen.setBounds((700/2) - 150, 100, 150, 25);
            destino.setBounds(700 - 250 , 100, 150, 25);

            //Añadiendo los botones al menu
            menu.add(buttonsChange[0]);
            menu.add(buttonsChange[1]);
            menu.add(buttonsChange[2]);

            //Estableciendo el estilo y posición de los botones.

            setButtonStyle(buttonsChange[0], 0, 80, 100, 30, Color.WHITE);
            setButtonStyle(buttonsChange[1], 0, 135,100, 30, Color.WHITE);
            setButtonStyle(buttonsChange[2], 0, 185,100, 30,  Color.WHITE);


            /*
                El action listener común entre los 3 botones tendrá la capacidad de redirigir al usuario a alguno de los formularios.
                Para ello usará el método ChangeState
             */


            buttonsChange[0].addActionListener(e -> changeState(frame, menu, mainView, 0));
            buttonsChange[1].addActionListener(e -> changeState(frame, menu, mainView, 2));
            buttonsChange[2].addActionListener(e -> changeState(frame, menu, mainView, 1));

            //El valor del estado cambia a true

            state = true;
        }else {
            //Dado que state sea igual a true, se eliminara el menu y sé restablecerán los valores para el panel principal y sus componentes.

            frame.remove(menu);
            mainView.setBounds(0,0,x,y);
            origen.setBounds((x/2) - 250, 100, 150, 25);
            destino.setBounds(x - 300, 100, 150, 25);

            //El valor del estado cambia a false.
            state = false;
        }
        return state;
    }

        /**
         * Este método establece un estilo básico y común para todos los JComboBox.
         * @param comboBox JComboBox a modificar.
         */
    public void setStyleComboBox(JComboBox comboBox){
        comboBox.setBackground(Color.decode("#3b3e46"));
        comboBox.setForeground(Color.WHITE);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
    }

        /**
         * Esta función establece un estilo básico y común para los botones.
         * @param button Botón a modificar.
         * @param x Posición en el eje x del botón.
         * @param y Posición en el eje y del botón.
         * @param w Ancho del botón.
         * @param h Largo del botón.
         */
    public void setButtonStyle(JButton button, int x, int y, int w, int h, Color color){

        button.setBounds(x,y,w,h);
        button.setBackground(color);
        button.setForeground(Color.decode("#454C70"));
        button.setBorder(null);


    }

        /**
         * Esta función es llamada desde cualquier formulario que requiera la plantilla típica empleada en este
         * proyecto, es decir, 2 campos de lista, uno de input y dos botones, uno para abrir el menu, y otro para enviar
         * datos.
         *
         * Utilizando para ello los métodos setStyleComboBox(), NumbersOnly(), setButtonStyle().
         * Este método utiliza los métodos sendData() y menuBar() para enviar los datos del usuario y la creación del menu desplegable respectivamente.
         *
         * @param frame Frame en donde se se colocara la plantilla.
         * @param panelPrincipal Panel sobre el cual se colocaran los componentes.
         * @param color Color de fondo del panel.
         * @param Origen Lista origen de unidades.
         * @param Destino Lista destino de unidades.
         *
         * @see #sendData(JComboBox, JComboBox, JTextField, JFrame)
         * @see #menuBar(boolean, JFrame, JPanel, JPanel, JComboBox, JComboBox, int, int)
         * @see #NumbersOnly(JTextField)
         * @see #setButtonStyle(JButton, int, int, int, int, Color)
         * @see #setStyleComboBox(JComboBox)
         *
         */

    public void MakeGUIS(JFrame frame,JPanel panelPrincipal, String imgLocation, Color color, String[] Origen, String[] Destino){
        //Sé establece el tamaño base del frame.
        final int x = 800;
        final int y = 500;
        final int posicionCenter = (x/2);
        //Sé crea el frame y se establecen sus características

        final ImageIcon icon = new ImageIcon(imgLocation);
        JLabel labelBackground = new JLabel(icon);
        frame.setSize(x,y);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

        //Sé crea el panel, usando el método makePanel();
        makePanel(frame, panelPrincipal, 0,0, x, y, color);

        //Creación de los componentes del panel, incluyendo las listas, los botones y los inputs.

        JComboBox<String> unidadOrigen = new JComboBox<>(Origen);
        JComboBox<String> unidadDestino = new JComboBox<>(Destino);

        JTextField inputUser = new JTextField();
        JButton menu_button = new JButton("Menu");
        JButton send_button = new JButton("Transformar");
        JPanel menuPrincipal = new JPanel();

        //Sé le da el estilo común a las listas
        setStyleComboBox(unidadOrigen);
        setStyleComboBox(unidadDestino);

        //Sé asigna el tamaño y posición de las listas, el input y los botones.

        unidadOrigen.setBounds( posicionCenter - 250, 100, 150, 25);
        unidadDestino.setBounds(x - 300, 100, 150, 25);

        inputUser.setBounds(posicionCenter - 100, y/2, 200, 25 );
        setButtonStyle(send_button, posicionCenter - 75, (y/2) + 40, 150, 30, Color.WHITE);
        labelBackground.setBounds(0,0,x,y);

        menu_button.setBounds(0,0, 70, 70);
        menu_button.setBackground(Color.WHITE);
        menu_button.setOpaque(false);
        menu_button.setForeground(Color.decode("#EAF58F"));

        //Sé filtra el input usando el método NumbersOnly()

        NumbersOnly(inputUser);




        //Sé añaden los respectivos eventos para los botones.
        menu_button.addActionListener(e -> showMenu = menuBar(showMenu, frame, menuPrincipal, panelPrincipal,unidadOrigen,unidadDestino, x, y));

        send_button.addActionListener(e -> sendData(unidadOrigen, unidadDestino, inputUser, frame));
        // Por ultimo, sé añaden los componentes al panel.
        panelPrincipal.add(unidadOrigen);
        panelPrincipal.add(unidadDestino);
        panelPrincipal.add(inputUser);
        panelPrincipal.add(menu_button);
        panelPrincipal.add(send_button);
        panelPrincipal.add(labelBackground);

    }
}
