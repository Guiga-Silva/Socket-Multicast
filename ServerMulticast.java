import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ServerMulticast {
   public static void main(String[] args) throws IOException {
	   String mensagem = " ";
	   byte[] envio = new byte[1024];
	   Scanner sc = new Scanner(System.in);
	   
	   MulticastSocket socket = new MulticastSocket();
	   InetAddress grupo = InetAddress.getByName("230.0.0.0");
	   
	   while(!mensagem.equals("Servidor Encerrado!")){
		   System.out.print("[Servidor] Digite a mensagem:");
		   mensagem = sc.nextLine();
		   if(mensagem.equals("encerrar"))
			   mensagem = "Servidor Encerrado!";
		   envio = mensagem.getBytes();	   
		   
		   DatagramPacket pacote = new DatagramPacket(envio, envio.length,grupo, 4321);
		   socket.send(pacote);
		   
		  
	   }

	   System.out.print("[Servidor] Multicast Encerrado");
	   socket.close(); 
   }
}