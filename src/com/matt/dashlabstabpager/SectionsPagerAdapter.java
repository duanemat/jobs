package com.matt.dashlabstabpager;

import java.util.Locale;

import com.matt.dashlabpager.fragments.DriverFragment;
import com.matt.dashlabpager.fragments.FuelFragment;
import com.matt.dashlabpager.fragments.MpgFragment;
import com.matt.dashlabpager.fragments.ScoreFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	public static final int TAB_SCORE = 0;
	public static final int TAB_MPG = 1;
	public static final int TAB_FUEL = 2;
	public static final int TAB_DISTANCE = 3;
	public static final String FRAGMENT_HEADER = "fragment_header";

	Context c;
	public SectionsPagerAdapter(FragmentManager fm, Context c) {
		super(fm);
		this.c = c;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		Bundle args = new Bundle();

		switch(position){
		case TAB_SCORE:
			fragment = new ScoreFragment();
			break;

		case TAB_MPG:
			fragment = new MpgFragment();
			break;

		case TAB_FUEL:
			fragment = new FuelFragment();			
			break;

		case TAB_DISTANCE:
			fragment = new MpgFragment();
			break;

		default:
			fragment = new DriverFragment();
			break;
		}

		//args.putInt(DriverFragment.ARG_SECTION_NUMBER, position + 1);
		args.putString(SectionsPagerAdapter.FRAGMENT_HEADER, (String) getPageTitle(position));
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// Show 3 total pages.
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return c.getString(R.string.title_score).toUpperCase(l);
		case 1:
			return c.getString(R.string.title_mpg).toUpperCase(l);
		case 2:
			return c.getString(R.string.title_fuel).toUpperCase(l);
		case 3:
			return c.getString(R.string.title_distance).toUpperCase(l);
		}
		return null;
	}
}
