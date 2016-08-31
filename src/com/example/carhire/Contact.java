package com.example.carhire;

import android.net.Uri;



/**
 * Created by billy on 29/04/2015.
 */
public class Contact {

    private  String _name,_phone,_email,_address,_tdate,_price;
    private Uri _imageURI;
    private  int _id;
    public Contact(int id,String name,String phone,String email,String tdate,String address,String price,Uri imageURI){
        _id=id;
        _name=name;
        _phone=phone;
        _email=email;
        _tdate=tdate;
        _address=address;
        _price=price;
        _imageURI=imageURI;
       
        		

    }
    
    public int getId(){
        return _id;
    }
    public String getName(){
        return _name;
    }
    public String getPhone(){
        return _phone;
    }
    public String getEmail(){
        return _email;
    }
    public String getTdate(){
    	return _tdate;
    }
    public String getAddress(){
        return _address;
    }
    public String getPrice(){
        return _price;
    }
    public Uri getImageURI(){
        return _imageURI;
    }
}

