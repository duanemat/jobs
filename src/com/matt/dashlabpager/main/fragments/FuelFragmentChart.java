package com.matt.dashlabpager.main.fragments;

import com.matt.dashlabstabpager.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FuelFragmentChart extends Fragment {

	Context context;

	public FuelFragmentChart(){
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			context = activity;
		}catch (Exception e){
			Log.e("Error", "Bad stuff");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.frag_fuel_chart,
				container, false);
		
		TextView fuelChartView = (TextView)rootView.findViewById(R.id.fuel_chart_text);
		Typeface typeface = Typeface.createFromAsset(this.getResources().getAssets(), "fonts/Roboto-Bold.ttf");
		fuelChartView.setTypeface(typeface);

		return rootView;
	}
	
	public void updateChart(int timeframe, Float[] values){
		TextView chartText = (TextView)this.getView().findViewById(R.id.fuel_chart_text);
		switch(timeframe){
		case FuelFragment.WEEK:
			chartText.setText("Week");
			break;
			
		case FuelFragment.MONTH:
			chartText.setText("Month");
			break;
			
		case FuelFragment.YEAR:
			chartText.setText("Year");
			break;			
		}
		
		if(values == null)
			return;
		
		// Clear out the layout
		LinearLayout chartLayout = (LinearLayout)this.getView().findViewById(R.id.fuel_chart_container);
		chartLayout.removeAllViews();
		
		// Cycle through each entry in the values array and create a chart element
		for(Float val: values){
			LinearLayout ll = new LinearLayout(context);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(5, LayoutParams.WRAP_CONTENT, 1.0f);
			layoutParams.setMargins(10, 0, 10, 0);			
			ll.setOrientation(LinearLayout.VERTICAL);			
			
			int posHeight = (int)(300*val);
			
			LinearLayout topLayout = new LinearLayout(context);
			topLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 300-posHeight));
			topLayout.setOrientation(LinearLayout.VERTICAL);
			
			LinearLayout bottomLayout = new LinearLayout(context);
			bottomLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, posHeight));
			bottomLayout.setOrientation(LinearLayout.VERTICAL);
			bottomLayout.setBackgroundColor(Color.BLUE);
			
			ll.addView(topLayout);
			ll.addView(bottomLayout);
			
			chartLayout.addView(ll, layoutParams);
			
		}
		
	}
}
