package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.business.Keep;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListItemActivity1 extends AppCompatActivity {
//	Button colorButton = new Button(getApplicationContext());

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_list_item1);
		Button colorButton = findViewById(R.id.buttonColor);

/*		FloatingActionButton fab = findViewById (R.id.buttonColor);
		fab.setOnClickListener (
				new View.OnClickListener () {
					@Override
					public void onClick (View v) {
						ColorPickerDialogBuilder
								.with (getApplicationContext())
								.setTitle ("Choisissez votre couleur")
//								.initialColor (getResources().getColor(R.color.purple_200))
								.wheelType (ColorPickerView.WHEEL_TYPE.FLOWER)
								.density (12)
								.setOnColorSelectedListener (new OnColorSelectedListener() {
									@Override
									public void onColorSelected(int selectedColor) {}
								})
								.setPositiveButton ("OK", new ColorPickerClickListener() {
									@Override
									public void onClick (DialogInterface dialog, int selectedColor, Integer[] allColors) {
										ConstraintLayout cl = findViewById (R.id.testColor);
										cl.setBackgroundColor (selectedColor);
									}
								})
								.setNegativeButton ("Annuler", new DialogInterface.OnClickListener() {
									@Override
									public void onClick (DialogInterface dialog, int which) {}
								})
								.build()
								.show();
					}
				}
		);
*/
	}

	public void dialogColorPicker (View view) {
			ColorPickerDialogBuilder
					.with (getApplicationContext())
					.setTitle ("Choisissez votre couleur")
//								.initialColor (getResources().getColor(R.color.purple_200))
					.wheelType (ColorPickerView.WHEEL_TYPE.FLOWER)
					.density (12)
					.setOnColorSelectedListener (new OnColorSelectedListener() {
						@Override
						public void onColorSelected(int selectedColor) {}
					})
					.setPositiveButton ("OK", new ColorPickerClickListener() {
						@Override
						public void onClick (DialogInterface dialog, int selectedColor, Integer[] allColors) {
							ConstraintLayout cl = findViewById (R.id.testColor);
							cl.setBackgroundColor (selectedColor);
						}
					})
					.setNegativeButton ("Annuler", new DialogInterface.OnClickListener() {
						@Override
						public void onClick (DialogInterface dialog, int which) {}
					})
					.build()
					.show();
//		Toast toast = Toast.makeText(this, "SALAM !", Toast.LENGTH_LONG);
//		toast.show();
	}

}