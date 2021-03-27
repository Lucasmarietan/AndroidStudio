package com.example.test.business;

import java.util.Date;

// Pour notre type de notes

public class Keep {
	private String titre;
	private String texte;
	private String tag;
	private boolean done = false;
	private Date dateLimite;
	private int backgroundColor;

	public Keep () {

	}

	public Keep (String titre, String texte) {
		this.titre = titre;
		this.texte = texte;
	}

	public Keep(String titre, String texte, int backgroundColor) {
		this.titre = titre;
		this.texte = texte;
		this.backgroundColor = backgroundColor;
	}

	public Keep(String titre, String texte, String tag, boolean done, Date dateLimite, int backgroundColor) {
		this.titre = titre;
		this.texte = texte;
		this.tag = tag;
		this.done = done;
		this.dateLimite = dateLimite;
		this.backgroundColor = backgroundColor;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public String toString () {
		return "Keep {" +
				"titre='" + titre + '\'' +
				", texte='" + texte + '\'' +
				", done=" + done +
				", dateLimite=" + dateLimite +
				'}';
	}
}