package com.example.carhire;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SHSPixManager {
	private SQLiteDatabase db;
	private SHSPixHelper helper;
	private SHSPix shsPix;
	private ArrayList<SHSPix> shsPixsList;

	public SHSPixManager(Context context){
		helper=new SHSPixHelper(context);
		
		
	} 
	
	public void openRead(){
		db=helper.getReadableDatabase();
	}
	public void openWrite(){
		
		db=helper.getWritableDatabase();
	}
	public void close(){
		db.close();
	}
	public long createSHSPix(SHSPix pix){
		
		return 12;
	}
	public long updateSHSPix(SHSPix pix){
		
		return 12;
	}
public long deleteSHSPix(SHSPix pix){
		
		return 12;
	}
public ArrayList<SHSPix>getAllSHSPix(){
	return shsPixsList;
	
}
public SHSPix getSHSpix(String desc){
	return shsPix;
	
}
}
