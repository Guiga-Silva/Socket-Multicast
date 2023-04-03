import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientMulticast implements Runnable {
	public static void main(String[] args) throws IOException {

		int PORTA_ESPORTE = 22622;
		int PORTA_ENTRETENIMENTO = 3000;
		Scanner sc = new Scanner(System.in);

		String mensagem = " ";
		
		
		MulticastSocket socketEsporte = new MulticastSocket(PORTA_ESPORTE);
		MulticastSocket socketEntretenimento = new MulticastSocket(PORTA_ENTRETENIMENTO);

		InetAddress ia = InetAddress.getByName("230.0.0.0");

		
		NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

		// Um grupo para entretenimento e outro para esportes
		InetSocketAddress grupoEsportes = new InetSocketAddress(ia, PORTA_ESPORTE);
		InetSocketAddress grupoEntretenimento = new InetSocketAddress(ia, PORTA_ENTRETENIMENTO);

		System.out.println("Qual canal você deseja entrar?\n[1] - Esportes\n[2] - Entretenimento\n");
		String porta = sc.nextLine();

		System.out.println("Agora, escolha o seu usário: ");
		String usuario = sc.nextLine();
		
		if (porta.equals("1")) {
			socketEsporte.joinGroup(grupoEsportes, ni);
		} 
		else if (porta.equals("2")) {
			socketEntretenimento.joinGroup(grupoEntretenimento, ni);
		}

		// while (!mensagem.equals("SAIR")) {

		// }

		System.out.println("[" + LocalDate.now() + "] " + usuario  + " saiu do grupo");
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'run'");
	}

}