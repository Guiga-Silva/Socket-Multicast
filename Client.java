import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client implements Runnable {

        String username;
        int port;
        Scanner sc = new Scanner(System.in);
        Thread server;
        

        public Client(String username, int port) {
                this.username = username;
                this.port = port;
        }

        public void run() {
                try (MulticastSocket socket = new MulticastSocket(port)) {

                        InetAddress ia = InetAddress.getByName("230.0.0.0");
                        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);
                        InetSocketAddress address = new InetSocketAddress(ia, this.port);
                        socket.joinGroup(address, ni);

                        String message = "";

                        while (true) {


                                message = JOptionPane.showInputDialog(null, "Mensagem: ", this.username + "'s chat", -1);
                                
                                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                String formattedDate = LocalDate.now().format(dateFormatter);
                                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                                String formattedTime = LocalTime.now().format(timeFormatter);
        
                                String userTime = formattedDate + " - " + formattedTime;

                                if (!message.equals("SAIR")) {
                                        message = "[" + userTime + "] " + this.username + ": " + message;
                                        send(message, address, socket);
                                }
                                else if (message.equals("SAIR")) {
                                        message = "[" + userTime + "] " + this.username + " saiu do grupo";
                                        send(message, address, socket);
                                        
                                        message = "SAIR";
                                        send(message, address, socket);
                                        break;
                                }
                        }

                        socket.leaveGroup(address, ni);

                        

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void send(String message, InetSocketAddress address, MulticastSocket socket) throws IOException {
                byte[] content = new byte[1024];

                content = message.getBytes();

                DatagramPacket packet = new DatagramPacket(content, content.length, address);
                socket.send(packet);
        }

}
