package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class ListenerReservation extends Activity {
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurantreservation;
	
	private EditText dateAndHour;
	private EditText numberPlaces;
	private EditText typePlaces;
	
	private ExpandableListView listMenusAndQuantities;
	private ExpandableListView listMealsAndQuantities;
	
	private Button addMenuAndQuantity;
	private Button addMealAndQuantity;
	private Button makeReservation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
      
        Intent i = getIntent();
        numbermemoryrestaurantreservation = i.getStringExtra(ListenerRestaurant.numbermemoryrestaurant);
        
       dateAndHour = (EditText)findViewById(R.id.editText3);
       numberPlaces = (EditText)findViewById(R.id.editText1);
       typePlaces = (EditText)findViewById(R.id.editText2);
       listMenusAndQuantities = (ExpandableListView)findViewById(R.id.expandableListView1);
       addMenuAndQuantity = (Button)findViewById(R.id.button1);
       listMealsAndQuantities = (ExpandableListView)findViewById(R.id.expandableListView2);
       addMealAndQuantity = (Button)findViewById(R.id.button2);
       makeReservation = (Button)findViewById(R.id.button3);
       
       addMenuAndQuantity.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				Intent i = new Intent(ListenerReservation.this, ListenerReservationMenuList.class);
				i.putExtra(numbermemoryrestaurantreservation, numbermemoryrestaurantreservation);
				startActivity(i);
			}
		});
       
       addMealAndQuantity.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				Intent i = new Intent(ListenerReservation.this, ListenerReservationMealList.class);
				i.putExtra(numbermemoryrestaurantreservation, numbermemoryrestaurantreservation);
				startActivity(i);
			}
		});
       
       makeReservation.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO faire la reservation en bdd+model
				Intent i = new Intent(ListenerReservation.this, ListenerHome.class);
				startActivity(i);
			}
		});
	}
}
	


