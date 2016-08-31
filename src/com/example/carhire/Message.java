package com.example.carhire;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by billy on 30/04/2015.
 */
public class Message {

    public  static void message(Context context,String message){

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }
}
