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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item1);
		root = findViewById(R.id.testColor);
		findViewById(R.id.buttonColor).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Context context = ListItemActivity1.this;
				ColorPickerDialogBuilder
						.with(context)
						.setTitle("Choisissez votre couleur de fond")
						.initialColor(currentBackgroundColor)
						.wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
						.density(12)
						.setPositiveButton("OK", new ColorPickerClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
								currentBackgroundColor = selectedColor;
								root.setBackgroundColor(selectedColor);
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
}