package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ListenerReservationMenuList extends Activity {
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurantreservationmenulist;
	
	private EditText numberMenu;
	private EditText chosenMenu;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private Button addMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.reservationmenulist);
      
        Intent i = getIntent();
        numbermemoryrestaurantreservationmenulist = i.getStringExtra(ListenerReservation.numbermemoryrestaurantreservation);
        
        numberMenu = (EditText)findViewById(R.id.editText1);
        chosenMenu = (EditText)findViewById(R.id.editText2);
        price = (CheckBox)findViewById(R.id.checkBox2);
        alphabetically = (CheckBox)findViewById(R.id.checkBox1);
        addMenu = (Button)findViewById(R.id.button1);
       
        addMenu.setOnClickListener(new View.OnClickListener() {
        	@Override	
			public void onClick(View v) {
				//TODO ajouter le menu et sa quantité!
				Intent i = new Intent(ListenerReservationMenuList.this, ListenerReservation.class);
				i.putExtra(numbermemoryrestaurantreservationmenulist, numbermemoryrestaurantreservationmenulist);
				startActivity(i);
			}
		});
	}
}
