import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
        
        static final String PORTA_ESPORTE = "Esporte";
        static final String PORTA_ENTRETENIMENTO = "Entretenimento";
        static Object[] options = {PORTA_ESPORTE, PORTA_ENTRETENIMENTO};

        static Scanner sc = new Scanner(System.in);
        static int option;
        static String username;
        static int port;
        
        public static void main(String[] args) throws SocketException, UnknownHostException {
                

                option = JOptionPane.showOptionDialog(null, "Escolha um topico:", "Topicos", -1, -1, null, options, null);

                if (option == 0) {
                        port = 4000;
                } 
                else if (option == 1) {
                        port = 5000;
                }

                username = JOptionPane.showInputDialog("Digite o seu nome de usuario:");

                Thread server = new Thread(new Server(port, username));
                Thread client = new Thread(new Client(username, port));
                
                client.start();
                server.start();
                
        }
}
