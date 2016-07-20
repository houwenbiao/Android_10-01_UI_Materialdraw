package com.gree.hwb.materialdraw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity
{
	private AccountHeader headerResult = null;
	private Drawer result = null;
	static int update_flag = 0;
	IProfile profile;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_title);
		setSupportActionBar(toolbar);
		profile = new ProfileDrawerItem().withName(getString(R.string.app_name)).withEmail("珠海格力电器股份有限公司").withIcon(getResources().getDrawable(R.drawable.search));
		buildHeader(false, savedInstanceState);
		// Create the AccountHeader
		//new DrawerBuilder().withActivity(this).build();
		PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withIcon(R.drawable.wifi).withName("wifi升级");
		PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withIcon(R.drawable.help).withName("帮助文档");

		result = new DrawerBuilder()
						 .withActivity(this)
						 .withToolbar(toolbar)
						 .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
						 .addDrawerItems(item1, item2)
						 .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener()
						 {
							 @Override
							 public boolean onNavigationClickListener(View clickedView)
							 {
								 MainActivity.this.finish();
								 return true;
							 }
						 })
						 .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
						 {
							 @Override
							 public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
							 {
								 // 添加监听事件
								 if(position == 1)
								 {
									 // Handle the camera action
									 if(update_flag == 1)
									 {
										 Toast.makeText(getApplicationContext(), "网络升级成功", Toast.LENGTH_LONG).show();
									 } else if(update_flag == 2)
									 {
										 Toast.makeText(getApplicationContext(), "无网络连接，请先连接WiFi", Toast.LENGTH_LONG).show();
									 } else
									 {
										 Toast.makeText(getApplicationContext(), "已是最新程序", Toast.LENGTH_LONG).show();
									 }

								 } else if(position == 2)
								 {
									 Intent intent = new Intent(MainActivity.this,HelpWebViewActivity.class);
									 startActivity(intent);
								 }
								 return false;//false:点击之后返回上一Activity，true:相反
							 }
						 }).withSavedInstance(savedInstanceState).build();
	}
	private void buildHeader(boolean compact, Bundle savedInstanceState)
	{
		// Create the AccountHeader
		headerResult = new AccountHeaderBuilder()
							   .withActivity(this)
							   .withAlternativeProfileHeaderSwitching(false)
							   .withCloseDrawerOnProfileListClick(true)
							   .withSelectionListEnabled(false)
							   .withCurrentProfileHiddenInList(true)
							   .withHeaderBackground(R.drawable.side_nav_bar)
							   .withCompactStyle(compact)
							   .addProfiles(profile)
							   .withSavedInstance(savedInstanceState)
							   .build();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_titleflowover, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
