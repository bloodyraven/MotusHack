package com.bloody.motushack.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.bloody.motushack.bean.LetterConstraints;
import com.bloody.motushack.enums.EtatEnum;
import com.bloody.motushack.model.LetterModel;
import com.bloody.motushack.model.WordModel;

@SuppressWarnings("serial")
public class WordPanel extends JPanel {
	
	private WordModel model;
	
	private LetterPanel[] letters;
	
	public WordPanel(WordModel model, int wordLength) {
		this.model = model;
		letters = new LetterPanel[wordLength];
		for (int i = 0; i < letters.length; i++) {
			letters[i] = new LetterPanel(new LetterModel());
			this.add(letters[i]);
		}
		JButton button = new JButton("=>");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// validate data
				for (LetterPanel letterPanel : letters) {
					if(letterPanel.getTextFieldValue().isBlank()) {
						JOptionPane.showMessageDialog(null, "Error at validation data");
						return;
					}
				}
				// récupérer les lettres
				for (int i = 0; i < button.getParent().getComponentCount(); i++) {
					if(button.getParent().getComponent(i).getClass() == LetterPanel.class) {
						LetterPanel pan = (LetterPanel) button.getParent().getComponent(i);
						pan.getModel().setLettre(pan.getTextFieldValue());
					}
				}
				// traitement
				List<String> words = ((AppPanel)button.getParent().getParent().getParent()).getModel().searchWords(getLetterConstraints(wordLength), wordLength);
				((AppPanel)button.getParent().getParent().getParent()).populateWordsPanel(words);
				// ajouter une ligne
				((AppPanel)button.getParent().getParent().getParent()).addNewLine();
				// disable boutons & TF
				button.setEnabled(false);
				for (LetterPanel letterPanel : letters) {
					letterPanel.disableFields();
				}
				button.getParent().setBackground(Color.GRAY);
				button.getParent().repaint();
				button.getParent().revalidate();
			}
		});
		this.add(button);
	}
	
	public LetterConstraints getLetterConstraints(int wordLength) {
		LetterConstraints constraints = new LetterConstraints();
		for (int i = 0; i < letters.length; i++) {
			String lettre = letters[i].getModel().getLettre().toUpperCase();
			if(letters[i].getModel().getEtat().equals(EtatEnum.BIENPLACE)) {
				constraints.addtoOkLetters(i, lettre);
			} else if(letters[i].getModel().getEtat().equals(EtatEnum.MALPLACE)) {
				constraints.addtoMalPlaced(i, lettre);
				constraints.addToWordContains(lettre);
			} else {
				if(!constraints.getWordContains().contains(lettre)) {
					constraints.addToKoLetters(lettre);
				}
			}
		}
		return constraints;
	}

	public WordModel getModel() {
		return model;
	}
	
	public LetterPanel[] getLetterPanels() {
		return letters;
	}

}
