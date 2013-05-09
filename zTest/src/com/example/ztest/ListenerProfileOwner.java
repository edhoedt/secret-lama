package com.example.ztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ListenerProfileOwner extends Activity {
	static String NUMBER;
	
	private TextView number;
	private TextView name;
		
	private EditText password;
		
	private Button change;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.profileowner);
	    
	    Intent i = getIntent();
	    NUMBER = i.getStringExtra(ListenerHomeOwner.NUMBER);
			
		number = (TextView) findViewById(R.id.textView2);
		number.setText(NUMBER);
		
		name = (TextView) findViewById(R.id.textView3);
		password = (EditText) findViewById(R.id.editText3);
		if(MainActivity.getUserManager().loadUser(NUMBER)) {
			name.setText(MainActivity.getUserManager().getCurrentUser().getName());
			password.setText(MainActivity.getUserManager().getCurrentUser().getPassword());
		}
			
		change = (Button) findViewById(R.id.button1);				
			
		// Association Listeners		
			
		change.setOnClickListener(new View.OnClickListener() {
			@Override	
			public void onClick(View v) {
				// password vide.
				if (password.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "You forgot to fill in the field for password!", Toast.LENGTH_LONG).show();
				}
				else {
					if(! MainActivity.getUserManager().loadUser(NUMBER)) {
						Toast.makeText(getApplicationContext(), "ERROR : Can't load user.", Toast.LENGTH_LONG).show();
					}
					// password non modifie.
					if(password.getText().toString().equals(MainActivity.getUserManager().getCurrentUser().getPassword())) {
						ListenerProfileOwner.this.finish();
					}
					// password modifie.
					else {
						MainActivity.getUserManager().setPassword(password.getText().toString());
						Toast.makeText(getApplicationContext(), "Your new password has been successfully saved.", Toast.LENGTH_LONG).show();
						ListenerProfileOwner.this.finish();
					}
				}
			}
		});
	}
}
