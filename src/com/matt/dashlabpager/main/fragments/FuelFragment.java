package com.matt.dashlabpager.main.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.matt.dashlabstabpager.R;

public class FuelFragment extends Fragment {
	public static final int WEEK = 0;
	public static final int MONTH = 1;
	public static final int YEAR = 2;

	Context context;
	public interface OnTimeSelectedListener{
		public void onTimeSelected(int time);
	}

	OnTimeSelectedListener mListener;

	public FuelFragment(){

	}	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnTimeSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnTimeSelectedListener");
		}

	}

	public void setContext(Context context){
		this.context = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.frag_fuel,
				container, false);

		// Get the button bar and then cycle through, enabling listeners for each and the default text color
		LinearLayout buttonBar = (LinearLayout) rootView.findViewById(R.id.fuel_time_button_list);
		for(int i=0; i<buttonBar.getChildCount(); i++){
			Button btn = (Button) buttonBar.getChildAt(i);
			btn.setOnClickListener(new ButtonClickListener(container));
			btn.setTextColor(Color.LTGRAY);
		}

		return rootView;
	}
	

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		try{
			// Handles an issue I had with duplicate XML frames.
			// Answer:  http://stackoverflow.com/questions/14928833/android-app-error-duplicate-id-0x7f04000f-tag-null-or-parent-id-0x0-with-ano
			FragmentManager fm = getActivity().getSupportFragmentManager();
			Fragment fragment_data = (fm.findFragmentById(R.id.fuel_data_fragment));
			Fragment fragment_chart = (fm.findFragmentById(R.id.fuel_chart_fragment));
			FragmentTransaction ft = fm.beginTransaction();
			ft.remove(fragment_data);
			ft.remove(fragment_chart);
			ft.commit();}
		catch (Exception e){
			Log.e("Error", "Bad stuff!" + e.getMessage());
		}
	}


	class ButtonClickListener implements OnClickListener{

		int buttonPressed;
		View fragView;
		ButtonClickListener(View v){
			fragView = v;
		}

		@Override
		public void onClick(View v) {
			
			LinearLayout ll = (LinearLayout)fragView.findViewById(R.id.fuel_time_button_list);
			
			// Set all the tabs to unselected
			for(int i=0; i<ll.getChildCount(); i++){
				Button buttonView = (Button) ll.getChildAt(i);
				buttonView.setSelected(false);
				buttonView.setTextColor(Color.LTGRAY);
				buttonView.setClickable(true);
				
			}
			
			Button b = (Button)v;			
			b.setSelected(true);
			b.setTextColor(Color.BLUE);
			b.setClickable(false);
			
			/* This is admittedly a bit of a cheat, but for simplicity I just included a tag with the button index and, since I defined the same above, used those
			 * for reference
			 */			

			int timeframe = Integer.parseInt((String) v.getTag());
			mListener.onTimeSelected(timeframe);


		}

	}




}
