package com.bloody.motushack.model;

import com.bloody.motushack.enums.EtatEnum;

public class LetterModel {
	
	private String lettre;
	private EtatEnum etat;
	
	public LetterModel(){
	}

	public String getLettre() {
		return lettre;
	}

	public void setLettre(String lettre) {
		this.lettre = lettre;
	}

	public EtatEnum getEtat() {
		return etat;
	}

	public void setEtat(EtatEnum etat) {
		this.etat = etat;
	}

}
