package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.business.Keep;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class ListItemActivity1 extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item1);

		Toast toast = Toast.makeText(this, "Salam", Toast.LENGTH_SHORT);
		toast.setMargin(50,50);

		ColorPickerDialogBuilder
				.with(this)
				.setTitle("Choose color")
				.initialColor(getResources().getColor(R.color.purple_200))
				.wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
				.density(12)
				.setOnColorSelectedListener(new OnColorSelectedListener() {
					@Override
					public void onColorSelected (int selectedColor) {
						toast.setText(selectedColor);
						toast.show();
//						toast ("onColorSelected: 0x" + Integer.toHexString(selectedColor));
					}
				})
				.setPositiveButton("ok", new ColorPickerClickListener() {
					@Override
					public void onClick (DialogInterface dialog, int selectedColor, Integer[] allColors) {
						ConstraintLayout cl = findViewById (R.id.testColor);
						cl.setBackgroundColor(selectedColor);
					}
				})
				.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.build()
				.show();
	}
}