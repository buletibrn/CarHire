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

public class Login extends Activity implements OnClickListener {
	EditText user,pass;
	Button blogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		user=(EditText)findViewById(R.id.etPhone);
		pass=(EditText)findViewById(R.id.etPaye);
		
		
		blogin=(Button)findViewById(R.id.btn1);
		blogin.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		
		
		case R.id.btn1:{
			
			String userid=user.getText().toString();
			String passs=pass.getText().toString();
			
			
			
			boolean diditwork=true;
			try{
			 sqlite abo=new sqlite(Login.this);
			 abo.openDB();
			 String vb= abo.getLogin(userid,passs);
			
		if(vb!=""){
			
			 
			 String usern=user.getText().toString();
				Bundle cary=new Bundle();
				cary.putString("key",usern);
				Intent intt=new Intent(Login.this,MobileCarHire.class);
				intt.putExtras(cary);
				startActivity(intt);
		}
		else{
			 Toast.makeText(getBaseContext(), "Invalid details",Toast.LENGTH_LONG).show();
		}
			 
			 abo.close();
			}catch(Exception e ){
				diditwork=false;
			    
				String era=e.toString();
				Dialog d=new Dialog(this); 
				d.setTitle("oops!!");
				TextView tv=new TextView(this);
				tv.setText(era);
				d.setContentView(tv);
				d.show();
			}
			
			
			 }
			break;
			 
			
			
	}
		}}

