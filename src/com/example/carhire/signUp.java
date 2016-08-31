package com.example.carhire;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signUp extends Activity implements OnClickListener{
	
	
	Button register;
	EditText fnamme,lnamme,email,idno,mobileno,creditno,username,password;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		inilize();
		
		

		
	}
	
	private void inilize() {
		// TODO Auto-generated method stub
		fnamme=(EditText)findViewById(R.id.fName);
		lnamme=(EditText)findViewById(R.id.sName);
		email=(EditText)findViewById(R.id.etEmail);
		idno=(EditText)findViewById(R.id.etIdNo);
		mobileno=(EditText)findViewById(R.id.etMobileNo);
		creditno=(EditText)findViewById(R.id.etCreditNo);
		username=(EditText)findViewById(R.id.etUName);
		password=(EditText)findViewById(R.id.etPassword);
		
		
		register=(Button)findViewById(R.id.btnSignUp);
		
		
		
		register.setOnClickListener(this);
		
		
		
		
		
		
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		
		case R.id.btnSignUp:
			boolean diditwork=true;
			try{
			String fnammme =fnamme.getText().toString();
			String llname =lnamme.getText().toString();
			String eemail =email.getText().toString();
		    long iidno=Integer.parseInt(idno.getText().toString());
		    long mmobileno=Integer.parseInt(mobileno.getText().toString());
		    long credditno=Integer.parseInt(creditno.getText().toString());
		    String usernamme =username.getText().toString();
		    String passsword =password.getText().toString();
			
			
			 sqlite entry=new sqlite(signUp.this);
			 entry.openDB();
			 String ad=entry.getadmin(fnammme).toString();
			 
			 if(ad=="")
			 {entry.insertuser(fnammme,llname,eemail,iidno,mmobileno,credditno,usernamme,passsword);
			 Toast.makeText(getBaseContext(), "Register Successful",Toast.LENGTH_LONG).show();
			 
			 }else{
				 Toast.makeText(getBaseContext(), "User already exits",Toast.LENGTH_LONG).show();
			 }
			 
			 entry.close();
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
			
			break;
		
			
		
		}
		
	}}
	

