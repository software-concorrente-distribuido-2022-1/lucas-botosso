import java.net.*;
import java.io.*;

class Conexao {

	private Socket socketCliente;

	Conexao(Socket aSocketCliente) throws IOException {
		this.socketCliente = aSocketCliente;
	}

	public void iniciar() {
		PrintWriter saida = null;
		BufferedReader entrada = null;

		InetAddress endCliente = this.socketCliente.getInetAddress();

		double saldoMedio = 0.0;
		double credito = 0.0;
		String mensagem = null;

		try {
			saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

			mensagem = entrada.readLine();
			saldoMedio = Double.parseDouble(mensagem);

			if (saldoMedio >= 201.0 && saldoMedio <= 400.99) {
				credito = saldoMedio * 0.2;
			} else if (saldoMedio >= 401.0 && saldoMedio <= 600.99) {
				credito = saldoMedio * 0.3;
			} else if (saldoMedio >= 601.0) {
				credito = saldoMedio * 0.4;
			}

			saida.println("Saldo medio: R$" + String.format("%.2f", saldoMedio));
			saida.println("Credito: R$" + String.format("%.2f", credito));

			System.out.println("Cliente " + endCliente.getHostAddress() + " atendido com a mensagem " + mensagem);

			socketCliente.close();
			saida.close();
			entrada.close();

		} catch (IOException e) {
			System.out.println("Erro E/S " + e);
		}
	}
}

public class Servidor {
	public static void main(String[] args) throws IOException {

		final int portaDefault = 8080;
		int porta;

		Socket socketCliente = null;
		ServerSocket socketServidor = null;

		if ((args.length == 1))
			porta = Integer.parseInt(args[0]);
		else
			porta = portaDefault;

		while (true) {
			try {
				socketServidor = new ServerSocket(porta);
				break;
			} catch (IOException e) {
				porta++;
			}
		}

		System.out.println("\nServidor ativado. " + "Aguardando Cliente na porta " + porta + "...\n");

		while (true) {

			socketCliente = null;
			try {
				socketCliente = socketServidor.accept();
			} catch (IOException e) {
				System.err.println("Erro de E/S " + e);
				socketServidor.close();
				System.exit(1);
			}

			new Conexao(socketCliente).iniciar();
		}
	}
}