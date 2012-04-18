package com.cary.makeup;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class MakeUpActivity extends Activity {
	
	ListView brandList;
	BrandAdapter adapter;
	private TextView mDialogText;
	SideBar sideBar;
	private WindowManager mWindowManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        brandList=(ListView)findViewById(R.id.brand_list);
        adapter=new BrandAdapter(getApplicationContext());
        brandList.setAdapter(adapter);
        sideBar=(SideBar)findViewById(R.id.side_bar);
        sideBar.setListView(brandList);
        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        mDialogText = (TextView) LayoutInflater.from(this).inflate(R.layout.list_position, null);
        mDialogText.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mDialogText, lp);
        sideBar.setTextView(mDialogText);
    }
}