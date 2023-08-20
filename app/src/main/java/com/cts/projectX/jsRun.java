package com.cts.projectX;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import android.webkit.WebView;

public abstract class jsRun implements JsInter {
	public void alert(Context text) {
		new Toast(text);
	}

}