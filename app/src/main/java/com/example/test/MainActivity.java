package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.test.business.Keep;
import com.example.test.utils.KeepsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	int cpt = 0; // Pour numéroter les titres
	private ArrayList<Keep> keeps = new ArrayList<>();
	private KeepsAdapter keepsAdapter; // Pour formater (?) les keeps
	private ListView listViewKeeps; // Pour afficher la liste des Keeps

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setRetainInstance (true);
		setContentView(R.layout.activity_main);

		FloatingActionButton fab = findViewById(R.id.fab); // Bouton flottant
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) { // Quand on clique sur le bouton, ça ouvre un pop-up
				final EditText taskEditText = new EditText(MainActivity.this);
				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
						.setTitle("Add a new task")
						.setMessage("What do you want to do next?")
						.setView(taskEditText)
						.setPositiveButton("Add", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) { // ça met le texte dans la liste des keeps
								String task = String.valueOf(taskEditText.getText());
								keepsAdapter.add(new Keep("Titre " + cpt++, task));
							}
						})
						.setNegativeButton("Cancel", null)
						.create();
				dialog.show();
			}
		});

		// Ajout des items dans la list
		listViewKeeps = (ListView) findViewById(R.id.lvItems);
		keepsAdapter = new KeepsAdapter(this, keeps);
		listViewKeeps = (ListView) findViewById(R.id.lvItems);
		listViewKeeps.setAdapter(keepsAdapter);
		keeps.add(new Keep ("Titre " + cpt++, "du Texte etc etc..."));
		keeps.add(new Keep ("Titre " + cpt++, "Encore un peu de texte :)"));

		// Setup remove listener method call
		setupListViewListener();
	}

	private void setupListViewListener() {
		listViewKeeps.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				listViewKeeps.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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
			}
		});
		listViewKeeps.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
						// Remove the item within array at position
						keeps.remove(pos);
						// Refresh the adapter
						keepsAdapter.notifyDataSetChanged();
						// Return true consumes the long click event (marks it handled)
						return true;
					}
				});
	}
}