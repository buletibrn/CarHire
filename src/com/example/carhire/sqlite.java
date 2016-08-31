package com.example.carhire;



import java.io.ByteArrayInputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

public class sqlite extends SQLiteOpenHelper {

	private static  String DB_NAME = "ussingsqlit";
    private static int DB_VERSION_NUMBER = 1;
    private  String TABLENAME = "register";
    private  String TABLEIMAGES = "tableimages";
    private  String TABLEIMAGESTWO = "tableimagestwo";
    private ImageView empphoto =null;
    public static final String  IDDD="iddd";
    public static final String  FILEPATH="file_path";
    public static final String  FILE_P="file_p";
    public static final String  AGE="age";
    public static final String  NAME="name";
    public static final String  NNAME="nname";
    private  String ITEMS_TABLE = "imgtable";
    public static final String COLUMN_ITEM_ID="cid";
    public static final String COLUMN_ITEM_NAME="cname";
    public static final String COLUMN_ITEM_IMAGE="pictureData";
    public static final String  ID="_id";
	public static final String  FNAME="fname";
	public static final String  SNAME="sname";
	public static final String  EMAIL="email";
	public static final String  IDNO="idno";
	public static final String  MOBILENO="mobileno";
	public static final String  CREDITNO="creditno";
	public static final String  USERNAME="username";
	public static final String  PASSWORD="password";
	
	
	
	 private  String  IMG="create table "+ITEMS_TABLE +
			 "( id INTEGER PRIMARY KEY NOT NULL UNIQUE, cname TEXT, pictureData TEXT););)";
	
	 private  String  CREATES="create table "+TABLENAME +
     " (_id integer primary key autoincrement, fname text not null,sname text not null,email text not null,idno long,mobileno long,creditno long,username text not null,password text not null,file_path TEXT, name TEXT );)";
	  private String IMAGETB="create table "+TABLEIMAGESTWO+" (idd INTEGER PRIMARY KEY AUTOINCREMENT, file_path TEXT, name TEXT);)"; 
	  private String IMAGETBTWO="create table "+TABLEIMAGES+" (iddd INTEGER PRIMARY KEY AUTOINCREMENT, file_p BLOB, nname TEXT,age INT);)"; 
	 private SQLiteDatabase DBInstance = null;
	 public sqlite(Context context)
	    {
	    	super(context, DB_NAME, null, DB_VERSION_NUMBER);
	    }
	
	

		@Override
		public void onCreate(SQLiteDatabase sqliteDBInstance) 
	    {
	        Log.i("onCreate", "Creating the database...");
	        sqliteDBInstance.execSQL( CREATES);
	        
	        Log.i("onCreate", "Creating the database...");
	        sqliteDBInstance.execSQL( IMG);
	       
	        Log.i("onCreate", "Creating the database...");
	        sqliteDBInstance.execSQL( IMAGETB);
	        Log.i("onCreate", "Creating the database...");
	        sqliteDBInstance.execSQL( IMAGETBTWO);
	        
	        
	      
	        
	    }

	
		 public void openDB() throws SQLException
		    {
		        Log.i("openDB", "Checking sqliteDBInstance...");
		        if(this.DBInstance == null)
		        {
		            Log.i("openDB", "Creating sqliteDBInstance...");
		            this.DBInstance = this.getWritableDatabase();
		        }
		    }
		
	
	
		 public void closeDB()
		    {
		        if(this.DBInstance != null)
		        {
		            if(this.DBInstance.isOpen())
		                this.DBInstance.close();
		        }
		    }
		
