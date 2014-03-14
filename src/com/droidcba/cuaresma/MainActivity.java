package com.droidcba.cuaresma;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		int cardNumber = 0;
		int month = Calendar.MONTH + 1;
		int day = 5;
		Date d = new Date();
		Calendar today = Calendar.getInstance();
		today.setTime(d);
		day = today.get(Calendar.DAY_OF_MONTH);
		
		if( month == 3) {
			cardNumber = day - 5;
		} else if (month == 4) {
			cardNumber = day + 26;
		}
		
		mViewPager.setCurrentItem(cardNumber);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
            	Intent share = new Intent(Intent.ACTION_SEND);
				share.setType("text/plain");
				share.putExtra(Intent.EXTRA_TEXT, "http://www.catolicosconaccion.com/");
                share.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sumate a Cuaresma con Acción!");
                
				startActivity(Intent.createChooser(share, "Compartir con"));
	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			
			int day = getDayByPosition(position);
			int month = getMonthByPosition(position);
			
			args.putString(DummySectionFragment.ARG_SECTION_NUMBER, "dia_" + month + "_" + day);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 40 total pages.
			return 40;
		}

		private int getDayByPosition(int position) {
			if(position > 26) {
				return position - 26;
			} else {
				return position + 5;
			}
		}
		
		private int getMonthByPosition(int position) {
			if(position > 26) {
				return 4;
			} else {
				return 3;
			}
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			
			if(position > 26) {
				return position - 26 + " de Abril";
			} else {
				return position + 5 + " de Marzo";
			}
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			
			String image_day = getArguments().getString(ARG_SECTION_NUMBER);

			ImageView iv = (ImageView) rootView.findViewById(R.id.imageView1);
			int drawableResourceId = this.getResources().getIdentifier(image_day, "drawable", "com.droidcba.cuaresma");
			iv.setImageResource(drawableResourceId);
			
			return rootView;
		}
	}

}
