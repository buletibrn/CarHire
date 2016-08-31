package com.example.carhire;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MobileCarHire extends ListActivity {
String classes[]={"Upload Car For Hire","View Cars Booked","Cars Available For Hire","Payment"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setListAdapter(new ArrayAdapter<String>(MobileCarHire.this, android.R.layout.simple_list_item_1, classes));
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		String posi=classes[position];
if (position==0){
 posi="MainActivity";
}
if(position==1){
	posi="carsbooked";
}
if(position==2){
	posi="car_list";
}
if(position==3){
	posi="Payc";
}
		
		
		try{
			
		Class clas1=Class.forName("com.example.carhire."+posi);
		Intent ourint=new Intent(MobileCarHire.this,clas1);
		startActivity(ourint);
		
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		
		 super.onCreateOptionsMenu(menu);
		MenuInflater blowup=getMenuInflater();
		blowup.inflate(R.menu.mobile_car_hire,menu);
		return true;
	}
	/*
	@Override
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		case R.id.Login:
			Intent i=new Intent("com.example.smart_voting_system.Login");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent pref=new Intent("com.example.smart_voting_system.PREFS");
			startActivity(pref);
			break;
		case R.id.exit:
			finish();
			break;	
		}
		return false;
	}
	*/

	
	
    
    
}
