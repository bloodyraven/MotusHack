package com.bloody.com.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bloody.motushack.bean.LetterConstraints;
import com.bloody.motushack.core.WordService;

public class WordServiceTest {

	@Test
    public void shouldAnswerWithTrue() {
        WordService service = new WordService();
        
        List<String> list = new ArrayList<String>();
        list.add("ABCDE");
        list.add("ABCDF");
        list.add("ABCDQ");
        list.add("QBCDE");
        
        LetterConstraints letters = new LetterConstraints();
//        letters.addtoOkLetters(0, "A");
//        letters.addToWordContains("Q");
        ArrayList<String> a = new ArrayList<String>();
        a.add("Q");
        letters.addtoMalPlaced(0, a);
        letters.addToWordContains("Q");
        letters.addToKoLetters("E");
        
        System.out.println(service.getPossibleWords(list, letters, 5));
    }
	
}
