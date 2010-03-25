package ep1;



public class Ep1 {
	public static void main(String[] args) {
		if(args.length == 2){
			String algoritmoEscolhido = args[0];
			int n = Integer.parseInt(args[1]);
			if(algoritmoEscolhido.equals("1")){
				rodaAlgoritmo1(n, 2);
			}
			else if(algoritmoEscolhido.equals("2")){
				System.out.println("Ainda não implementamos o algoritmo 2!");
			}
			else {
				System.out.println("Ainda não implementamos o algoritmo 3!");
			}
		}
		else {
			System.out.println("O programa precisa de dois parametros!");
		}
	}

	private static void rodaAlgoritmo1(int n, int threads) {
		Algoritmo1 alg1 = new Algoritmo1(n, threads);
		
		long begin = System.currentTimeMillis();
		double sum = alg1.calculaSoma();
		long end = System.currentTimeMillis();
		
		long elapsedTime = end - begin;
		System.out.println("Resultado foi: " + sum + " em " + elapsedTime + "ms");
	}
}
