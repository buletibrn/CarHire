package com.example.carhire;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class onHire extends Activity implements OnClickListener{
EditText Description;
EditText Location,plno;
EditText Date;
Button submit;
String details;
String pno;
DbHelperAdapter dbHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onhire);
		init();
		Bundle getcary=getIntent().getExtras();
        details=getcary.getString("key");
        Description.setText( details);
        Bundle getplateno=getIntent().getExtras();
        pno=getplateno.getString("plate");
        plno.setText(pno);
        dbHandler=new DbHelperAdapter(getApplicationContext());
        
	}
	private void init() {
		// TODO Auto-generated method stub
		Description=(EditText)findViewById(R.id.etDescription);
		Location=(EditText)findViewById(R.id.etLoaction);
		Date=(EditText)findViewById(R.id.etDate);
		plno=(EditText)findViewById(R.id.etToPay);
	submit=(Button)findViewById(R.id.btnSubmit);
		
		
		
	submit.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
switch(arg0.getId()){
		
		
		case R.id.btnSubmit:{
			try{
			Hiredetails hiredetails=new Hiredetails(dbHandler.getHireCount(),String.valueOf(Description.getText()),String.valueOf(Location.getText()),String.valueOf(Date.getText()));
             dbHandler.createHire(hiredetails);
			 dbHandler.removeCar(plno.getText().toString());
             Toast.makeText(getApplicationContext(),  " contact created", Toast.LENGTH_SHORT).show();
         return;
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
}
		
	}}


