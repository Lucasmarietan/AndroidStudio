package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.test.business.Keep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private ArrayList<Keep> items;
	private ArrayAdapter<Keep> itemsAdapter;
	private ListView lvItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setRetainInstance (true);
		setContentView(R.layout.activity_main);

		// Ajout des items dans la list
		lvItems = (ListView) findViewById(R.id.lvItems);
		items = new ArrayList<Keep>();
//		readFromFile();
		itemsAdapter = new ArrayAdapter<Keep>(this,
				android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);
		items.add(new Keep ("Titre 1", "Texte 2 etc etc..."));
		items.add(new Keep ("Titre 2", "Encore un peu de texte :)"));

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
		lvItems.setOnItemLongClickListener(
				new AdapterView.OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> adapter,
												   View item, int pos, long id) {
						// Remove the item within array at position
						items.remove(pos);
						// Refresh the adapter
						itemsAdapter.notifyDataSetChanged();
						// Return true consumes the long click event (marks it handled)
//						writeToFile();
						return true;
					}
				});
	}

	public void onAddItem(View view) {
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		String itemText = etNewItem.getText().toString();
		itemsAdapter.add(new Keep("TT", itemText));
		etNewItem.setText("");
//		writeToFile();
	}
}