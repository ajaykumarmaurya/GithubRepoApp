package com.example.android.githubrepolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	/**
	 * There will be a singleton instance of {@link DatabaseOpenHelper}
	 */
	private static DatabaseOpenHelper mInstance;
	/**
	 * Name of database
	 */
	private static final String DATABASE_NAME = "github.db";
	/**
	 * Database version
	 */
	private static final int DATABASE_VERSION =1;

	/**
	 * Concrete factory method for {@link DatabaseOpenHelper}, use by
	 * {@link DatabaseAdapter} class to get the instance of this class.
	 * 
	 * @param context
	 * @return
	 */
	public static DatabaseOpenHelper getMyOpenHelper(Context context) {
		if (mInstance == null) {
			mInstance = new DatabaseOpenHelper(context);
		}
		return mInstance;
	}

	/**
	 * {@link DatabaseOpenHelper} constructor that defines the database name including
	 * database version.
	 * 
	 * @param context
	 */
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		DatabaseTableHelper.createTable(db);



	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if(newVersion>oldVersion){
			 //db.execSQL("DROP TABLE IF EXISTS push" );

			 DatabaseTableHelper.createTable(db);
		}
	

	}

}
