package com.djzass.medipoint;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.content.Intent;



public class MainActivity extends Activity {

    public static boolean SERVICE_TIMER_STARTED = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start bg timer service
        startService(new Intent(this, TimerService.class));
        //set up the tab host
        final TabHost tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();

        //set treatment tab spec
        TabHost.TabSpec tabspec = tabhost.newTabSpec("Treatment");
        tabspec.setContent(R.id.TreatmentTabContent);
        tabspec.setIndicator(Html.fromHtml("Treatment"));
        //add tab
        tabhost.addTab(tabspec);

        //set appointment tab spec
        tabspec = tabhost.newTabSpec("Appointment");
        tabspec.setContent(R.id.AppointmentTabContent);
        tabspec.setIndicator("Appointment");
        //add tab
        tabhost.addTab(tabspec);

        //set history tab spec
        tabspec = tabhost.newTabSpec("History");
        tabspec.setContent(R.id.HistoryTabContent);
        tabspec.setIndicator("History");
        //add tab
        tabhost.addTab(tabspec);
        tabhost.setCurrentTab(1);

        //set tab color
        setTabColor(tabhost);
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {
                setTabColor(tabhost);
            }
        });

        //style the indicator color
        TabWidget tabwidget = tabhost.getTabWidget();

        for(int i=0; i < tabwidget.getChildCount(); i++){
            TextView tv = (TextView) tabwidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(this.getResources().getColorStateList(R.color.theme_bg));
        }

        //set up fragment manager
        FragmentManager fragManager = getFragmentManager();
        MedicalHistoryFragment historyFrag;

        //prevent the fragment from being duplicated
        if (fragManager.findFragmentById(R.id.HistoryTabContent) == null) {
            historyFrag = new MedicalHistoryFragment();

            //begin transaction
            FragmentTransaction fragTransaction = fragManager.beginTransaction();

            //add fragment to tab
            fragTransaction.add(R.id.HistoryTabContent, historyFrag, "Medical History Fragment");
            fragTransaction.commit();

        }
        else {
            historyFrag = (MedicalHistoryFragment) fragManager.findFragmentById(R.id.HistoryTabContent);
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
        else if(id==R.id.action_logout){
            AccountManager acctMgr = new AccountManager(this);
            acctMgr.logout();
            finish();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //method for tab background color
    public static void setTabColor(TabHost tabhost) {

        TabWidget tabwidget = tabhost.getTabWidget();

        // unselected
        for (int i = 0; i < tabwidget.getChildCount(); i++) {
            tabwidget.getChildAt(i)
                    .setBackgroundResource(R.drawable.tabunselected);
        }

        //selected
        tabwidget.getChildAt(tabhost.getCurrentTab())
                .setBackgroundResource(R.drawable.tabselected);

    }
}
