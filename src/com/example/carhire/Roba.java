package com.example.carhire;

import com.example.carhire.pics.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Roba extends Activity implements OnClickListener{
Button signUp,Login;

private Integer[] pics={R.drawable.car2,R.drawable.car3,R.drawable.car4,R.drawable.car5,R.drawable.car6,R.drawable.car8};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.beginn);
	Gallery gallery=(Gallery)findViewById(R.id.gallery1);
		gallery.setAdapter(new ImageAdapter(this));
		
		
		
			
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		signUp=(Button)findViewById(R.id.btnSiggnUp);
		Login=(Button)findViewById(R.id.btnLoggin);
		signUp.setOnClickListener(this);
		Login.setOnClickListener(this);
		
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch(arg0.getId()){
		
		
		case R.id.btnSiggnUp:{
			
			
			
			Intent intt=new Intent(Roba.this,signUp.class);
			
			startActivity(intt);
		}
		break;
		case R.id.btnLoggin:{

			
			
			Intent intt=new Intent(Roba.this,Login.class);
			
			startActivity(intt);
		}
		break;
			
		}
		}
		
	}
	


