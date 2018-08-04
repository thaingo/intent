package com.example.thaingo.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.thaingo.criminalintent.database.CrimeBaseHelper;
import com.example.thaingo.criminalintent.database.CrimeDbSchema;
import com.example.thaingo.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
    }

    public Crime getCrime(UUID id) {
        return null;
    }

    public void addCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
        mSQLiteDatabase.insert(CrimeTable.NAME, null, values);
    }

    public void updateCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
        mSQLiteDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?",
                               new String[]{crime.getId().toString()});
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Cols.UUID, crime.getId().toString());
        contentValues.put(CrimeTable.Cols.TITLE, crime.getTitle());
        contentValues.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        contentValues.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return contentValues;
    }

    private Cursor queryCrimes(String whereClause, String[] whereArgs) {
        return mSQLiteDatabase.query(CrimeTable.NAME, null, whereClause, whereArgs, null, null, null);
    }
}
