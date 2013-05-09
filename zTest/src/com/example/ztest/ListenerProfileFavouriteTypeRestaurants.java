package com.example.ztest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ListenerProfileFavouriteTypeRestaurants extends Activity {

	//MySQLiteHelper sqliteHelper = new MySQLiteHelper(this);

	private EditText add;
	private EditText remove;
		
	private Button addbutton;
	private Button removebutton;
		
	// private ExpandableListView listfavrest; //TODO ???
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.profilefavouritetyperestaurants);
			
		add = (EditText) findViewById(R.id.editText1);
		remove = (EditText) findViewById(R.id.editText2);
	  
		addbutton = (Button) findViewById(R.id.button1);
		removebutton = (Button) findViewById(R.id.button2);

		// Association Listeners
			
		addbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (add.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "You forgot to fill in the field!", Toast.LENGTH_LONG).show();
				}	
			}
		});	
			
		removebutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (remove.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "You forgot to fill in the field!", Toast.LENGTH_LONG).show();
				}
			}
		});
	}	
}
