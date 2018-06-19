package com.example.android.githubrepolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {

	private DatabaseOpenHelper   openHelper;
	private SQLiteDatabase       database;

	public DatabaseAdapter(Context context) {
		openHelper = DatabaseOpenHelper.getMyOpenHelper(context);
		open();
	}

	private void open() {
		database = openHelper.getWritableDatabase();
	}

	public DatabaseTableHelper getTable() {
		if (database != null) {
			return (new DatabaseTableHelper(database));			
		} else {
			return (null);			
		}
	}

	
}
