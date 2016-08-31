package com.example.carhire;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 29/04/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by billy on 25/04/2015.
 */
public class DbHelperAdapter  {

    DbHelper helper;
    public DbHelperAdapter(Context context){
        helper=new DbHelper(context);
    }



    public void createContact(Contact contact){
        SQLiteDatabase database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(DbHelper.KEY_NAME, contact.getName());
        values.put(DbHelper.KEY_PHONE, contact.getPhone());
        values.put(DbHelper.KEY_EMAIL, contact.getEmail());
        values.put(DbHelper.KEY_TDATE, contact.getTdate());
        values.put(DbHelper.KEY_ADDRESS, contact.getAddress());
        values.put(DbHelper.KEY_PRICE, contact.getPrice());
        values.put(DbHelper.KEY_IMAGE_URI, contact.getImageURI().toString());
        database.insert(DbHelper.DATABASE_TABLE, null, values);
        Toast.makeText(helper.context,"contact created",Toast.LENGTH_SHORT).show();
        database.close();
    }
    public void createHire(Hiredetails hiredetails){
        SQLiteDatabase database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(DbHelper. KEY_DESCRPTION, hiredetails.getDescription());
        values.put(DbHelper.KEY_LOCATION, hiredetails.getLocation());
        values.put(DbHelper. KEY_DATEBOOKING, hiredetails.getDateBooking());
   
      
        database.insert(DbHelper.TABLE_HIREDETAILS, null, values);
        Toast.makeText(helper.context,"Successful",Toast.LENGTH_SHORT).show();
        database.close();
    }
    public void createPayment(Paymentdetails paymentdetails){
        SQLiteDatabase database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
       
        values.put(DbHelper. KEY_PLATENO, paymentdetails.getPlateNo());
        values.put(DbHelper.KEY_TOPAY, paymentdetails.getTopay());
        values.put(DbHelper. KEY_PAYED, paymentdetails.getPayed());
        values.put(DbHelper. KEY_BALANCE, paymentdetails.getBalance());
       
               
              
        database.insert(DbHelper.TABLE_PAYMENT, null, values);
        Toast.makeText(helper.context,"Successful",Toast.LENGTH_SHORT).show();
        database.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query(DbHelper.DATABASE_TABLE,new String[]{DbHelper.KEY_ID,DbHelper.KEY_NAME,DbHelper.KEY_PHONE,DbHelper.KEY_EMAIL,DbHelper.KEY_TDATE,DbHelper.KEY_ADDRESS,DbHelper.KEY_PRICE,DbHelper.KEY_IMAGE_URI},DbHelper.KEY_ID + " = ? ",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null)
        cursor.moveToFirst();
        Contact contact=new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6), Uri.parse(cursor.getString(7)));

        db.close();
        cursor.close();
        return contact;
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.delete(DbHelper.DATABASE_TABLE,DbHelper.KEY_ID + " = ? ",new String[]{String .valueOf(contact.getId())});
        db.close();
    }
    
    public boolean removeCar(String countryName)
    
    {
    	 SQLiteDatabase db=helper.getWritableDatabase();
        int result =db.delete(DbHelper.DATABASE_TABLE, "phone='" + countryName + "'", null);
 
        if(result > 0)
            return true;
        else
            return false;
    }
 
    public int getContactCount(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+DbHelper.DATABASE_TABLE,null);
        int count=cursor.getCount();

        db.close();
        cursor.close();
        return count ;
    }
    public int getHireCount(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+DbHelper.TABLE_HIREDETAILS,null);
        int count=cursor.getCount();

        db.close();
        cursor.close();
        return count ;
    }
    public int getPaymentCount(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+DbHelper.TABLE_PAYMENT,null);
        int count=cursor.getCount();

        db.close();
        cursor.close();
        return count ;
    }

	 public String getPcar(String adm) {
			//String[] column=new String[]{ADM};
		 SQLiteDatabase db=helper.getReadableDatabase();
		 Cursor c=db.rawQuery("SELECT * FROM "+DbHelper.TABLE_PAYMENT+" WHERE plateno='"+adm+"'", null);
			String result ="";
	
			int iRow=c.getColumnIndex(DbHelper.KEY_BALANCE);
			
			
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext() ){
			   result=result+c.getString(iRow)+"\n";
			   
			   
			}
			
			return result;
		}
    public int updateContact(Contact contact){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(DbHelper.KEY_NAME, contact.getName());
        values.put(DbHelper.KEY_PHONE, contact.getPhone());
        values.put(DbHelper.KEY_EMAIL, contact.getEmail());
        values.put(DbHelper.KEY_TDATE, contact.getTdate());
        values.put(DbHelper.KEY_ADDRESS, contact.getAddress());
        values.put(DbHelper.KEY_IMAGE_URI, contact.getImageURI().toString());
        int affrow=db.update(DbHelper.DATABASE_TABLE,values,DbHelper.KEY_ID + " = ? ",new String[]{String.valueOf(contact.getId())});
        db.close();
        return affrow;


    }


    public List<Contact> getAllContact(){
        List<Contact> contacts=new ArrayList<Contact>();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM " +DbHelper.DATABASE_TABLE,null);
        while (cursor.moveToNext()){
            

                contacts.add(new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6), Uri.parse(cursor.getString(7))));

        }

        cursor.close();
        db.close();
        return contacts;
    }
    public List<Hiredetails> getAllHire(){
        List<Hiredetails> hire=new ArrayList<Hiredetails>();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM " +DbHelper.TABLE_HIREDETAILS,null);
        while (cursor.moveToNext()){
            

                hire.add(new Hiredetails(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3)));

        }

        cursor.close();
        db.close();
        return hire;
    }
    public List<Paymentdetails> getAllPayment(){
        List<Paymentdetails> pay=new ArrayList<Paymentdetails>();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM " +DbHelper.TABLE_PAYMENT,null);
        while (cursor.moveToNext()){
            

                pay.add(new Paymentdetails(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));

        }

        cursor.close();
        db.close();
        return pay;
    }



    static class DbHelper extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION=1;
        private static final String DATABASE_NAME="carr",
                DATABASE_TABLE="contacts",
                TABLE_HIREDETAILS="hiredetails",
                TABLE_PAYMENT="payment",
                		KEY_ID="_id",
                				KEY_IDD="_idd",
                						KEY_IDDD="_iddd",
                        KEY_NAME="name",
                        KEY_PHONE="phone",
                        KEY_EMAIL="email",
                        KEY_TDATE="tdate",
                        KEY_ADDRESS="address",
                        KEY_PRICE="_price",
                         KEY_IMAGE_URI="imageUri",
                        KEY_DESCRPTION="description",
                       KEY_LOCATION="location",
                      KEY_DATEBOOKING="datebooking",
                     KEY_PLATENO="plateno",
                      KEY_DESCRP="descrp",
                      KEY_TOPAY="topay",
                      KEY_PAYED="payed",
                      KEY_BALANCE="balance";
                

        private static final String CREATETB=" CREATE TABLE " + DATABASE_TABLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_PLATENO+"," + KEY_NAME + " VARCHAR(255)," + KEY_PHONE + " VARCHAR(255)," + KEY_EMAIL + " VARCHAR(255)," + KEY_TDATE + " VARCHAR(255)," + KEY_ADDRESS + " VARCHAR(255)," + KEY_PRICE + "  INTEGER," + KEY_IMAGE_URI + " VARCHAR(255))";
        private static final String DROPTB="DROP TABLE IF EXISTS"+DATABASE_TABLE;
	
        private static final String CREATEHIRE=" CREATE TABLE " +  TABLE_HIREDETAILS + " ( " + KEY_IDD + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DESCRPTION + " VARCHAR(255)," + KEY_LOCATION + " VARCHAR(255)," +  KEY_DATEBOOKING + " VARCHAR(255))";
        private static final String DROPHIRE="DROP TABLE IF EXISTS"+ TABLE_HIREDETAILS;
        
        private static final String CREATEPAYMENT=" CREATE TABLE " +  TABLE_PAYMENT + " ( " + KEY_IDDD + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_PLATENO+"," + KEY_DESCRP + " VARCHAR(255)," + KEY_TOPAY + " INTEGER," +  KEY_PAYED + " INTEGER," +  KEY_BALANCE + " INTEGER)";
        private static final String DROPPAYMENT="DROP TABLE IF EXISTS"+ TABLE_PAYMENT;

        private Context context;

        public DbHelper(Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
            Message.message(context,"Constructor Called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATETB);
                Message.message(context,"oncreate Called");
            }catch (Exception e){
                Message.message(context,"opps failed to create tables"+e);

            }
            try{
                db.execSQL(CREATEHIRE);
                Message.message(context,"oncreate Called");
            }catch (Exception e){
                Message.message(context,"opps failed to create tables"+e);

            }
            try{
                db.execSQL(CREATEPAYMENT);
                Message.message(context,"oncreate Called");
            }catch (Exception e){
                Message.message(context,"opps failed to create tables"+e);

            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"onupgrade Called");
                db.execSQL(DROPTB);
                onCreate(db);
            }catch (Exception e){
                Message.message(context,"opps failed to upgrade"+e);
            }
            try {
                Message.message(context,"onupgrade Called");
                db.execSQL(DROPHIRE);
                onCreate(db);
            }catch (Exception e){
                Message.message(context,"opps failed to upgrade"+e);
            }
            try {
                Message.message(context,"onupgrade Called");
                db.execSQL(DROPPAYMENT);
                onCreate(db);
            }catch (Exception e){
                Message.message(context,"opps failed to upgrade"+e);
            }


        }

    }

}
