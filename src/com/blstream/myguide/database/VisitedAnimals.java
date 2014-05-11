
package com.blstream.myguide.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.blstream.myguide.zoolocations.Animal;

public class VisitedAnimals {

	private DatabaseHelper mDatabaseHelper;

	public VisitedAnimals(Context ctx) {
		mDatabaseHelper = new DatabaseHelper(ctx);
	}

	public void setVisited(Animal animal, boolean visited) {
		if (animal == null) {
			throw new IllegalArgumentException();
		}
		int visitedInt = visited ? 1 : 0;
		try {
			SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
			String where = String.format("%s = %s", DatabaseHelper.COLUMN_ANIMAL_ID, animal.getId());
			Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, where, null, null, null,
					null, null);
			cursor.moveToFirst();
			if (cursor.isAfterLast()) {
				ContentValues values = new ContentValues();
				values.put(DatabaseHelper.COLUMN_ANIMAL_ID, animal.getId());
				values.put(DatabaseHelper.COLUMN_VISITED, visitedInt);
				db.insert(DatabaseHelper.TABLE_NAME, null, values);
			}
			else {
				ContentValues values = new ContentValues();
				values.put(DatabaseHelper.COLUMN_VISITED, visitedInt);
				db.update(DatabaseHelper.TABLE_NAME, values, where, null);
			}
			mDatabaseHelper.close();
		} catch (SQLiteException e) {
		}
	}

	public boolean isVisited(Animal animal) {
		boolean visited = false;
		if (animal == null) {
			throw new IllegalArgumentException();
		}
		try {
			SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
			String where = String.format("%s = %s", DatabaseHelper.COLUMN_ANIMAL_ID, animal.getId());
			Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, where, null, null, null,
					null, null);
			cursor.moveToFirst();
			if (!cursor.isAfterLast() && cursor.getInt(2) == 1) {
				visited = true;
			}
			cursor.close();
			mDatabaseHelper.close();
		} catch (SQLiteException e) {
		}
		return visited;
	}

	public void clear() {
		try {
			SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
			ContentValues values = new ContentValues();
			values.put(DatabaseHelper.COLUMN_VISITED, 0);
			db.update(DatabaseHelper.TABLE_NAME, values, null, null);
			mDatabaseHelper.close();
		} catch (SQLiteException e) {
		}
	}
}
