package com.mobile.hcms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jemsnaban on 12/13/16.
 */

public class PerformanceFragment extends Fragment{

    Bundle bundle = null;

    private MainActivity activity;
    public PerformanceFragment() {
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
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        ButterKnife.bind(this, view);

        activity.setTitle("Performance");



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
