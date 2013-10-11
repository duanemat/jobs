package com.matt.dashlabpager.main.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matt.dashlabstabpager.R;

public class FuelFragmentData extends Fragment {

	Context context;

	public FuelFragmentData(){
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

		View rootView = inflater.inflate(R.layout.frag_fuel_data,
				container, false);		
		
		TextView fuelDollarsView = (TextView)rootView.findViewById(R.id.fuel_spending);
		Typeface typeface = Typeface.createFromAsset(this.getResources().getAssets(), "fonts/Roboto-Bold.ttf");
		fuelDollarsView.setTypeface(typeface);
		

		return rootView;
	}

	// Update the fuel information
	public void updateData(int timePeriod, int spending, int savings){

		TextView periodSpendingView = (TextView) this.getView().findViewById(R.id.fuel_spending_label);
		TextView periodSavingsView = (TextView) this.getView().findViewById(R.id.fuel_savings_label);
		switch(timePeriod){
		case FuelFragment.WEEK:
			periodSpendingView.setText(R.string.fuel_label_week_spending);
			periodSavingsView.setText(R.string.fuel_label_week_savings);
			break;

		case FuelFragment.MONTH:
			periodSpendingView.setText(R.string.fuel_label_month_spending);
			periodSavingsView.setText(R.string.fuel_label_month_savings);
			break;

		case FuelFragment.YEAR:
			periodSpendingView.setText(R.string.fuel_label_year_spending);
			periodSavingsView.setText(R.string.fuel_label_year_savings);
			break;
		}

		// Set the fuel spending values
		TextView spendingView = (TextView) this.getView().findViewById(R.id.fuel_spending);
		spendingView.setText("$"+String.valueOf(spending));

		TextView savingsView = (TextView) this.getView().findViewById(R.id.fuel_savings);
		savingsView.setText("$"+String.valueOf(savings));

		// Set the arrow and color of the text depending on if driver is saving money or not
		if(savings < 0){
			savingsView.setTextColor(Color.RED);
			Drawable dr = this.getResources().getDrawable(R.drawable.arrow_up_float);
			Drawable icon = changeIconColor(dr, false);
			//savingsView.setCompoundDrawables(dr, null, null, null);
			savingsView.setCompoundDrawablesWithIntrinsicBounds(dr, null, null, null);
		}
		else{
			savingsView.setTextColor(Color.GREEN);
			Drawable dr = this.getResources().getDrawable(R.drawable.arrow_down_float);
			Drawable icon = changeIconColor(dr, true);
			savingsView.setCompoundDrawablesWithIntrinsicBounds(dr, null, null, null);
		}

	}

	// Simple function that allows you to change the color of a drawable programatically
	private Drawable changeIconColor(Drawable drawable, boolean makeGreen){

		if(drawable == null)
			return null;

		Drawable icon = drawable.mutate();
		icon.clearColorFilter();

		if(makeGreen){			
			icon.setColorFilter(Color.GREEN, Mode.SRC_IN);
		}else{
			icon.setColorFilter(Color.RED, Mode.SRC_IN);
		}

		return icon;
	}

}
