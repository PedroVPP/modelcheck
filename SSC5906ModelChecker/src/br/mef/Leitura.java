package br.mef;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Classe de suporte a leitura de arquivos
 * 
 * @year 2010
 * @category VV&T
 */
public class Leitura {

	private File arquivo;

	private FileInputStream fIn;

	private InputStreamReader reader;

	private BufferedReader entrada;

	public Leitura(String dot) {
		arquivo = new File(dot);
		fIn = null;
		reader = null;
		entrada = null;

	}

	public boolean abrir() {
		if (arquivo.exists()) {
			try {
				fIn = new FileInputStream(arquivo);
				reader = new InputStreamReader(fIn);
				entrada = new BufferedReader(reader);
			} catch (Exception e) {
				return false;
			}
			return true;
		} else
			return false;
	}

	public boolean fimArquivo() {
		try {
			return !entrada.ready();
		} catch (Exception e) {
			return true;
		}
	}

	public String lerLinha() {
		try {
			if (entrada.ready())
				return entrada.readLine();
			else
				return "";
		} catch (Exception e) {
			return null;
		}
	}

	public boolean fechar() {
		try {
			entrada.close();
			reader.close();
			fIn.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
