package com.bloody.motushack.model;

import java.util.List;

import com.bloody.motushack.bean.LetterConstraints;
import com.bloody.motushack.core.WordService;

public class AppModel {
	
	private List<String> possibleWords;
	private WordService wordService;
	
	public AppModel() {
		wordService = new WordService();
		possibleWords = wordService.getAllWords();
	}
	
	public List<String> searchFirstWords(String firstLetter, int wordLength) {
		possibleWords = wordService.getPossibleWords(firstLetter, wordLength);
		return possibleWords;
	}
	
	public List<String> searchWords(LetterConstraints lc, int wordLength) {
		possibleWords = wordService.getPossibleWords(possibleWords, lc, wordLength);
		return possibleWords;
	}

	public List<String> getPossibleWords() {
		return possibleWords;
	}

}
