package com.example.carhire;

import com.example.carhire.listItems;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listItems extends ListActivity{
	String classes[]={"Login","signUp","pics","listItems","Results"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(listItems.this, android.R.layout.simple_list_item_1, classes));
		}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
String posi=classes[position];
		
		try{
			
		Class clas1=Class.forName("com.example.carhire."+posi);
		Intent ourint=new Intent(listItems.this,clas1);
		startActivity(ourint);
		
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	

}
