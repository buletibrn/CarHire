package com.example.carhire;


import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.carhire.Dbcreator;


public class Dbcreator {
	
	public static final String  ID="_id";
	public static final String  ADM="adm";
	public static final String  FNAME="fname";
	public static final String  SNAME="sname";
	public static final String  PASSC="passc";
	
	
	
	private static final String DBNAME="smart";
	private static final String DBTABLE="register";
	
	private static final int DATABASE_VERSION=1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context,DBNAME,null,DATABASE_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		  db.execSQL( "CREATE TABLE " +DBTABLE + "(" +
		            ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
		            ADM+" TEXT NOT NULL, "+
				    FNAME+" TEXT NOT NULL, "+
				    SNAME+" TEXT NOT NULL, "+
		            PASSC+" INTEGER  );"
				     );	
		 
		  db.execSQL("CREATE TABLE images (_id INTEGER PRIMARY KEY AUTOICREMENT, file_path TEXT, name TEXT);");
		 
		  
		  
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			 db.execSQL("DROP TABLE IF EXISTS " + DBTABLE);
			
			 onCreate(db);
		}

		
		
	}
	
	public Dbcreator(Context c){
		ourContext=c;
	}
	
	public Dbcreator open() throws SQLException{
		ourHelper =new DbHelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
		}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String admn, String fname,String lname,int pass) {
		ContentValues cv=new ContentValues();
		cv.put(ADM, fname);
		cv.put(FNAME, fname);
		cv.put(SNAME, lname);
		cv.put(PASSC, pass);
		return ourDatabase.insert(DBTABLE, null,cv);
		
	}
	
	
		
	
	public String getadmin(long passc)  throws SQLException {
		
		String[] columns=new String[]{ID,ADM,FNAME,PASSC};
		Cursor c=ourDatabase.query(DBTABLE,columns,ADM+"="+passc,null,null,null,null);
		if(c!=null){
			c.moveToFirst();
			String admin=c.getString(1);
			return admin;
		}
		return null;
	}
	
	 
	
public String getfname(long passc)  throws SQLException {
		
		String[] columns=new String[]{ID,ADM,FNAME,PASSC};
		Cursor c=ourDatabase.query(DBTABLE,columns,ADM+"="+passc,null,null,null,null);
		if(c!=null){
			c.moveToFirst();
			String fname=c.getString(2);
			return fname;
		}
		return null;
	}
public String getlname(long passc)  throws SQLException {
	
	String[] columns=new String[]{ID,ADM,FNAME,PASSC};
	Cursor c=ourDatabase.query(DBTABLE,columns,ADM+"="+passc,null,null,null,null);
	if(c!=null){
		c.moveToFirst();
		String lname=c.getString(3);
		return lname;
	}
	return null;
}




}