import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server implements Runnable {

        InetAddress ia;
        NetworkInterface ni;
        int port;
        InetSocketAddress address;
        String username;

        public Server(int port, String username) throws SocketException, UnknownHostException {
                this.port = port;
                this.username = username;
        }

        public void run() {

                try (MulticastSocket socket = new MulticastSocket(port)) {                        

                        ia = InetAddress.getByName("230.0.0.0");
                        ni = NetworkInterface.getByInetAddress(ia);
                        address = new InetSocketAddress(ia, port);
                        socket.joinGroup(address, ni);

                        receive(socket);

                        socket.leaveGroup(address, ni);

                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

        public void receive(MulticastSocket socket) throws IOException {
                
                while (true) {
                                
                        byte[] buffer = new byte[1024];

                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);

                       
                        byte[] data = new byte[packet.getLength()];
                        System.arraycopy(packet.getData(), packet.getOffset(), data, 0, packet.getLength());

                        String message = new String(data);

                        if (!message.equals("SAIR")) {
                                System.out.println(message);
                        } else {
                                break;
                        }
                                    
                }
                      
        }

}
