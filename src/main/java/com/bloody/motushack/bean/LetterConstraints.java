package com.bloody.motushack.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterConstraints {

	private HashMap<Integer, String> okLetters;
	private HashMap<Integer, List<String>> malPlaced;
	private List<String> wordContains;
	private List<String> koLetters;
	
	public LetterConstraints() {
		this.koLetters = new ArrayList<String>();
		this.okLetters = new HashMap<Integer, String>();
		this.wordContains = new ArrayList<String>();
		this.malPlaced = new HashMap<Integer, List<String>>();
	}
	
	public void addtoOkLetters(int i, String s) {
		okLetters.put(i, s);
	}
	
	public void addtoMalPlaced(int i, List<String> s) {
		malPlaced.put(i, s);
	}
	
	public void addtoMalPlaced(int i, String s) {
		if(malPlaced.containsKey(i)) {
			List<String> actual = malPlaced.get(i);
			if(!actual.contains(s)) {
				actual.add(s);
				malPlaced.put(i, actual);
			}
		} else {
			ArrayList<String> list = new ArrayList<String>();
			list.add(s);
			malPlaced.put(i, list);
		}
	}
	
	public void addToWordContains(String s)  {
		wordContains.add(s);
	}
	
	public void addToKoLetters(String s)  {
		koLetters.add(s);
	}
	
	public List<String> getKoLetters() {
		return koLetters;
	}
	
	public boolean koLettersContains(String s) {
		return koLetters.contains(s);
	}

	public List<String> getWordContains() {
		return wordContains;
	}

	public HashMap<Integer, List<String>> getMalPlaced() {
		return malPlaced;
	}

	public HashMap<Integer, String> getOkLetters() {
		return okLetters;
	}
}
