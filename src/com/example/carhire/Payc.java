package com.example.carhire;

import java.util.ArrayList;
import java.util.List;

//import com.example.carhire.carsbooked.ContactListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Payc extends Activity{
	private static final int EDIT=0,DELETE=1;
	List<Hiredetails> hire =new ArrayList<Hiredetails>();
    ListView contactListView;
    ArrayAdapter<Hiredetails> contactAdapter;
    DbHelperAdapter dbHandler;
    TextView name;
	 TextView phone;
	 TextView email;
	 TextView tdate;
    TextView address;
    TextView price;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.booked);
		contactListView= (ListView) findViewById(R.id.list_item);
        dbHandler=new DbHelperAdapter(getApplicationContext());

        registerForContextMenu(contactListView);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			
				
				Intent ourint=new Intent(Payc.this,Payment.class);
				;
				startActivity(ourint);
				
				
				
			}
		});
        if(dbHandler.getHireCount()!=0)
        hire.addAll(dbHandler.getAllHire());

            populateList();

            contactAdapter.notifyDataSetChanged();
           
	}
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
	        	   /*
	               dbHandler.deleteContact(contacts.get(longclickeditemindex));
	               contacts.remove(longclickeditemindex);
	               contactAdapter.notifyDataSetChanged();
	               break;
	               */
	       }
	      return super.onContextItemSelected(item);
	    }
/*
	    private boolean hireExist(Hiredetails hire){
	        String name=hire.getDescription();
	        int contactcount=hire.size();
	        for(int i=0;i<contactcount;i++){
	            if(name.compareToIgnoreCase(hire.get(i).getDescription())==0)
	                return true;

	        }

	        return false;
	    }
*/

	    public void populateList(){
	        contactAdapter=new ContactListAdapter();
	        contactListView.setAdapter(contactAdapter);
	    }


	    private class ContactListAdapter extends ArrayAdapter<Hiredetails>{

	        public ContactListAdapter()
	        {
	            super(Payc.this,R.layout.listview_item,hire);
	        }

	        @Override
	        public View getView(int position, View view, ViewGroup parent) {

	            if(view==null)
	                view=getLayoutInflater().inflate(R.layout.hview,parent,false);

	            Hiredetails currentcontact=hire.get(position);

	             name= (TextView) view.findViewById(R.id.contactName);
	           phone= (TextView) view.findViewById(R.id.phone);
	             email= (TextView) view.findViewById(R.id.email);
	             address= (TextView) view.findViewById(R.id.address);
	             tdate= (TextView) view.findViewById(R.id.Todate);
	             price=(TextView) view.findViewById(R.id.price);
	           


	            name.setText(currentcontact.getDescription());
	            phone.setText(currentcontact.getLocation());
	            email.setText(currentcontact.getDateBooking());
	            
	           

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
