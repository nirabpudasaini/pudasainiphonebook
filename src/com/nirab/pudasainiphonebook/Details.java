package com.nirab.pudasainiphonebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends Activity {

	TextView txt_name, txt_father, txt_address, txt_phone;
	Button btn_call, btn_msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		Intent intent = getIntent();
		String name = intent.getStringExtra("NAME");
		String father = intent.getStringExtra("FATHER");
		String address = intent.getStringExtra("ADDRESS");
		String phone = intent.getStringExtra("PHONE");

		txt_name = (TextView) findViewById(R.id.textName);
		txt_father = (TextView) findViewById(R.id.textFather);
		txt_address = (TextView) findViewById(R.id.textAddress);
		txt_phone = (TextView) findViewById(R.id.textPhone);
		btn_call = (Button) findViewById(R.id.buttonCall);
		btn_msg = (Button) findViewById(R.id.buttonMessage);

		btn_call.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:"
							+ txt_phone.getText().toString()));
					startActivity(callIntent);
				} catch (ActivityNotFoundException activityException) {
					Log.e("Calling a Phone Number", "Call failed",
							activityException);
				}

			}

		});

		btn_msg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts(
						"sms", txt_phone.getText().toString(), null)));

			}

		});

		txt_name.setText(name);
		txt_father.setText(father);
		txt_address.setText(address);
		txt_phone.setText(phone);

	}

}
