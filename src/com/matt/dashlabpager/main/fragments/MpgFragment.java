package com.matt.dashlabpager.main.fragments;

import com.matt.dashlabpager.main.SectionsPagerAdapter;
import com.matt.dashlabstabpager.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MpgFragment extends Fragment {
	
	public MpgFragment(){
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_driver_dummy,
				container, false);
		TextView titleView = (TextView) rootView
				.findViewById(R.id.section_label);		
		titleView.setText(getArguments().getString(SectionsPagerAdapter.FRAGMENT_HEADER));
		
		return rootView;
	}
}
