package com.gree.hwb.materialdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Administrator on 2016/7/11.
 */
public class HelpWebViewActivity extends AppCompatActivity
{
	private WebView wv_help ;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		wv_help = (WebView) findViewById(R.id.wv_help);
		wv_help.loadUrl("file:///android_asset/help.html");
	}
}
