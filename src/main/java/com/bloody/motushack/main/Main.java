package com.bloody.motushack.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.bloody.motushack.model.AppModel;
import com.bloody.motushack.ui.AppPanel;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("MotusHack");
		String length = JOptionPane.showInputDialog(null, "Taille du mot", "", JOptionPane.QUESTION_MESSAGE);
		String firstLetter = JOptionPane.showInputDialog(null, "Première lettre du mot", "", JOptionPane.QUESTION_MESSAGE);
		f.setContentPane(new AppPanel(new AppModel(),Integer.parseInt(length),firstLetter));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
}
