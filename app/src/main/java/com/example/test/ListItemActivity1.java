package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test.business.Keep;

public class ListItemActivity1 extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item1);
		Bundle b = getIntent().getExtras();
		int value = -1; // or other values
		if(b != null)
			value = b.getInt("key");
		Keep keep = getIntent().getExtras().getParcelable("Keep");
		TextView textView = findViewById(R.id.kkey);
		textView.setText(value);
//		TextView textView = findViewById(R.layout.activity_list_item1);
	}
}