package com.example.carhire;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class imgpath extends Activity implements OnClickListener{
	 private static int RESULT_LOAD_IMAGE = 1;
	 private ImageView imageview=null;
    private TextView imgpath=null;
	 private Button buttonLoadImage;
	 private Button btninsert=null;
	    private Button btnretrive=null;
	  private  TextView tv=null;
	  private  EditText nameBox=null;
	    private MyDataBase mdb=null;
	    private SQLiteDatabase db=null;
	    private Cursor c=null;
	    private byte[] img=null;
	    private static final String DATABASE_NAME = "ImaggeDb.db";
	    public static final int DATABASE_VERSION = 1;
	    String p;
	    String n;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.imageload);
		 inilize();
	}
	private void inilize() {
		// TODO Auto-generated method stub
		nameBox=(EditText)findViewById(R.id.etName);
		tv=(TextView)findViewById(R.id.tV);
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

        }}
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
		 p= imgpath.getText().toString();
		 n=nameBox.getText().toString();
		// Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
		 sqlite entry=new sqlite(imgpath.this);
		 entry.openDB();
		 entry.insertImage(p, n);
		 Toast.makeText(getBaseContext(), "Successful",Toast.LENGTH_LONG).show();
        }
        else if(btnretrive==v)
        {
        	n=nameBox.getText().toString();
        	boolean diditwork=true;
        	try{
        		
        	sqlite entry=new sqlite(imgpath.this);
			 entry.openDB();
			 String ad=entry.getImage(n);
			// tv.setText(ad);
			 Cursor cc = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,null);
			 startManagingCursor(cc);
			 //ImageView im = new ImageView(mContext); 
			 
        	ImageView imaggeView = (ImageView) findViewById(R.id.imgView);
    		Bitmap b = BitmapFactory.decodeFile(ad);
    		imaggeView.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, ""+ad));
    		// imaggeView.setImageBitmap(BitmapFactory.decodeFile(ad));
             // imaggeView.setImageBitmap(b);
    		 }
        	catch(Exception e ){
        		 diditwork=false;
			    String era=e.toString();
				Dialog d=new Dialog(this); 
				d.setTitle("oops!!");
				TextView tv=new TextView(this);
				tv.setText(era);
				d.setContentView(tv);
				d.show();
			}
			// ImageView imaggeView = (ImageView) findViewById(R.id.imgView);
        		//Bitmap b = BitmapFactory.decodeFile(ad);
                  //imaggeView.setImageBitmap(b);
        		       


//ByteArrayInputStream imageStream = new ByteArrayInputStream(b);               
//Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//image.setImageBitmap(theImage);               
 
	
}
		
	}
}
