package com.bloody.motushack.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.bloody.motushack.enums.EtatEnum;
import com.bloody.motushack.model.LetterModel;

@SuppressWarnings("serial")
public class LetterPanel extends JPanel {
	
	private LetterModel model;
	private JTextField field = new JTextField();

	public LetterPanel(LetterModel model) {
		this.model = model;
		model.setEtat(EtatEnum.PASPRESENT);
		
		field.setSize(new Dimension(30, 20));
		field.setMargin(new Insets(15, 15, 15, 15));
		field.setDocument(new LimitJTextField(1));
		this.setLayout(new BorderLayout());
		this.add(field, BorderLayout.CENTER);
		
		JPanel colorPanel = new JPanel();
		JButton redButton = new JButton();
		redButton.setBackground(Color.RED);
		redButton.setMargin(new Insets(5, 5, 5, 5));
		JButton yellowButton = new JButton();
		yellowButton.setBackground(Color.YELLOW);
		yellowButton.setMargin(new Insets(5, 5, 5, 5));
		JButton whiteButton = new JButton();
		whiteButton.setBackground(Color.WHITE);
		whiteButton.setMargin(new Insets(5, 5, 5, 5));
		colorPanel.add(redButton);
		redButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Component field = redButton.getParent().getParent().getComponent(0);
				field.setBackground(Color.RED);
				field.validate();
				field.repaint();
				model.setEtat(EtatEnum.BIENPLACE);
			}
		});
		colorPanel.add(yellowButton);
		yellowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Component field = yellowButton.getParent().getParent().getComponent(0);
				field.setBackground(Color.YELLOW);
				field.validate();
				field.repaint();
				model.setEtat(EtatEnum.MALPLACE);
			}
		});
		colorPanel.add(whiteButton);
		whiteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Component field = whiteButton.getParent().getParent().getComponent(0);
				field.setBackground(Color.WHITE);
				field.validate();
				field.repaint();
				model.setEtat(EtatEnum.PASPRESENT);
			}
		});
		this.add(colorPanel, BorderLayout.SOUTH);
	}

	public LetterModel getModel() {
		return model;
	}
	
	public String getTextFieldValue() {
		return field.getText();
	}
	
	public void disableFields() {
		JPanel panbuttons = ((JPanel)this.getComponent(1));
		for (int i = 0; i < panbuttons.getComponentCount(); i++) {
			((JButton)panbuttons.getComponent(i)).setEnabled(false);
		}
		field.setEnabled(false);
	}
	
	public JTextField getField() {
		return field;
	}
	
	class LimitJTextField extends PlainDocument {
	   private int max;
	   LimitJTextField(int max) {
	      super();
	      this.max = max;
	   }
	   public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
	      if (text == null)
	         return;
	      if ((getLength() + text.length()) <= max) {
	         super.insertString(offset, text, attr);
	      }
	   }
	}
	
}
