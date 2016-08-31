package com.example.carhire;

import java.util.ArrayList;
import java.util.List;

//import com.example.carhire.MainActivity.ContactListAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class car_list extends Activity{
	private static final int EDIT=0,DELETE=1;

	 TextView name;
	 TextView phone;
	 TextView email;
	 TextView tdate;
     TextView address;
     TextView price;
     

    List<Contact> contacts =new ArrayList<Contact>();
    ListView contactListView;


   
    Uri imageuri=Uri.parse("android.resource://com.example.billy.contactorganizer/drawable/index.jpg");

    DbHelperAdapter dbHandler;
    int longclickeditemindex;
    ArrayAdapter<Contact> contactAdapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carlist);
       
      
        contactListView= (ListView) findViewById(R.id.list_item);
        dbHandler=new DbHelperAdapter(getApplicationContext());

        registerForContextMenu(contactListView);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			
				String platte=phone.getText().toString();
				
				String usern=name.getText().toString()+" "+phone.getText().toString()+" "+"Available From"+ email.getText().toString()+"To"+tdate.getText().toString()+"The Cost is "+price.getText();
				Bundle cary=new Bundle();
				Bundle plateno=new Bundle();
				plateno.putString("plate", platte);
				cary.putString("key",usern);
				Intent ourint=new Intent(car_list.this,onHire.class);
				ourint.putExtras(cary);
				ourint.putExtras(plateno);
				startActivity(ourint);
				
				
				
			}
		});
        	
        

        contactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longclickeditemindex=position;
                return false;
        		
               	
            }
        });




        if(dbHandler.getContactCount()!=0)
        contacts.addAll(dbHandler.getAllContact());

            populateList();

            contactAdapter.notifyDataSetChanged();
           
     //end of oncreate method.
       

}
/*
    public void onCreateContextMenu(ContextMenu menu,View view,ContextMenu.ContextMenuInfo menuInfo){
      super.onCreateContextMenu(menu,view,menuInfo);
        menu.setHeaderIcon(R.drawable.ic_launcher);
        menu.setHeaderTitle("Contacts Options");
        menu.add(Menu.NONE,EDIT,Menu.NONE,"Edit contact");
        menu.add(Menu.NONE,DELETE,Menu.NONE,"Delete contact");
    }

    public boolean onContextItemSelected(MenuItem item){
       switch (item.getItemId()){
           case EDIT:
               break;
           case DELETE:
               dbHandler.deleteContact(contacts.get(longclickeditemindex));
               contacts.remove(longclickeditemindex);
               contactAdapter.notifyDataSetChanged();
               break;
       }
      return super.onContextItemSelected(item);
    }
*/
    private boolean contactExist(Contact contact){
        String name=contact.getName();
        int contactcount=contacts.size();
        for(int i=0;i<contactcount;i++){
            if(name.compareToIgnoreCase(contacts.get(i).getName())==0)
                return true;

        }

        return false;
    }


    public void populateList(){
        contactAdapter=new ContactListAdapter();
        contactListView.setAdapter(contactAdapter);
    }


    private class ContactListAdapter extends ArrayAdapter<Contact>{

        public ContactListAdapter()
        {
            super(car_list.this,R.layout.another,contacts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if(view==null)
                view=getLayoutInflater().inflate(R.layout.another,parent,false);

            Contact currentcontact=contacts.get(position);

             name= (TextView) view.findViewById(R.id.contactName);
           phone= (TextView) view.findViewById(R.id.phone);
             email= (TextView) view.findViewById(R.id.email);
             address= (TextView) view.findViewById(R.id.address);
             tdate= (TextView) view.findViewById(R.id.Todate);
             price=(TextView) view.findViewById(R.id.price);
            ImageView imagecontact= (ImageView) view.findViewById(R.id.ivContctimage);


            name.setText(currentcontact.getName());
            phone.setText(currentcontact.getPhone());
            email.setText(currentcontact.getEmail());
            tdate.setText(currentcontact.getTdate());
            price.setText(currentcontact.getPrice());
            address.setText(currentcontact.getAddress());
            imagecontact.setImageURI(currentcontact.getImageURI());
           

            return view;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mobile_car_hire, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
       
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            
        }
    }
    
}
