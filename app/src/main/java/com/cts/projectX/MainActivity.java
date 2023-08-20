package com.cts.projectX;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.net.Uri;

import android.widget.TextView;

import android.webkit.JavascriptInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.w3c.dom.Node;

public class MainActivity extends AppCompatActivity {
	private WebView webView;
	private TextView textView;
	private static final String customErrorPage = "file:///android_asset/error.html";
	private static final String yourCustomURL = "file:///android_asset/web/index.html";
	private static final String txt = "		Project loader		" + "	by cross the hedgehog	";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = findViewById(R.id.webView);
		textView = findViewById(R.id.TextView);
		//hide actionbar
		getSupportActionBar().hide();
		//set some splash screen(text for now)
		textView.setText(txt);

		// Enable JavaScript
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setAllowFileAccess(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setForceDark(1);
		// Set a WebViewClient to handle page navigation inside the WebView
		webView.setWebViewClient(new CustomWebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		// Load the URL
		if (savedInstanceState == null) {
			webView.loadUrl(yourCustomURL);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		webView.saveState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		webView.restoreState(savedInstanceState);
	}

	// Handle the back button to navigate inside the WebView instead of closing the app
	@Override
	public void onBackPressed() {
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}

	private class CustomWebViewClient extends WebViewClient {
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			// Load a custom error page with error details as query parameters
			String errorPageUrl = customErrorPage + "?errorCode=" + errorCode + "&description=" + description + "&url="
					+ failingUrl + "&ext=" + yourCustomURL + "eName=" + view;
			webView.loadUrl(errorPageUrl);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// Show the webview and hide the TextView
			findViewById(R.id.TextView).setVisibility(View.GONE);
			findViewById(R.id.webView).setVisibility(View.VISIBLE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// Show the TextView and hide the webview
			findViewById(R.id.TextView).setVisibility(View.VISIBLE);
			findViewById(R.id.webView).setVisibility(View.GONE);
		}
	}

}
