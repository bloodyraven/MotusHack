package com.bloody.motushack.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.bloody.motushack.model.AppModel;
import com.bloody.motushack.model.WordModel;

@SuppressWarnings("serial")
public class AppPanel extends JPanel {
	
	private AppModel model;
	private WordPanel[] words;
	public int cpt = 1;
	public JPanel wordsPanel;
	public JPanel searchPanel;
	
	public AppPanel(AppModel model, int wordLength, String firstLetter) {
		this.model=model;
		words = new WordPanel[20];
		for (int i = 0; i < words.length; i++) {
			words[i] = new WordPanel(new WordModel(), wordLength);
		}
		this.setLayout(new BorderLayout());
		wordsPanel = new JPanel();
		wordsPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
		wordsPanel.setLayout(new GridLayout(0, 6, 2, 2));
		JScrollPane jsp = new JScrollPane(wordsPanel);
		
		List<String> list = model.searchFirstWords(firstLetter, wordLength);
		populateWordsPanel(list);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(words[0]);
		this.add(jsp, BorderLayout.EAST);
		this.add(searchPanel, BorderLayout.CENTER);
	}
	
	public void populateWordsPanel(List<String> list) {
		wordsPanel.removeAll();
		wordsPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
		wordsPanel.setLayout(new GridLayout(0, 6, 2, 2));
		for (String word : list) {
			JLabel label = new JLabel(word);
			label.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					((AppPanel)wordsPanel.getParent().getParent().getParent()).setWordToLastPan(word);
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			wordsPanel.add(label);
		}
		wordsPanel.repaint();
		wordsPanel.revalidate();
	}
	
	public void addNewLine() {
		searchPanel.add(words[cpt]);
		cpt++;
		searchPanel.repaint();
		searchPanel.validate();
	}
	
	public void setWordToLastPan(String s) {
		for (int i=0; i<words[cpt].getLetterPanels().length; i++) {
			words[cpt-1].getLetterPanels()[i].getField().setText(s.substring(i,i+1));
		}
	}

	public AppModel getModel() {
		return model;
	}
}
