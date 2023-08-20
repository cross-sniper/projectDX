package com.cts.projectX;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public interface JsInter {
	@JavascriptInterface
	void alert(String text);
	void prompt(String text, String placeholder);
}
