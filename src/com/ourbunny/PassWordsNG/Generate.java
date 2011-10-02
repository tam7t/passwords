package com.ourbunny.PassWordsNG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.ourbunny.PassWordsNG.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;;


public class Generate extends Activity {
	private List<String> wordList;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.readWordlist();
        
        /** Callback for generate button */
        findViewById(R.id.generate).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String words[] = new String[4];
				
				((ToggleButton)findViewById(R.id.toggleButton1)).setEnabled(true);
				((ToggleButton)findViewById(R.id.toggleButton2)).setEnabled(true);
				((ToggleButton)findViewById(R.id.toggleButton3)).setEnabled(true);
				((ToggleButton)findViewById(R.id.toggleButton4)).setEnabled(true);
				
				// Log.d(TAG, "Generate PRESSED!!!!!");
				if ( ((ToggleButton)findViewById(R.id.toggleButton1)).isChecked() == false) {
					((TextView)findViewById(R.id.textView1)).setText(getWord());
				}
				if ( ((ToggleButton)findViewById(R.id.toggleButton2)).isChecked() == false) {
					((TextView)findViewById(R.id.textView2)).setText(getWord());
				}
				if ( ((ToggleButton)findViewById(R.id.toggleButton3)).isChecked() == false) {
					((TextView)findViewById(R.id.textView3)).setText(getWord());
				}
				if ( ((ToggleButton)findViewById(R.id.toggleButton4)).isChecked() == false) {
					((TextView)findViewById(R.id.textView4)).setText(getWord());
				}
				words[0]= (String) ((TextView)findViewById(R.id.textView1)).getText();
				words[1]= (String) ((TextView)findViewById(R.id.textView2)).getText();
				words[2]= (String) ((TextView)findViewById(R.id.textView3)).getText();
				words[3]= (String) ((TextView)findViewById(R.id.textView4)).getText();
				((EditText)findViewById(R.id.textPass)).setText(words[0] + ' ' + words[1] + ' ' + words[2] + ' ' + words[3]);
			}
		});
        
        /** Callback for generate button */
        findViewById(R.id.clear).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Log.d(TAG, "Generate PRESSED!!!!!");
				((ToggleButton)findViewById(R.id.toggleButton1)).setChecked(false);
				((ToggleButton)findViewById(R.id.toggleButton2)).setChecked(false);
				((ToggleButton)findViewById(R.id.toggleButton3)).setChecked(false);
				((ToggleButton)findViewById(R.id.toggleButton4)).setChecked(false);
				
				((EditText)findViewById(R.id.textPass)).setText("");
				
				((TextView)findViewById(R.id.textView1)).setText(R.string.empty);
				((TextView)findViewById(R.id.textView2)).setText(R.string.empty);
				((TextView)findViewById(R.id.textView3)).setText(R.string.empty);
				((TextView)findViewById(R.id.textView4)).setText(R.string.empty);
				
				((ToggleButton)findViewById(R.id.toggleButton1)).setEnabled(false);
				((ToggleButton)findViewById(R.id.toggleButton2)).setEnabled(false);
				((ToggleButton)findViewById(R.id.toggleButton3)).setEnabled(false);
				((ToggleButton)findViewById(R.id.toggleButton4)).setEnabled(false);
			}
		});
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuAbout:
			startActivityForResult(new Intent(Generate.this, About.class), 0);
			return true;
		case R.id.menuXKCD:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://xkcd.com/936/"));
			startActivity(browserIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
    }
    
    private void readWordlist() {
    	this.wordList = new ArrayList<String>();
    	BufferedReader br = null;
    	try {
    		br = new BufferedReader(new InputStreamReader(getAssets().open("words")));
    		String word;
    		while((word=br.readLine()) != null)
    			this.wordList.add(word);
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    	finally {
    		try {
    			br.close(); //stop reading
    		}
    		catch(IOException ex) {
    			ex.printStackTrace();
    		}
    	}
    }
    
    /** generate a random common word **/
    public String getWord() {
    	SecureRandom sr = new SecureRandom();
    	int index = sr.nextInt(wordList.size());
    	return this.wordList.get(index);
    }
    
    
}