		 public Bitmap  getAllImages() 
		 { 
		     SQLiteDatabase db=this.getWritableDatabase(); 




		  //Cursor cur= db.rawQuery("Select "+colID+" as _id , "+colName+", "+colAge+" from "+employeeTable, new String [] {}); 
		     Cursor cur= db.rawQuery("SELECT * FROM "+ITEMS_TABLE,null); 
		     //if(cur.getCount() > 0){
		         //Cursor c = mDb.query(" ... "); 
		         cur.moveToFirst(); 




		 ByteArrayInputStream inputStream = new ByteArrayInputStream(cur.getBlob(cur.getColumnIndex(COLUMN_ITEM_IMAGE))); 
		         Bitmap b = BitmapFactory.decodeStream(inputStream); 
		    // }
		 return b;
		 } 
		 public long insertuser(String fname,String sname ,String email,Long idno,Long mobileno,Long creditno,String username,String password)throws SQLException
		    {
		        ContentValues contentValues = new ContentValues();
		        contentValues.put(FNAME, fname);
		        contentValues.put(SNAME, sname);
		        contentValues.put(EMAIL, email);
		        contentValues.put(IDNO, idno);
		        contentValues.put(MOBILENO,mobileno);
		        contentValues.put(CREDITNO,creditno);
		        contentValues.put(USERNAME,username);
		        contentValues.put(PASSWORD,password);
		      
		        
		        
		        return this.DBInstance.insert(TABLENAME, null, contentValues);
		    }
		 public String getadmin(String adm) {
				//String[] column=new String[]{ADM};
			 Cursor c=DBInstance.rawQuery("SELECT * FROM register WHERE fname='"+adm+"'", null);
				String result ="";
				
				int iRow=c.getColumnIndex(FNAME);
				
				
				for(c.moveToFirst();!c.isAfterLast();c.moveToNext() ){
				   result=result+c.getString(iRow)+"\n";
				}
				return result;
			}
		 public String getNames(Long idno) {
				//String[] column=new String[]{ADM};
			 Cursor c=DBInstance.rawQuery("SELECT * FROM register WHERE IDNO='"+idno+"'", null);
				String result ="";
				
				int iRow=c.getColumnIndex(IDNO);
				
				
				for(c.moveToFirst();!c.isAfterLast();c.moveToNext() ){
				   result=result+c.getString(iRow)+"\n";
				}
				return result;
			}
		 public String getImage(String name)
		 {
			 Cursor c=DBInstance.rawQuery("SELECT * FROM tableimagesTWO WHERE name='"+name+"'", null);
				String result ="";
				
				int iRow=c.getColumnIndex(FILEPATH);
				for(c.moveToFirst();!c.isAfterLast();c.moveToNext() ){
				   result=result+c.getString(iRow)+"\n";
				}
				return result;
				
				
		 }
		 public Employee getImageTWO()throws SQLException
		 {
			 Cursor c=DBInstance.query(true,TABLEIMAGES,new String[] { FILE_P,
					    NNAME, AGE }, null, null, null, null, null, null);
			 while(c.moveToFirst() ){			
				byte[] blob=c.getBlob(c.getColumnIndex("(FILE_P"));
				String namme = c.getString(c.getColumnIndex(NAME));
				   int age = c.getInt(c.getColumnIndex(AGE));
				   c.close();
					Bitmap b1=BitmapFactory.decodeByteArray(blob, 0, blob.length);
					 empphoto.setImageBitmap(b1);
				   return new Employee(Utility.getPhoto(blob),namme, age);
				}
				
				c.close();
				  return null;
				
		 }
		 public long insertImage(String path,String name)throws SQLException
		 {

		        ContentValues contentValues = new ContentValues();
		        contentValues.put(FILEPATH, path);
		        contentValues.put(NAME, name);
		       
		        
		        
		        return this.DBInstance.insert(TABLEIMAGESTWO,null, contentValues);
}
		 public long insertImageTWO(Employee employee)throws SQLException
		 {

		        ContentValues contentValues = new ContentValues();
		        contentValues.put( FILE_P, Utility.getBytes(employee.getBitmap()));
		        contentValues.put(NNAME, employee.getNameOne());
		        contentValues.put(AGE, employee.getAgeOne());
		       
		        
		        
		        return this.DBInstance.insert(TABLEIMAGES,null, contentValues);

}
		 public String getLogin(String adm,String pass) {
				//String[] column=new String[]{ADM};
			 Cursor c=DBInstance.rawQuery("SELECT * FROM register WHERE username='"+adm+"' and password='"+pass+"'", null);
				String result ="";
		
				int iRow=c.getColumnIndex(PASSWORD);
				
				
				for(c.moveToFirst();!c.isAfterLast();c.moveToNext() ){
				   result=result+c.getString(iRow)+"\n";
				   
				   
				}
				
				return result;
			}



		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
}
