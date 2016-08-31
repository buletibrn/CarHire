package com.example.carhire;

import com.example.carhire.DBhelper.DatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InsertandRetriveBlobData extends Activity implements OnClickListener{
 private DBhelper DbHelper;
 private static int RESULT_LOAD_IMAGE = 1;
 private ImageView empphoto =null;
private TextView empname=null;
private TextView empage=null;
private TextView imgpath=null;
 private Button buttonLoadImage;
 private Button btninsert=null;
 private Button btnretrive=null;
 //private DatabaseHelper mDbHelper;
 public static final String EMP_ID = "id";
 public static final String EMP_NAME = "name";
 public static final String EMP_AGE = "age";
 public static final String EMP_PHOTO = "photo";
  private DatabaseHelper mDbHelper;
 private SQLiteDatabase mDb;
 Employee employee_One=null;
  private static final String DATABASE_NAME = "EmployessDB.db";
 private static final int DATABASE_VERSION = 1;
  private static final String EMPLOYEES_TABLE = "Employees";

  private static final String CREATE_EMPLOYEES_TABLE = "create table "
   + EMPLOYEES_TABLE + " (" + EMP_ID
   + " integer primary key autoincrement, " + EMP_PHOTO
   + " blob not null, " + EMP_NAME + " text not null unique, "
   + EMP_AGE + " integer );";
  public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.imageload);
  empname = (TextView) findViewById(R.id.Todate);
  btninsert=(Button)findViewById(R.id.button_insert);
  btnretrive= (Button)findViewById(R.id.button_retrieve);
  empage = (TextView) findViewById(R.id.age);
  imgpath=(TextView) findViewById(R.id.tV);
  buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
  buttonLoadImage.setOnClickListener(this);
   empphoto = (ImageView) findViewById(R.id.imgView);
   empphoto.setImageResource(0);
  Bitmap b1=BitmapFactory.decodeResource(getResources(), R.drawable.car4);
  empphoto.setImageBitmap(b1);
  btninsert.setOnClickListener(this);
  btnretrive.setOnClickListener(this);
  DbHelper = new DBhelper(this);
  employee_One = new Employee(BitmapFactory.decodeResource(
    getResources(), R.drawable.car4), "Surya", 25);
 
 
  
  
  empname.setText(employee_One.getName());
 
  empphoto.setImageBitmap(employee_One.getBitmap());
  
  empage.setText("" + employee_One.getAge());

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

          empphoto = (ImageView) findViewById(R.id.imgView);
          empphoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
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
		// Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
		 Employee employee_One = new Employee(((BitmapDrawable)empphoto.getDrawable()).getBitmap(), "Surya", 25);
		 DbHelper.open();
		  DbHelper.insertEmpDetails(employee_One);
		  DbHelper.close();
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
				  */
         Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
         empphoto.setImageBitmap(b);
     }
     else if(btnretrive==v)
     {
    	
    	 DbHelper.open();
 		employee_One = DbHelper.retriveEmpDetails();
 		DbHelper.close();
 		TextView empname = (TextView) findViewById(R.id.Todate);
		empname.setText(employee_One.getName());
		ImageView empphoto = (ImageView) findViewById(R.id.imgView);
		empphoto.setImageBitmap(employee_One.getBitmap());
		TextView empage = (TextView) findViewById(R.id.age);
		empage.setText("" + employee_One.getAge());
         }
	
}
}
	

