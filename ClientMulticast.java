import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class ClientMulticast {
	public static void main(String[] args) throws IOException {

		int PORT_ESPORTE = 22622;
		int PORT_ENTRETENIMENTO = 66266;

		String msg = " ";

		// Duas portas diferentes, uma para entretenimento e outra para esportes
		MulticastSocket socket = new MulticastSocket(4321);

		InetAddress ia = InetAddress.getByName("230.0.0.0");

		// Um grupo para entretenimento e outro para esportes
		InetSocketAddress grupo = new InetSocketAddress(ia, 4321);

		NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

		// If para determinar o socket escolhido pelo cliente. Entretenimento ou Esportes
		socket.joinGroup(grupo, ni);

		// Escolher o nome de usuário

		while (!msg.equals("SAIR")) {
			// System.out.println("[Cliente] Esperando por mensagem Multicast...");
			System.out.print("[Servidor] Digite a mensagem: ");

			byte[] envio = new byte[1024];
			
 			// If para determinar o socket escolhido pelo cliente. Entretenimento ou Esportes

			DatagramPacket packet = new DatagramPacket(envio, envio.length, PORT);
			socket.receive(packet);

			msg = new String(packet.getData());
			System.out.println("[Cliente] Mensagem recebida do Servidor: " + msg);

		}

		System.out.println("[Cliente] Conexao Encerrada!");
		socket.leaveGroup(grupo, ni);
		socket.close();

	}

}