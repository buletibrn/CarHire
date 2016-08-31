package com.example.carhire;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.example.carhire.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class imgload extends Activity implements OnClickListener{
	 private static int RESULT_LOAD_IMAGE = 1;
	 private ImageView imageview=null;
    private TextView imgpath=null;
	 private Button buttonLoadImage;
	 private Button btninsert=null;
	    private Button btnretrive=null;
	    private MyDataBase mdb=null;
	    private SQLiteDatabase db=null;
	    private Cursor c=null;
	  private EditText nameBox=null;
	    private byte[] img=null;
	    private static final String DATABASE_NAME = "ImaggeDb.db";
	    public static final int DATABASE_VERSION = 1;
	    String p;
	    String n;


	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.imageload);
	        nameBox=(EditText)findViewById(R.id.etName);
	        imgpath=(TextView)findViewById(R.id.Todate);
	        btninsert=(Button)findViewById(R.id.button_insert);
	        btnretrive= (Button)findViewById(R.id.button_retrieve);
	        buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
	        buttonLoadImage.setOnClickListener(this);
	        imageview= (ImageView)findViewById(R.id.imgView);
	        imageview.setImageResource(0);
	       Bitmap b1=BitmapFactory.decodeResource(getResources(), R.drawable.car4);
	       imageview.setImageBitmap(b1);
	        btninsert.setOnClickListener(this);
	        btnretrive.setOnClickListener(this);
	        mdb=new MyDataBase(getApplicationContext(), DATABASE_NAME,null, DATABASE_VERSION);
	       // create table datatable(_id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, price INTEGER);
	        Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
	        //Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
	        ByteArrayOutputStream bos=new ByteArrayOutputStream();
	        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
	        img=bos.toByteArray();
	        db=mdb.getWritableDatabase();
	        /* {

	            @Override
	            public void onClick(View arg0) {

	                Intent i = new Intent(
	                        Intent.ACTION_PICK,
	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

	                startActivityForResult(i, RESULT_LOAD_IMAGE);
	            }
	        });*/
	    }


	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };

	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();

	            ImageView imageView = (ImageView) findViewById(R.id.imgView);
	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	            imgpath.setText( picturePath); 

	        }


	    }


		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 if( buttonLoadImage==v)
		        {

	                Intent i = new Intent(
	                        Intent.ACTION_PICK,
	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

	                startActivityForResult(i, RESULT_LOAD_IMAGE);
		        }
			 if(btninsert==v)
		        {
				 /*
				// Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
				 ContentValues cv=new ContentValues();
		            cv.put("image", img);
		            db.insert("tableimage", null, cv);
		            Toast.makeText(this, "inserted successfully", Toast.LENGTH_SHORT).show();
		          
		            /* try{
						FileInputStream fis=new FileInputStream("imgpath");
						byte[] image=new byte[fis.available()];
						fis.read(image);
						ContentValues values=new ContentValues();
						values.put("a", "image");
						db.insert("tb", null,values);
						Toast.makeText(this,"insert success", Toast.LENGTH_SHORT).show();
						fis.close();
						}
						catch(IOException e){
						e.printStackTrace();	
						}
						  
		            Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		            imageview.setImageBitmap(b);
		            */
				 n=nameBox.getText().toString();
				 
				 Employee employee_One = new Employee(((BitmapDrawable)imageview.getDrawable()).getBitmap(), n, 25);
				 
				
				 sqlite entry=new sqlite(imgload.this);
				 entry.openDB();
				// String ad=entry.getImageTWO(n);
				 entry.insertImageTWO(employee_One);
				 Toast.makeText(getBaseContext(), "Successful",Toast.LENGTH_LONG).show();
		        }
		        else if(btnretrive==v)
		        {
		        	sqlite entry=new sqlite(imgload.this);
					 entry.openDB();
					
					//String[] col={"image"};
		        	/*
	                c=db.query("tableimage", col, null, null, null, null, null);
	               // byte[] image = cursor.getBlob(1);
	                if(c!=null){
	                    c.moveToFirst();
	                    do{
	                        img=c.getBlob(c.getColumnIndex("image"));
	                       }while(c.moveToNext());
	                }
	                Bitmap b1=BitmapFactory.decodeByteArray(img, 0, img.length);
	              
	                 imageview.setImageBitmap(b1);
	                 Toast.makeText(this, "Retrive successfully", Toast.LENGTH_SHORT).show();
		            }
		            */
		        	try{
		        		 n=nameBox.getText().toString();
		        		Employee bp =  entry.getImageTWO();
		
		//ByteArrayInputStream imageStream = new ByteArrayInputStream(b);               
		//Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		//image.setImageBitmap(theImage);               
		 //imageview.setImageBitmap(bp);
		        		}
		 catch(Exception e ){
				
			    
				String era=e.toString();
				Dialog d=new Dialog(this); 
				d.setTitle("oops!!");
				TextView tv=new TextView(this);
				tv.setText(era);
				d.setContentView(tv);
				d.show();
			}
			
		}

}}
