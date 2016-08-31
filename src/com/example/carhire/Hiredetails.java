package com.example.carhire;

public class Hiredetails {
	private  String _description,_location,_datebooking;
	 private  int _idd;
	public Hiredetails(int idd,String description,String location,String datebooking){
        _idd=idd;
        _description=description;
        _location=location;
        _datebooking=datebooking;
       
        }
	public int getIdd(){
        return _idd;
    }
    public String getDescription(){
        return _description;
    }
    public String getLocation(){
        return  _location;
    }
    public String getDateBooking(){
        return _datebooking;
    }
    

}
