package com.reminder;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnCheckedChangeListener{
	
	private Switch switcher;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		switcher = (Switch)findViewById(R.id.switcher);
		switcher.setOnCheckedChangeListener(this);
		intent = new Intent(MainActivity.this,service_class.class);
		stopService(intent);
	}
	
	@Override
    public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
        // TODO Auto-generated method stub
		intent = new Intent(MainActivity.this,service_class.class);
        if(isChecked) {
        	startService(intent);
        } else {
        	stopService(intent);   
        }
    }
	
	@Override
	public void onPause(){
		super.onPause();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}