package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.test.business.Keep;
import com.example.test.utils.KeepsAdapter;

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

		// Ajout des items dans la list
		listViewKeeps = (ListView) findViewById(R.id.lvItems);
//		keeps = new ArrayList<Keep>();
//		readFromFile();
		keepsAdapter = new KeepsAdapter(this, keeps);
//		itemsAdapter = new ArrayAdapter<Keep>(this, android.R.layout.simple_list_item_1, items);
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
		listViewKeeps.setOnItemLongClickListener(
				new AdapterView.OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
						// Remove the item within array at position
						keeps.remove(pos);
						// Refresh the adapter
						keepsAdapter.notifyDataSetChanged();
						// Return true consumes the long click event (marks it handled)
//						writeToFile();
						return true;
					}
				});
	}

	public void onAddItem(View view) {
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		String itemText = etNewItem.getText().toString();
		keepsAdapter.add(new Keep("Titre " + cpt++, itemText));
		etNewItem.setText("");
//		writeToFile();
	}
}