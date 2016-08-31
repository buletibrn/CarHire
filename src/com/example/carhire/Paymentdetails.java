package com.example.carhire;

import android.net.Uri;

public class Paymentdetails {
	 private  String _topay,_payed,_balance,_plateno;
	 private int _iddd;
	 public Paymentdetails(int iddd,String plateno,String topay,String payed,String balance){
		 _iddd=iddd;
		 _plateno=plateno;
         _topay=topay;
   _payed=payed;
   _balance=balance;
	 }
	 public int getIddd(){
	        return _iddd;
	    }
	    
	    public String getTopay(){
	        return _topay;
	    }
	    public String getPayed(){
	        return _payed;
	    }
	    public String getBalance(){
	        return _balance;
	    }
	    public String getPlateNo(){
	    	return _plateno;
	    }

}
