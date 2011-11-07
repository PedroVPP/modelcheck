package br.algorithms;

import java.util.ArrayList;

public class AlgorithmsInformation {
	
	private static ArrayList<Information> algorithmsInformation = new ArrayList<Information>();

	public static ArrayList<Information> getAlgorithmsInformation() {
		return algorithmsInformation;
	}

	public static void addInformation(Information information) {
		algorithmsInformation.add(information);
	}
}
