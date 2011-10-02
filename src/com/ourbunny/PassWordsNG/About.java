package com.ourbunny.PassWordsNG;

import com.ourbunny.PassWordsNG.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class About extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        PackageInfo packageInfo;
        String strVersion;
		try {
			packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			strVersion = packageInfo.versionName;
        } catch (NameNotFoundException e) {
        	e.printStackTrace();
        	strVersion = "Undefined Version";
        }
        
        // text2 has links specified by putting <a> tags in the string
        // resource.  By default these links will appear but not
        // respond to user input.  To make them active, you need to
        // call setMovementMethod() on the TextView object.
        
        TextView t_version = (TextView) findViewById(R.id.textveiw_version);
        t_version.setText(strVersion);
        
        TextView t_contact = (TextView) findViewById(R.id.textview_contact);
        t_contact.setMovementMethod(LinkMovementMethod.getInstance());
        
        findViewById(R.id.button_back).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Log.d(TAG, "Generate PRESSED!!!!!");
				Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
    }
}