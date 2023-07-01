import javax.swing.*;
import java.awt.*;

public class Control extends JFrame {
    JPanel panel = new JPanel();
    JButton button = new JButton("Click me");

    public Control(){
           int width = 600;
           int height = 600;

            this.setLayout(null);
           //Set panel propities
            panel.setLayout(null);
            panel.setBounds(50,50, 475, 475);
            panel.add(button);
            panel.setBackground(Color.BLUE);
            add(panel);


            button.setBounds(190, 230, 100, 20);
            button.setBackground(Color.black);


            //Jframe sets

            setVisible(true);
            setSize(width,height);
            setBackground(Color.GREEN);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            String saludo = JOptionPane.showInputDialog(panel, "Ingresa un lindo saludo!");

            switch (saludo){
                case "Hola":
                    System.out.println("Hola!, ¿Cómo estás?");
                    break;
                case "Buenos dias":
                    System.out.println("Buenos dias para ti también!");
                    break;
                case "Buenas buenas":
                    System.out.println("Y que te quemas!");
                default:
                    System.out.println("Hablame :(");
                    break;
            }
       }



        public static void main(String[] args){

            new Control();


        }
}


