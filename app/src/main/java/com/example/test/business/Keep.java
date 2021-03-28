package com.example.test.business;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

// Pour notre type de notes

@RequiresApi(api = Build.VERSION_CODES.O)
public class Keep {
	private String titre;
	private String texte;
	private String tag;
	private boolean done = false;
	private int backgroundColor;
	private LocalDate dateLimite = LocalDate.parse("2000-01-01"); // Pour éviter un NullPointerException dans KeepsAdapter

	public Keep () {

	}

	public Keep (String titre, String texte) {
		this.titre = titre;
		this.texte = texte;
	}

	public Keep (String titre, String texte, int backgroundColor) {
		this (titre, texte);
		this.backgroundColor = backgroundColor;
	}

	public Keep (String titre, String texte, LocalDate dateLimite) {
		this (titre, texte);
		this.dateLimite = dateLimite;
	}

	public Keep (String titre, String texte, String tag, boolean done, LocalDate dateLimite) {
		this(titre, texte, dateLimite);
		this.tag = tag;
		this.done = done;
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

	public LocalDate getDateLimite() {
		return dateLimite;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getColorString () {
		return String.valueOf(this.backgroundColor);
	}

	//	@RequiresApi(api = Build.VERSION_CODES.O)
//	public String getDateLimiteString () {
//		DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

//		String outputText = outputFormat.format(date);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yy");
//		return this.dateLimite.format(formatter);
//		DateFormat df = new SimpleDateFormat("dd-mm-yy");
//		return df.format(this.dateLimite);
//	}

	public void setDateLimite(LocalDate dateLimite) {
		this.dateLimite = dateLimite;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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