package com.matt.dashlabstabpager;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

import com.matt.dashlabpager.fragments.FuelFragment;
import com.matt.dashlabpager.fragments.FuelFragmentChart;
import com.matt.dashlabpager.fragments.FuelFragmentData;

public class DriverActivity extends FragmentActivity implements
ActionBar.TabListener, FuelFragment.OnTimeSelectedListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	FragmentManager fragManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), this);
		
		fragManager = getSupportFragmentManager();

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.driver, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		
		// Initialize the data
		if(tab.getPosition() == SectionsPagerAdapter.TAB_FUEL){
						
			// Perform the click to get us started
			Button btn = (Button) this.findViewById(R.id.btn_fuel_month);
			btn.performClick();
		}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTimeSelected(int time) {
		Log.d("Time", "Activity " + time);
		int spending = (int) (300*Math.random());
		int savings = (int)(100*Math.random());
		if(Math.random()*2 < 1){
			savings *= -1;
		}
		//int time = (int)(3*Math.random());
		
		FuelFragmentData frag = (FuelFragmentData) fragManager.findFragmentById(R.id.fuel_data_fragment);
		frag.updateData(time, spending, savings);
		
		FuelFragmentChart fragChart = (FuelFragmentChart) fragManager.findFragmentById(R.id.fuel_chart_fragment);
		
		Float[] values = new Float[5];
		for(int i=0; i<values.length; i++){
			values[i] = (float) Math.random();
		}
		
		fragChart.updateChart(time, values);
		
	}	

}
