package com.example.carhire;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.ContactsContract;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final int EDIT=0,DELETE=1;

    EditText name,phone,email,address,tdate,price;
    int g;
    TabHost tabHost;

    List<Contact> contacts =new ArrayList<Contact>();
    ListView contactListView;


    ImageView contactImage;
    Uri imageuri=Uri.parse("android.resource://com.example.billy.contactorganizer/drawable/index.jpg");

    DbHelperAdapter dbHandler;
    int longclickeditemindex;
    ArrayAdapter<Contact> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
        name= (EditText) findViewById(R.id.etname);
        phone= (EditText) findViewById(R.id.etphone);
        email= (EditText) findViewById(R.id.etemail);
        tdate=(EditText) findViewById(R.id.etDateTo);
        price=(EditText) findViewById(R.id.etPrice);
        contactImage= (ImageView) findViewById(R.id.imageViewContact);

        address= (EditText) findViewById(R.id.etaddress);
        tabHost= (TabHost) findViewById(R.id.tabHost);
        contactListView= (ListView) findViewById(R.id.list_item);
        dbHandler=new DbHelperAdapter(getApplicationContext());
     
        registerForContextMenu(contactListView);

        contactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longclickeditemindex=position;
                return false;
            }
        });


        tabHost.setup();
        TabHost.TabSpec spec=tabHost. newTabSpec("creator");
        spec.setContent(R.id.creator_tab);
        spec.setIndicator("creator");
        tabHost.addTab(spec);

        spec=tabHost. newTabSpec("List");
        spec.setContent(R.id.tab_contact_store);
        spec.setIndicator("List");
        tabHost.addTab(spec);
       

        final Button  add= (Button) findViewById(R.id.btSubmit);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            	  Contact contact=new Contact(dbHandler.getContactCount(),String.valueOf(name.getText()),String.valueOf(phone.getText()),String.valueOf(email.getText()),String.valueOf(tdate.getText()),String.valueOf(address.getText()),String.valueOf(price.getText()),imageuri);
                if(!contactExist(contact)) {
                    dbHandler.createContact(contact);
                    contacts.add(contact);
                    contactAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), String.valueOf(name.getText())+" "+String.valueOf(phone.getText())  + "Car Uploaded", Toast.LENGTH_SHORT).show();
                return;
                }
                Toast.makeText(getApplicationContext(), String.valueOf(name.getText())+ " already exists", Toast.LENGTH_SHORT).show();
              }
        });
        



        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                add.setEnabled(!String.valueOf(name.getText()).trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        

        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"select contact image"),1);
            }
        });


        if(dbHandler.getContactCount()!=0)
        contacts.addAll(dbHandler.getAllContact());

            populateList();
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
    
     }//end of oncreate method.
   


    public void onActivityResult(int reqCode,int resCode, Intent data){

        if(resCode==RESULT_OK){
            if(reqCode==1) {
                imageuri=data.getData();
                contactImage.setImageURI(data.getData());
            }
            }
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
               dbHandler.deleteContact(contacts.get(longclickeditemindex));
               contacts.remove(longclickeditemindex);
               contactAdapter.notifyDataSetChanged();
               break;
       }
      return super.onContextItemSelected(item);
    }

    private boolean contactExist(Contact contact){
        String name=contact.getPhone();
        int contactcount=contacts.size();
        for(int i=0;i<contactcount;i++){
            if(name.compareToIgnoreCase(contacts.get(i).getPhone())==0)
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
            super(MainActivity.this,R.layout.listview_item,contacts);
        }
        
        @Override
        public View getView(int position, View view, ViewGroup parent) {
        	

            if(view==null)
                view=getLayoutInflater().inflate(R.layout.another,parent,false);

            Contact currentcontact=contacts.get(position);

            TextView name= (TextView) view.findViewById(R.id.contactName);
            TextView phone= (TextView) view.findViewById(R.id.phone);
            TextView email= (TextView) view.findViewById(R.id.email);
            TextView date= (TextView) view.findViewById(R.id.Todate);
            TextView address= (TextView) view.findViewById(R.id.address);
            TextView price= (TextView) view.findViewById(R.id.price);
            ImageView imagecontact= (ImageView) view.findViewById(R.id.ivContctimage);


            name.setText(currentcontact.getName());
            phone.setText(currentcontact.getPhone());
            email.setText(currentcontact.getEmail());
            date.setText(currentcontact.getTdate());
            address.setText(currentcontact.getAddress());
            price.setText(currentcontact.getPrice());
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
    }}