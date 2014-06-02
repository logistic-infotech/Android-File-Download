package com.logistic.filedownload;

import java.io.File;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class LiApplication extends Application {

	private static final String TAG = "LIApplication";
	// private static final String PATH_SEPARATOR = "/";

	public static LiApplication appDelegate;

	public static ProgressDialog pDialog;

	public static String base64EncodedPublicKey = "";
	public static boolean isKindle = false;

	@Override
	public void onCreate() {
		super.onCreate();

		appDelegate = this;
	}

	public static void ShowProgressdialog(Context context) {
		if (null != pDialog && pDialog.isShowing()) {
			pDialog.dismiss();
		}

		pDialog = new ProgressDialog(context);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);
		pDialog.show();
	}

	public static void hideProgree(Context context) {
		if (null != pDialog && pDialog.isShowing()) {
			pDialog.dismiss();
		}
	}

	public static File getFilePath(String filename, Context context) {
		File cacheDir = context.getCacheDir();
		// if(cacheDir == null) {
		// cacheDir = context.getFilesDir();
		// }
		return new File(cacheDir, filename);
	}

	public static void alert(String message, Context context) {
		AlertDialog.Builder bld = new AlertDialog.Builder(context);
		bld.setMessage(message);
		bld.setNeutralButton("OK", null);
		Log.i(TAG, "Showing alert dialog: " + message);
		bld.create().show();
	}

}
