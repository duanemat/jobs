package com.matt.dashlabpager.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.matt.dashlabstabpager.R;
import com.matt.dashlabstabpager.R.id;
import com.matt.dashlabstabpager.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DriverFragment extends Fragment {

	public static final String SECTION_HEADER = "section_header";
	

	private List<Double> s;

	private LinearLayout linearChart;
	
	public DriverFragment(){

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
				
		
		View rootView = inflater.inflate(R.layout.fragment_driver_dummy,
				container, false);
		TextView titleView = (TextView) rootView
				.findViewById(R.id.section_label);		
		titleView.setText(getArguments().getString(SECTION_HEADER));
		
		return rootView;		
	}
	


}
