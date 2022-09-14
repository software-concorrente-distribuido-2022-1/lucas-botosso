import java.net.*;
import java.util.Arrays;
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

		String nome = null;
		String cargo = null;
		double salario = 0.0;
		String[] mensagem = null;

		try {
			saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

			mensagem = entrada.readLine().split(",");
			nome = mensagem[0];
			cargo = mensagem[1];
			salario = Double.parseDouble(mensagem[2]);

			if (cargo.toLowerCase().equals("operador")) {
				salario = salario * 1.2;
			} else if (cargo.toLowerCase().equals("programador")) {
				salario = salario * 1.18;
			}

			saida.println("Nome: " + nome);
			saida.println("Salario reajustado: R$" + String.format("%.2f", salario));

			System.out.println(
					"Cliente " + endCliente.getHostAddress() + " atendido com a mensagem " + Arrays.toString(mensagem));

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