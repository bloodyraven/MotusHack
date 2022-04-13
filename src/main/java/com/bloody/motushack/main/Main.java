package com.bloody.motushack.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.bloody.motushack.model.AppModel;
import com.bloody.motushack.ui.AppPanel;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("MotusHack");
		setContentAndAskInputs(f);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem jmi = new JMenuItem("Nouveau");
		
		jmi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(false);
				setContentAndAskInputs(f);
				f.setVisible(true);
			}
		});
		
		menubar.add(menu);
		menu.add(jmi);
		
		f.setJMenuBar(menubar);
	}
	
	public static void setContentAndAskInputs(JFrame f) {
		String length = JOptionPane.showInputDialog(null, "Taille du mot", "", JOptionPane.QUESTION_MESSAGE);
		String firstLetter = JOptionPane.showInputDialog(null, "Première lettre du mot", "", JOptionPane.QUESTION_MESSAGE);
		f.setContentPane(new AppPanel(new AppModel(),Integer.parseInt(length),firstLetter));
	}
	
}
