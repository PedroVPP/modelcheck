package br.mef;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Classe de suporte a escrita de arquivos
 * 
 * @year 2010
 * @category VV&T
 */
public class Escrita {

	private File arquivo;

	private FileOutputStream fOut;

	private OutputStreamWriter writer;

	private BufferedWriter saida;

	public Escrita(String tempDot) {
		arquivo = new File(tempDot);
		fOut = null;
		writer = null;
		saida = null;

	}

	public boolean abrir() {
		try {
			fOut = new FileOutputStream(arquivo);
			writer = new OutputStreamWriter(fOut);
			saida = new BufferedWriter(writer);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean escreverLinha(String linha) {
		try {
			saida.write(linha);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean novaLinha() {
		try {
			saida.newLine();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean fechar() {
		try {
			saida.close();
			writer.close();
			fOut.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

