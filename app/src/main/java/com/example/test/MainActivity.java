package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.test.business.Keep;
import com.example.test.storage.KeepDBHelper;
import com.example.test.utils.KeepsAdapter;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	int cpt = 0; // Pour numéroter les titres
	private ArrayList<Keep> keeps = new ArrayList<>();
	private KeepsAdapter keepsAdapter; // Pour formater (?) les keeps
	private ListView listViewKeeps; // Pour afficher la liste des Keeps
	private KeepDBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setRetainInstance (true);
		setContentView(R.layout.activity_main);

//		Test de Base de données
		db = new KeepDBHelper(this);
		long idKeep = db.insertKeep(new Keep("Keep 1", "Un texte de la BDD"));
		Log.d("L'ID du Keep mis dans la BDD :", String.valueOf(idKeep));
		Keep keepDB = db.getKeep(idKeep);
		Log.d("Le titre du Keep recup de la BDD", keepDB.getTitre());
		db.updateKeep("Keep 1", "Nouveau texte");
		keepDB = db.getKeep(idKeep);
		Log.d("L'Keep a jour :", keepDB.getTexte());
		int num = db.deleteKeep("Keep 1");
//		keepDB = db.getKeep(idKeep);
		Log.d("L'Keep delllllete :", String.valueOf(num));

		FloatingActionButton fab = findViewById(R.id.fab); // Bouton flottant
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) { // Quand on clique sur le bouton, ça ouvre un pop-up
				LinearLayout ll = new LinearLayout(MainActivity.this); // Layout pour insérer 2 editText dans le pop-up
				final EditText titreEditText = new EditText(MainActivity.this);
				titreEditText.setHint("Saisissez votre titre"); // Affiche en arrière plan ou on écrit
				ll.addView(titreEditText);
				final EditText textEditText = new EditText(MainActivity.this);
				textEditText.setHint("Saisissez votre texte");
				ll.addView(textEditText);
				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
						.setTitle("Ajouter une nouvelle note").setView(ll) // Récupère le Layout
						.setPositiveButton("Ajouter note", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) { // ça met le texte dans la liste des keeps
								keepsAdapter.add(new Keep(String.valueOf(titreEditText.getText()), String.valueOf(textEditText.getText())));
							}
						})
						.setNegativeButton("Annuler", null)
						.setNeutralButton("Ajouter une note avec date", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
									@RequiresApi(api = Build.VERSION_CODES.O)
									@Override
									public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
										keepsAdapter.add(new Keep(String.valueOf(titreEditText.getText()), String.valueOf(textEditText.getText()), LocalDate.of(year, monthOfYear, dayOfMonth)));
									}
								};
								Calendar c = Calendar.getInstance();
								DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, dateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
								datePickerDialog.show();
							}
						})
						.create();
				dialog.show();
			}
		});

		listViewKeeps = (ListView) findViewById(R.id.lvItems); // Ajout des items dans la list
		keepsAdapter = new KeepsAdapter(this, keeps);
		listViewKeeps = (ListView) findViewById(R.id.lvItems);
		listViewKeeps.setAdapter(keepsAdapter);
		keeps.add(new Keep("Titre " + cpt++, "du Texte etc etc...", 0x999945));
		keeps.add(new Keep("Titre " + cpt++, "Encore un peu de texte :)", 0x655443));
		// Setup remove listener method call
		setupListViewListener();
	}

	private void setupListViewListener() {
		listViewKeeps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					Intent myIntent = new Intent(view.getContext(), ListItemActivity1.class);
					startActivityForResult(myIntent, 0);
				}
				if (position == 1) {
					Intent myIntent = new Intent(view.getContext(), ListItemActivity2.class);
					startActivityForResult(myIntent, 0);
				}
			}
		});
		listViewKeeps.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
/*			@Override // Pour supprimer la note avec un long clic
			public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this) // Pop up pour confirmer la suppression
						.setTitle("Voulez-vous vraiment supprimer cette note ?")
						.setPositiveButton("Supprimer la note", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								keeps.remove(pos);
								keepsAdapter.notifyDataSetChanged();
							}
						})
						.setNegativeButton("Annuler", null)
						.create();
				dialog.show();
				return true; // Marque la fin du clic
			} */ // Commenter jusqu'ici pour tester le color picker

			@Override // Pour sélectionner une couleur de fond pour la note
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				final Context context = MainActivity.this;
				ColorPickerDialogBuilder
						.with(context)
						.setTitle("Choisissez votre couleur de fond")
						.initialColor(0xFFF)
						.wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
						.density(12)
						.setPositiveButton("OK", new ColorPickerClickListener() {
							@RequiresApi(api = Build.VERSION_CODES.O)
							@Override
							public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
								Log.d("CCLLRR : ", String.valueOf(selectedColor));
								keepsAdapter.getItem(position).setBackgroundColor(selectedColor);
//								keeps.get(position).setBackgroundColor(selectedColor);
//								Log.d("CCLLRR kp :", String.valueOf(keeps.get(position).getBackgroundColor()));
//								keepsAdapter.notifyDataSetChanged();
							}
						})
						.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
							}
						})
						.showColorEdit(true)
						.setColorEditTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_bright))
						.build()
						.show();
				return true;
			} // Commenter jusqu'ici pour tester le pop up de suppression
		});
	}


/**
 * Ebauche pour la changement de vue de la page d'accueil
 * Un bouton doit être créé dans les 2 layouts qui sur un onCick appelle cette méthode
 * Mais vraiment pas (!) très fonctionnel (perte de la listView, pas de réutilisation du code, etc...)
 * Si qqn trouve une solution c'est volontiers :))
 */
/*	public void changeLayout(View view) {
		int layout = 1; // A instancier dans MainActivity.class
		if (this.layout == 1)
			setContentView(R.layout.activity_main_linear); // Layout à créer basé sur le LinearConstraint
		else
			setContentView(R.layout.activity_main);
		this.layout = this.layout * -1; // Pour passer du négatif au positif
	}*/
}