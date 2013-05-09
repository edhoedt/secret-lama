package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ListenerRestaurant extends Activity{
	
	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	static String numbermemoryrestaurant;
	
	private TextView number;
	private TextView name;
	private TextView city;
	private TextView street;
	private TextView numberAddress;
	private TextView description;
	
	private Button search;
	
	private ExpandableListView listRestaurants;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);
      
        Intent i = getIntent();
        numbermemoryrestaurant = i.getStringExtra(ListenerFindRestaurant.numbermemoryfindrestaurant);
        
       //TODO l'interface n'a pas l'air bonne -> il manque des EditText non? Lesquels sont impossibles à changer.
        // mais existe de sorte que "name : La crêperie Bretonne" s'affiche. Il faut garder le titre de la ligne!
	}
}
	

