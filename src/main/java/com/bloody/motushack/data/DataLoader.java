package com.bloody.motushack.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {

	private List<String> words;

	public DataLoader() {
		words = new ArrayList<String>();
		try {
			File myObj = new File("resources/words.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				words.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public List<String> getWords() {
		return words;
	}

}
