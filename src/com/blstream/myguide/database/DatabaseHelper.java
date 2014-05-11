
package com.blstream.myguide.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "VisitedAnimals";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_ANIMAL_ID = "animal_id";
	public static final String COLUMN_VISITED = "visited";

	private static final String DATABASE_NAME = "MyGuide.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( "
			+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_ANIMAL_ID + " INTEGER, "
			+ COLUMN_VISITED + " INTEGER DEFAULT 0);";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
}
