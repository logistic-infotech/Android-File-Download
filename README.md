Android-File-Download
=====================

Android download file with progressbar 0 to 100
-----------------------------------------------

There is very less code we need to put to download file just like below:

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
