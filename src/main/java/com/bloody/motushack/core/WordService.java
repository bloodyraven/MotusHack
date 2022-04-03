package com.bloody.motushack.core;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.bloody.motushack.bean.LetterConstraints;
import com.bloody.motushack.data.DataLoader;

public class WordService {

	private List<String> words;

	public WordService() {
		DataLoader loader = new DataLoader();
		words = loader.getWords();
	}

	public List<String> getAllWords() {
		return words;
	}

	public List<String> getPossibleWords(List<String> words, LetterConstraints letters, int wordLength) {
		String regex = generateMustContainsLettersRegex(letters.getWordContains());
		List<String> matching = words;
		if(!regex.equals("^")) {
			Pattern mustContainsLettersPattern = Pattern.compile(regex);
			matching = words.stream().filter(mustContainsLettersPattern.asPredicate()).collect(Collectors.toList());
		}
		Pattern constraintsPattern = Pattern.compile(generateConstraintsRegex(letters, wordLength));
		matching = matching.stream().filter(constraintsPattern.asPredicate()).collect(Collectors.toList());
		return matching;
	}
	
	public List<String> getPossibleWords(String firstLetter, int wordLength) {
		String regex = generateFirstFilter(firstLetter, wordLength);
		Pattern mustContainsLettersPattern = Pattern.compile(regex);
		words = words.stream().filter(mustContainsLettersPattern.asPredicate()).collect(Collectors.toList());
		return words;
	}
	
	private String generateFirstFilter(String firstLetter, int wordLength) {
		StringBuilder regex = new StringBuilder("^");
		regex.append(firstLetter.toUpperCase());
		regex.append("[A-Z]{"+(wordLength-1)+"}$");
		return regex.toString();
	}

	private String generateConstraintsRegex(LetterConstraints constraints, int wordLength) {
		StringBuilder regex = new StringBuilder("^");
		for (int i=0; i<wordLength ;i++) {
			if(constraints.getOkLetters().containsKey(i)) {
				regex.append(constraints.getOkLetters().get(i));
			} else if(!constraints.getKoLetters().isEmpty() || constraints.getMalPlaced().containsKey(i)){
				regex.append("([^");
				for (String letter : constraints.getKoLetters()) {
					regex.append(letter);
				}
				if(constraints.getMalPlaced().containsKey(i)) {
					for (String letter : constraints.getMalPlaced().get(i)) {
						regex.append(letter);
					}
				}
				regex.append("]){1}");
			} else {
				regex.append("(.*){1}"); // TODO only A-Z
			}
		}
		regex.append("$");
		return regex.toString();
	}
	
	private String generateMustContainsLettersRegex(List<String> letters) {
		String s="^";
		for (String letter : letters) {
			s+="(?=[^"+letter.toUpperCase()+"]*+"+letter.toUpperCase()+")";
		}
		return s;
	}

}
