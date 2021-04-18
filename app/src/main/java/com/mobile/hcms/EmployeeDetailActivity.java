package com.mobile.hcms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mobile.hcms.data.AssignmentDataFragment;
import com.mobile.hcms.data.MaritalDataFragment;
import com.mobile.hcms.data.PersonalDataFragment;
import com.mobile.hcms.data.TimeDataFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class EmployeeDetailActivity extends AppCompatActivity implements MaterialTabListener {

    public static final String[] TITLE = {"Personal Data", "Marital Info", "Time Management", "Assignment"};

    @BindView(R.id.materialTabHost)
    MaterialTabHost tabHost;
    @BindView(R.id.viewpager)
    ViewPager pager;

    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Personal Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        adapter = new ViewPagerAdapter(getSupportFragmentManager()) {
            @Override
            public void setCommonTitle(int position) {
                //toolbar.setTitle(TITLE[position]);
            }
        };

        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
        } else if (id == android.R.id.home){
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private abstract class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            setCommonTitle(num);

            switch (num){
                case 0 :
                    return new PersonalDataFragment();
                case 1:
                    return new MaritalDataFragment();
                case 2:
                    return new TimeDataFragment();
                case 3:
                    return new AssignmentDataFragment();
                default:
                    return new PersonalDataFragment();
            }
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }

        public abstract void setCommonTitle(int position);

    }

}
