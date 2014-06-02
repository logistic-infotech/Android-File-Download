package com.logistic.filedownload;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashScreen extends Activity {
	private static final int SPLASH_DISPLAY_TIME = 2000; /* 1 second */

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		new Handler().postDelayed(new Runnable() 
		{
			@Override
			public void run() {
				SplashScreen.this.finish();
				Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
				SplashScreen.this.startActivity(mainIntent);
				overridePendingTransition(R.anim.mainfadein,R.anim.splashfadeout);
			}
		}, SPLASH_DISPLAY_TIME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
