package com.nirab.pudasainiphonebook;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private Cursor contacts;
	private MyDatabase db;
	private SimpleCursorAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getListView().setDividerHeight(2);
		populateList();

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Cursor mycursor = (Cursor) getListView().getItemAtPosition(position);
		String name, father, address, phone;
		name = mycursor.getString(1);
		father = mycursor.getString(2);
		address = mycursor.getString(3);
		phone = mycursor.getString(4);
		Intent i = new Intent(this, Details.class);
		i.putExtra("NAME", name);
		i.putExtra("FATHER", father);
		i.putExtra("ADDRESS", address);
		i.putExtra("PHONE", phone);
		startActivity(i);
		// Toast.makeText(
		// this,
		// "Name:" + name + "\nFather:" + father + "\nAddress:" + address
		// + "\nPhone Number:" + phone, Toast.LENGTH_SHORT).show();

	}

	private void populateList() {

		String[] from = new String[] { "name", "number" };
		int[] to = new int[] { R.id.name, R.id.number };

		db = new MyDatabase(this);
		contacts = db.getContacts();
		adapter = new SimpleCursorAdapter(this, R.layout.contact_row, contacts,
				from, to, 0);

		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			//TODO the search code goes here
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}