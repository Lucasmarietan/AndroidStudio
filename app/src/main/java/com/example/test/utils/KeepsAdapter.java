package com.example.test.utils;

// Pour formatter les listes

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.business.Keep;

import java.util.List;

public class KeepsAdapter extends ArrayAdapter<Keep> {
	public KeepsAdapter(Context context, List<Keep> keeps) {
		super(context, 0, keeps);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Keep keep = getItem (position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null)
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_keep, parent, false);
		// Lookup view for data population
		TextView tvTitre = (TextView) convertView.findViewById(R.id.Keep_Titre);
		TextView tvTexte = (TextView) convertView.findViewById(R.id.Keep_Texte);
		// Populate the data into the template view using the data objec
		tvTitre.setText(keep.getTitre());
		tvTexte.setText(keep.getTexte());
//		tvTitre.setTextColor(keep.getBackgroundColor()); // Fonctionne pas (fais disparaitre le texte)
//		tvTexte.setTextColor(keep.getBackgroundColor()); // Fonctionne pas (fais disparaitre la texte)
//		convertView.setBackgroundColor(121212); // Rien ne change...
		// Return the completed view to render on screen
		return convertView;
	}
}