package com.example.carhire;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Img extends Activity {
	 private static int DB_VERSION = 1;
		private static  String DB_NAME = "SHSPix_DB.sql";
	    private  String TABLE_NAME = "SHSPix_tbl";
	    
	private static final int REQUEST_PICK_FILE = 1;
	 File selectedFile;
	 Button submit,browsebutton;
	 Intent intent;
	 EditText imgpath;
	 String filepath;
	 TextView  textView;
	 SQLiteDatabase database;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.evano);
	  
	  submit=(Button)findViewById(R.id.save1);
	  browsebutton=(Button)findViewById(R.id.browse1);
	  imgpath=(EditText)findViewById(R.id.etPhone);
	  submit.setOnClickListener(new OnClickListener() {
	   
	   @Override
	   public void onClick(View v) {
	    // TODO Auto-generated method stub
	    
	    insertDetails();
	    createTable();
	    saveInDB();
	   }

	   private void insertDetails() {
	    // TODO Auto-generated method stub
	    database=openOrCreateDatabase("imagedb", MODE_PRIVATE, null);
	    database.execSQL("create table if not exists imagestore(id integer primary key autoincrement,imagestore blob)");
	    
	   }
	  } );
	  browsebutton.setOnClickListener(new OnClickListener() {
	   
	   @Override
	   public void onClick(View v) {
	    // TODO Auto-generated method stub
	    intent = new Intent(Img.this,FilePickerActivity.class); 
	    startActivityForResult(intent, REQUEST_PICK_FILE);
	   }
	  });
	 }
	 
	 
	 
	 
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if(resultCode == RESULT_OK) {
	   switch(requestCode) {
	   case REQUEST_PICK_FILE:
	    if(data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
	     // Get the file path
	     selectedFile = new File(data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));
	     // Set the file path text view
	   imgpath.setText(selectedFile.getPath());  
	     //Now you have your selected file, You can do your additional requirement with file.
	     filepath=selectedFile.getPath();
	    }
	   }
	  }
	  }
	 void createTable() {


	        SQLiteDatabase myDb = openOrCreateDatabase(DB_NAME,


	                Context.MODE_PRIVATE, null);


	        String MySQL = "create table if not exists "


	                + TABLE_NAME


	                + " (_id INTEGER primary key autoincrement, name TEXT not null, image BLOB);";


	        myDb.execSQL(MySQL);


	        myDb.close();


	    }

	 void saveInDB() {
		String Immagepath=imgpath.toString();


	        SQLiteDatabase myDb = openOrCreateDatabase(DB_NAME,


	                Context.MODE_PRIVATE, null);


	        byte[] byteImage1 = null;


	        String s = myDb.getPath();


	        myDb.execSQL("delete from " + TABLE_NAME);          // clearing <span id="IL_AD8" class="IL_AD">the table</span>


	        ContentValues newValues = new ContentValues();


	        String name = "CoderzHeaven";


	        newValues.put("name", name);


	        try {


	            FileInputStream instream = new FileInputStream(Immagepath);


	            BufferedInputStream bif = new BufferedInputStream(instream);


	            byteImage1 = new byte[bif.available()];


	            bif.read(byteImage1);


	            newValues.put("image", byteImage1);


	            long ret = myDb.insert(TABLE_NAME, null, newValues);


	            if (ret < 0)


	                textView.append("Error");
	            Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_LONG).show();

	        } catch (IOException e) {


	            textView.append("Error Exception : " + e.getMessage());


	        }


	        myDb.close();


	}
	  }
