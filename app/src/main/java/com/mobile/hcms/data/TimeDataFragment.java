package com.mobile.hcms.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mobile.hcms.EmployeeDetailActivity;
import com.mobile.hcms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jemsnaban on 12/13/16.
 */

public class TimeDataFragment extends Fragment{

    Bundle bundle = null;
    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    private EmployeeDetailActivity activity;
    public TimeDataFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (EmployeeDetailActivity) getActivity();
        //activity.setTitle("Personal Data");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_data, container, false);

        ButterKnife.bind(this, view);

        spinner.setItems("Feb 20, 2017 - Feb 24, 2017", "Feb 27, 2017 - Mar 3, 2017",
                "Mar 6, 2017 - Mar 10, 2017", "Mar 13, 2017 - Mar 17, 2017", "Mar 20, 2017 - Mar 24, 2017");
        spinner.setOnItemSelectedListener((view1, position, id, item) -> {

        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
