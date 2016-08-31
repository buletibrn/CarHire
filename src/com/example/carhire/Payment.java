package com.example.carhire;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends Activity implements OnClickListener{
	EditText toPay,payed,Balance,plateno;
	Button submit;
	TextView tv;
String pno;
DbHelperAdapter dbHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay);
		init();
		  dbHandler=new DbHelperAdapter(getApplicationContext());
		
		
	}

	private void init() {
		// TODO Auto-generated method stub
		plateno= (EditText) findViewById(R.id.etPlateNo);
		 toPay= (EditText) findViewById(R.id.etToPay);
		payed= (EditText) findViewById(R.id.etPaye);
		 Balance= (EditText) findViewById(R.id.etBalance);
		 submit= (Button) findViewById(R.id.btpay);
		 tv=(TextView)findViewById(R.id.tvStatus);
		 submit.setOnClickListener(this);
		 
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
switch(arg0.getId()){
		
		
		case R.id.btpay:{
			double m;
			double z;
			double k;
			String n;
			m=Double.parseDouble(toPay.getText().toString());
			z=Double.parseDouble(payed.getText().toString());
			k=m-z;
			n=Double.toString(k);
			Balance.setText(n);
			try{
				Paymentdetails paymentdetails=new Paymentdetails(dbHandler.getPaymentCount(),String.valueOf( plateno.getText()),String.valueOf( toPay.getText()),String.valueOf(payed.getText()),String.valueOf(Balance.getText()));
	             dbHandler.createPayment(paymentdetails);
	            String ad= dbHandler.getPcar(plateno.getText().toString());
	            Integer y=Integer.parseInt(ad);
	            if(y<=0){
	            tv.setText("Paye");
	            submit.setEnabled(false);
	            }
				 Toast.makeText(getApplicationContext(),  " Done", Toast.LENGTH_SHORT).show();
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
		break;
		
	}
	

}}
