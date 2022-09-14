public class BuscaMain {

	public static void main(String[] args) {
		int tamanhoVetor = 100000000;
		int valor = (int) ((Math.random() * (tamanhoVetor - 1)) + 1);
		int indice;
		int[] vetor = new int[tamanhoVetor];

		for (int i = 0; i < tamanhoVetor; i++) {
			vetor[i] = i + 1;
		}
		
		indice = BuscaParalela.parallelSearch(valor, vetor, 100);
		System.out.println("Valor: " + valor + " Indice: " + indice);
	}

}