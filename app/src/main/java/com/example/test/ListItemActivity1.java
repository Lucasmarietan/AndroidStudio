package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.business.Keep;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListItemActivity1 extends AppCompatActivity {
	private View root;
	private int currentBackgroundColor = 0xffffffff;
//	Button colorButton = new Button(getApplicationContext());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item1);
		root = findViewById(R.id.testColor);
//		Button colorButton = findViewById(R.id.buttonColor);
		findViewById(R.id.buttonColor).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Context context = ListItemActivity1.this;

				ColorPickerDialogBuilder
						.with(context)
						.setTitle("Choisissez votre couleur !")
						.initialColor(currentBackgroundColor)
						.wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
						.density(12)
						.setOnColorChangedListener(new OnColorChangedListener() {
							@Override
							public void onColorChanged(int selectedColor) {
								// Handle on color change
								Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
							}
						})
						.setOnColorSelectedListener(new OnColorSelectedListener() {
							@Override
							public void onColorSelected(int selectedColor) {
								toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
							}
						})
						.setPositiveButton("OK", new ColorPickerClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
								changeBackgroundColor(selectedColor);
								if (allColors != null) {
									StringBuilder sb = null;

									for (Integer color : allColors) {
										if (color == null)
											continue;
										if (sb == null)
											sb = new StringBuilder("Color List:");
										sb.append("\r\n#" + Integer.toHexString(color).toUpperCase());
									}

									if (sb != null)
										Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
								}
							}
						})
						.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
							}
						})
						.showColorEdit(true)
						.setColorEditTextColor(ContextCompat.getColor(ListItemActivity1.this, android.R.color.holo_blue_bright))
						.build()
						.show();
			}
		});
	}

	private void changeBackgroundColor(int selectedColor) {
		currentBackgroundColor = selectedColor;
		root.setBackgroundColor(selectedColor);
	}

	private void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

/*	public void dialogColorPicker (View view) {
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
*/
}