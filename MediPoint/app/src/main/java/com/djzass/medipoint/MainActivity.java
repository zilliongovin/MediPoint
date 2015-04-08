package com.djzass.medipoint;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.djzass.medipoint.logic_manager.AccountManager;
import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;


public class MainActivity extends FragmentActivity {

    public static boolean SERVICE_TIMER_STARTED = false;
   public static Container GlobalContainer = new Container();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AccountManager acctMgr = new AccountManager(this);
        //get Patient ID
        //String id = GlobalContainer.GlobalAccountManager.getLoggedInAccountId();
        Toast.makeText(this,""+acctMgr.getLoggedInAccountId(this),Toast.LENGTH_SHORT).show();

        //start bg timer service
        startService(new Intent(this, TimerService.class));

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));

        // Give the SlidingTabLayout the ViewPager
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);

        // Set custom tab layout
        slidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);

        // Center the tabs in the layout
        slidingTabLayout.setDistributeEvenly(true);

        // Customize tab color
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.theme_bg);
            }
        });
        slidingTabLayout.setViewPager(viewPager);

        // Calling Application class (see application tag in AndroidManifest.xml)
        //final Container globalVar = (Container) getApplicationContext();


    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 2;
        //final int PAGE_COUNT = 3;
        //private String tabTitles[] = new String[] { "Appointment", "Tab2", "Tab3" };
        private String tabTitles[] = new String[]{"Appointment", "Medical History"};

        public SampleFragmentPagerAdapter(android.support.v4.app.FragmentManager fm, MainActivity mainActivity) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return AppointmentListFragment.newInstance();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return MedicalHistoryFragment.newInstance();
                /*case 2: // Fragment # 1 - This will show SecondFragment
                    return SecondFragment.newInstance(2, "Page # 3");*/
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //logout menu item selected
        else if (id == R.id.action_logout) {
            AccountManager acctMgr = new AccountManager(this);
            acctMgr.logout();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
