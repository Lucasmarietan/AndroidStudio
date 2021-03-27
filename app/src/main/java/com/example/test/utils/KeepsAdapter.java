package com.example.test.utils;

// Pour formatter les listes

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.business.Keep;

import java.text.SimpleDateFormat;
import java.util.List;

public class KeepsAdapter extends ArrayAdapter<Keep> {
	public KeepsAdapter(Context context, List<Keep> keeps) {
		super(context, 0, keeps);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Keep keep = getItem (position); // Récupère la note
		if (convertView == null) // Crée la vue si nécessaire
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_keep, parent, false);
//		Récupère les id des éléments de la vue
		TextView tvTitre = (TextView) convertView.findViewById(R.id.Keep_Titre);
		TextView tvTexte = (TextView) convertView.findViewById(R.id.Keep_Texte);
		TextView tvDate = (TextView) convertView.findViewById(R.id.Keep_Date);
//		Remplit la vue
		tvTitre.setText(keep.getTitre());
		tvTexte.setText(keep.getTexte());
//		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
//		String date = formatter.format(keep.getDateLimite());
//		tvDate.setText(date);
		tvDate.setText(keep.getDateLimite().toString());
		return convertView;
	}
}