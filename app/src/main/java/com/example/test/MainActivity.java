package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.test.business.Keep;
import com.example.test.utils.KeepsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
			public void onClick(View view) { // Quand on clique sur le bouton, ça ajoute le texte dans keeps
				EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
				String itemText = etNewItem.getText().toString();
				keepsAdapter.add(new Keep("Titre " + cpt++, itemText));
				etNewItem.setText("");
			}
		});

		// Ajout des items dans la list
		listViewKeeps = (ListView) findViewById(R.id.lvItems);
//		readFromFile();
		keepsAdapter = new KeepsAdapter(this, keeps);
		listViewKeeps = (ListView) findViewById(R.id.lvItems);
		listViewKeeps.setAdapter(keepsAdapter);
		keeps.add(new Keep ("Titre " + cpt++, "du Texte etc etc..."));
		keeps.add(new Keep ("Titre " + cpt++, "Encore un peu de texte :)"));

		// Setup remove listener method call
		setupListViewListener();
	}

/*	private void writeToFile() {
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("rieder.txt", Context.MODE_PRIVATE));
			outputStreamWriter.write(String.valueOf(items));
			outputStreamWriter.close();
		}
		catch (IOException e) {
			Log.e("Exception", "File write failed: " + e.toString());
		}
	}

	private String readFromFile () {
		String ret = "";
		try {
			InputStream inputStream = openFileInput("rieder.txt");
			if ( inputStream != null ) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null )
					stringBuilder.append("\n").append(receiveString);
				inputStream.close();
				ret = stringBuilder.toString();
			}
		}
		catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}
		return ret;
	}
 */
/*	private void readItems () {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			items = new ArrayList<String> (FileUtils.readLines (todoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
		}
	}

	private void writeItems () {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines (todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
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
/*				if (position == 0) {
					Intent myIntent = new Intent(view.getContext(), ListItemActivity1.class);
					myIntent.putExtra("Keep", keeps.get(position));
//					Bundle b = new Bundle();
//					b.putInt("key", 12345);
//					myIntent.putExtras(b);
					try {
						startActivity(myIntent);
					} catch (ActivityNotFoundException e) {

						// Define what your app should do if no activity can handle the intent.
					}

					startActivity(myIntent);
//					startActivityForResult(myIntent, 0);
//					finish();
				}
*/				/*if (position == 1) {
					Intent myIntent = new Intent(view.getContext(), ListItemActivity1.class);
					Bundle b = new Bundle();
					b.putInt("key", 54321);
					myIntent.putExtras(b);
					startActivity(myIntent);
//					startActivityForResult(myIntent, 0);
//					finish();
				}*/
//				KeepsAdapter item = (KeepsAdapter) parent.getItemAtPosition(position);
//				Intent intent = new Intent(view.getContext(), KeepsAdapter.class);
				//based on item add info to intent
//				startActivity(intent);
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