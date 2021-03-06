package com.example.test.utils;

// Pour formatter les listes

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.test.R;
import com.example.test.business.Keep;

import java.text.SimpleDateFormat;
import java.util.List;

public class KeepsAdapter extends ArrayAdapter<Keep> {
	public KeepsAdapter(Context context, List<Keep> keeps) {
		super(context, 0, keeps);
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	public View getView(int position, View convertView, ViewGroup parent) {
		Keep keep = getItem (position); // Récupère la note
		if (convertView == null) // Crée la vue si nécessaire
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_keep, parent, false);
//		Récupère les id des éléments de la vue
		TextView tvTitre = (TextView) convertView.findViewById(R.id.Keep_Titre);
		TextView tvTexte = (TextView) convertView.findViewById(R.id.Keep_Texte);
		TextView tvDate = (TextView) convertView.findViewById(R.id.Keep_Date);
		TextView tvCouleur = (TextView) convertView.findViewById(R.id.Keep_Color);
//		Remplit la vue
		tvTitre.setText(keep.getTitre());
		tvTexte.setText(keep.getTexte());
//		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
//		String date = formatter.format(keep.getDateLimite());
//		tvDate.setText(date);
		tvDate.setText(keep.getDateLimite().toString());
		Log.d("Couleur de la note", keep.getColorString());
		tvCouleur.setText(keep.getColorString());

		convertView.setBackgroundColor(Color.toArgb(keep.getBackgroundColor()));
//		((TextView)convertView).setText((String)getItem(position));
		return convertView;
	}
}