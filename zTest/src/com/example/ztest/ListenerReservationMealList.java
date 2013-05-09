package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ListenerReservationMealList extends Activity {
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurantreservationmeallist;
	
	private EditText numberMeal;
	private EditText chosenMeal;
	
	private CheckBox price;
	private CheckBox alphabetically;
	
	private Button addMeal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.reservationmeallist);
      
        Intent i = getIntent();
        numbermemoryrestaurantreservationmeallist = i.getStringExtra(ListenerReservation.numbermemoryrestaurantreservation);
        
       numberMeal = (EditText)findViewById(R.id.editText1);
       chosenMeal = (EditText)findViewById(R.id.editText2);
       price = (CheckBox)findViewById(R.id.checkBox2);
       alphabetically = (CheckBox)findViewById(R.id.checkBox1);
       addMeal = (Button)findViewById(R.id.button1);
       
       addMeal.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				//TODO ajouter le meal et sa quantité!
				Intent i = new Intent(ListenerReservationMealList.this, ListenerReservation.class);
				i.putExtra(numbermemoryrestaurantreservationmeallist, numbermemoryrestaurantreservationmeallist);
				startActivity(i);
			}
		});
	}
}
