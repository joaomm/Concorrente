package ep2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeitorDeArquivo {
	private static BufferedReader br;
	private static DataInputStream in;

	public LeitorDeArquivo(String nomeDoArquivo) {
		try {
			FileInputStream fstream = new FileInputStream(nomeDoArquivo);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String proximaLinha() {
		try {
			String linha = br.readLine();
			if (linha == null) {
				in.close();
			}
			return linha;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
