package com.example.test.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.test.business.Keep;

@RequiresApi(api = Build.VERSION_CODES.O)
public class KeepBDHelper extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "notes_db";

	public KeepBDHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		// create notes table
		db.execSQL(Keep.CREATE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + Keep.TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	public long insertKeep(Keep keep) {
		// get writable database as we want to write data
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Keep.COLUMN_TITRE, keep.getTitre());
		values.put(Keep.COLUMN_TEXTE, keep.getTitre());
		values.put(Keep.COLUMN_TAG, keep.getTitre());
		values.put(Keep.COLUMN_BG_COLOR, keep.getTitre());
		// insert row
		long id = db.insert(Keep.TABLE_NAME, null, values);
		// close db connection
		db.close();
		// return newly inserted row id
		return id;
	}

	public Keep getKeep(long id) {
		// get readable database as we are not inserting anything
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(Keep.TABLE_NAME,
				new String[]{Keep.COLUMN_NUM, Keep.COLUMN_TITRE, Keep.COLUMN_TEXTE},
				Keep.COLUMN_NUM + "=?",
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		// prepare note object
		Keep note = new Keep(
				cursor.getString(cursor.getColumnIndex(Keep.COLUMN_TITRE)),
				cursor.getString(cursor.getColumnIndex(Keep.COLUMN_TEXTE)));
		// close the db connection
		cursor.close();
		return note;
	}

/*
	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "keeps.db";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + KeepContract.KeepEntry.TABLE_NAME + " (" +
					KeepContract.KeepEntry._ID + " INTEGER PRIMARY KEY," +
					KeepContract.KeepEntry.COLUMN_NAME_TITRE + " TEXT," +
					KeepContract.KeepEntry.COLUMN_NAME_TEXTE + " TEXT," +
					KeepContract.KeepEntry.COLUMN_NAME_TAG + " TEXT," +
					KeepContract.KeepEntry.COLUMN_NAME_BG_COLOR + " TEXT)";
	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + KeepContract.KeepEntry.TABLE_NAME;

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
		// to simply to discard the data and start over
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
}
*/
}