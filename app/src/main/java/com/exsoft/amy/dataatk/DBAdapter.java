package com.exsoft.amy.dataatk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exsoft.amy.dataatk.domain.ATK;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amy on 09/11/15.
 */
public class DBAdapter extends SQLiteOpenHelper {

    private static final String DB_NAME = "dataatk";
    private static final String TABLE_NAME = "barang";
    private static final String COL_ID = "id";
    private static final String COL_NAMABARANG = "namabarang";
    private static final String COL_MEREK = "merek";
    private static final String COL_SATUAN = "satuan";
    private static final String COL_JUMLAH = "jumlah";
    private static final String COL_HARGA = "harga";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "
            + TABLE_NAME + ";";
    private SQLiteDatabase sqliteDatabase = null;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL(DROP_TABLE);
    }

    public void openDB() {
        if (sqliteDatabase == null) {
            sqliteDatabase = getWritableDatabase();
        }
    }

    public void closeDB() {
        if (sqliteDatabase != null) {
            if (sqliteDatabase.isOpen()) {
                sqliteDatabase.close();
            }
        }
    }

    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + COL_NAMABARANG + " TEXT," + COL_MEREK + " TEXT" + COL_SATUAN + " TEXT" + COL_JUMLAH + " TEXT" + COL_HARGA + " TEXT);");
    }

    public void updateAtk(ATK atk) {
        sqliteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAMABARANG, atk.getNamabarang());
        cv.put(COL_MEREK, atk.getMerek());
        cv.put(COL_SATUAN, atk.getSatuan());
        cv.put(COL_JUMLAH, atk.getJumlah());
        cv.put(COL_HARGA, atk.getHarga());
        String whereClause = COL_ID + "==?";
        String whereArgs[] = new String[]{atk.getId()};
        sqliteDatabase.update(TABLE_NAME, cv, whereClause, whereArgs);
        sqliteDatabase.close();
    }

    public void save(ATK atk) {
        sqliteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAMABARANG, atk.getNamabarang());
        contentValues.put(COL_MEREK, atk.getMerek());
        contentValues.put(COL_SATUAN, atk.getMerek());
        contentValues.put(COL_JUMLAH, atk.getMerek());
        contentValues.put(COL_HARGA, atk.getMerek());

        sqliteDatabase.insertWithOnConflict(TABLE_NAME, null,
                contentValues, SQLiteDatabase.CONFLICT_IGNORE);

        sqliteDatabase.close();
    }

    public void delete(ATK atk) {
        sqliteDatabase = getWritableDatabase();
        String whereClause = COL_ID + "==?";
        String[] whereArgs = new String[]{String.valueOf(atk.getId())};
        sqliteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
        sqliteDatabase.close();
    }

    public void deleteAll() {
        sqliteDatabase = getWritableDatabase();
        sqliteDatabase.delete(TABLE_NAME, null, null);
        sqliteDatabase.close();
    }

    public List<ATK> getAllAtk() {
        sqliteDatabase = getWritableDatabase();

        Cursor cursor = this.sqliteDatabase.query(TABLE_NAME, new String[]{
                COL_ID, COL_NAMABARANG, COL_MEREK, COL_SATUAN, COL_JUMLAH, COL_HARGA}, null, null, null, null, null, null);
        List<ATK> atks = new ArrayList<ATK>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ATK atk = new ATK();
                ATK.setId(cursor.getString(cursor.getColumnIndex(COL_ID)));
                ATK.setNamabarang(cursor.getString(cursor.getColumnIndex(COL_NAMABARANG)));
                ATK.setMerek(cursor.getString(cursor.getColumnIndex(COL_MEREK)));
                ATK.setSatuan(cursor.getString(cursor.getColumnIndex(COL_SATUAN)));
                ATK.setJumlah(cursor.getString(cursor.getColumnIndex(COL_JUMLAH)));
                ATK.setHarga(cursor.getString(cursor.getColumnIndex(COL_HARGA)));
                atks.add(atk);
            }
            sqliteDatabase.close();
            return atks;
        } else {
            sqliteDatabase.close();
            return new ArrayList<ATK>();
        }
    }

    public void updateATK(ATK editATK) {
    }
}
