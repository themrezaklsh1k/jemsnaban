package com.mobile.hcms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobile.hcms.data.AssignmentDataFragment;
import com.mobile.hcms.data.MaritalDataFragment;
import com.mobile.hcms.data.PersonalDataFragment;
import com.mobile.hcms.data.TimeDataFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by jemsnaban on 12/13/16.
 */

public class DashboardFragment extends Fragment implements MaterialTabListener {

    Bundle bundle = null;

    public static final String[] TITLE = {"Notification Message", "Approval Task", "Outgoing Task", "Rejected Task"};

    @BindView(R.id.materialTabHost)
    MaterialTabHost tabHost;
    @BindView(R.id.viewpager)
    ViewPager pager;

    ViewPagerAdapter adapter;

    private MainActivity activity;
    public DashboardFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ButterKnife.bind(this, view);

        activity.setTitle("Dashboard");

        adapter = new ViewPagerAdapter(getFragmentManager()) {
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

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
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
            return new DashboardDataFragment();
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
