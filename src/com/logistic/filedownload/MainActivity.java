package com.logistic.filedownload;

import java.io.File;
import java.io.InputStream;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.logistic.restclient.LIRestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

public class MainActivity extends Activity {

	EditText etUrl;
	Button btnGo;

	private static final String TAG = MainActivity.class.getName();

	ProgressBar progressBar;

	Context c;
	InputStream input;
	public ImageView imageView;
	String fileName;
	String URL;
	public String strDefaultImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etUrl = (EditText) findViewById(R.id.etUrl);
		imageView = (ImageView) findViewById(R.id.imageView1);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		etUrl.setText("http://2.bp.blogspot.com/-qkiBFQco1Xw/T8srlweUvoI/AAAAAAAAEQM/WBveO1dVlD8/s1600/nature-wallpaper-27.jpg");
		btnGo = (Button) findViewById(R.id.button1);
		btnGo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (etUrl.getText().toString().length() > 0) {
					fileDownload(etUrl.getText().toString().trim());
				}

			}
		});
	}

	public void fileDownload(String url) {
		URL = url;
		
		fileName = url.substring(url.lastIndexOf('/') + 1, url.length());

		File f = LiApplication.getFilePath(fileName, MainActivity.this);
		// if (f.exists()) {
		System.out.println("getting image from folder " + f);
		// myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
		// imageView.setImageBitmap(myBitmap);

		// } else {
		System.out.println("getting image from url" + f);
		progressBar.setProgress(0);
		progressBar.setVisibility(View.VISIBLE);
		LIRestClient.downloadFile(url, null, new FileAsyncHttpResponseHandler(f) {

			@Override
			public void onSuccess(int statusCode, File file) {
				Log.i(TAG, "onClick onSuccess :" + statusCode);

				Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
				imageView.setImageBitmap(myBitmap);

				// progressBar.setVisibility(View.GONE);
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				int totProgress = (int) (((float) bytesWritten * 100) / totalSize);
				Log.i("Progress::::", "" + totProgress);
				if (totProgress > 0) {
					progressBar.setProgress(totProgress);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable e, File response) {
				response.delete();
				Log.i(TAG, "onClick onFailure :" + statusCode + " " + e.getLocalizedMessage());
				e.printStackTrace();
				// progressBar.setVisibility(View.GONE);

				int resID = getResources().getIdentifier(strDefaultImage, "drawable", c.getPackageName());
				imageView.setImageResource(resID);
			}
		});
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
