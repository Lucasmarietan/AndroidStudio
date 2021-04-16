package com.devmobile.keephegelite.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.devmobile.keephegelite.R;
import com.devmobile.keephegelite.business.Keep;
import com.devmobile.keephegelite.storage.KeepDBHelper;

// TODO : Ne pas ajouter si vide !!!

@RequiresApi(api = Build.VERSION_CODES.O)
public class NewKeep extends AppCompatActivity {
	private KeepDBHelper db;
	private EditText titre;
	private EditText texte;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_keep);
		db = new KeepDBHelper(this);
		titre = (EditText) findViewById(R.id.New_Keep_Titre);
		texte = (EditText) findViewById(R.id.New_Keep_Texte);
		titre.setHint("Votre titre ici");
		texte.setHint("Votre texte ici");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (!titre.getText().toString().isEmpty() || !texte.getText().toString().isEmpty()) {
			db.insertKeep(new Keep(titre.getText().toString(), texte.getText().toString()));
		}
	}
}