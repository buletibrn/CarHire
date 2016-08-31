package com.example.carhire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MENU extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(5000);
				}
				catch (InterruptedException e){
					e.printStackTrace();
					
				}
				finally{
					Intent Beginn=new Intent("com.example.carhire.MobileCarHire");
					startActivity(Beginn);
				}
			}
		};
		timer.start();
		
	}

}
