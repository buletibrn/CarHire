package com.example.carhire;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SHSPixHelper extends SQLiteOpenHelper {
	 private static int DB_VERSION = 1;
	private static  String DB_NAME = "SHSPix_DB.sql";
    private  String TABLE_NAME = "SHSPix_tbl";
    
    static final String IMAGE="image";
    static final String PLACE="image_tag";
    static final String ID="_id";
    static final String DESC="desc";
    private String QUERY_S="CREATE TABLE"+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
    		+IMAGE+" BlOB,"+PLACE+" TEXT,"+DESC+" TEXT)";
	public SHSPixHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub 
db.execSQL(QUERY_S);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
onCreate(db);
	}

}
