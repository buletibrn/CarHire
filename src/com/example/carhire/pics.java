package com.example.carhire;

import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class pics extends Activity {
private Integer[] pics={R.drawable.car2,R.drawable.car3,R.drawable.car4,R.drawable.car5,R.drawable.car6,R.drawable.car8};
private ImageView imageView;
SQLiteDatabase db;

@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pics);
		Gallery gallery=(Gallery)findViewById(R.id.gallery1);
		gallery.setAdapter(new ImageAdapter(this));
		imageView=(ImageView) findViewById(R.id.imageView1);
		db=this.openOrCreateDatabase("register.db", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists tb (a blob)");
		gallery.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "pic:"+ arg2, Toast.LENGTH_SHORT).show();
				imageView.setImageResource(pics[arg2]);
			}});
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.mobile_car_hire, menu);
		//return super.onCreateOptionsMenu(menu);
		return true;
	}
	public class ImageAdapter extends BaseAdapter{
		
		private Context context;
		int imageBackground;
		public ImageAdapter(Context context){
			this.context=context;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pics.length;
		}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ImageView imageView=new ImageView(context);
			imageView.setImageResource(pics[arg0]);
			return imageView;
		}
	}
	public void saveImage(View view){
		try{
		FileInputStream fis=new FileInputStream("/storage/sdcard/rango5.jpg");
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
	}
	public void getImage(View view){
		Cursor c=db.rawQuery("select * from tb",null);
		if (c.moveToNext()){
			c.getBlob(0);
			byte[] image=c.getBlob(0);
			Bitmap bmp=BitmapFactory.decodeByteArray(image, 0, image.length);
			imageView.setImageBitmap(bmp);
			Toast.makeText(this,"select success", Toast.LENGTH_SHORT).show();
		}
	}


		
	}


