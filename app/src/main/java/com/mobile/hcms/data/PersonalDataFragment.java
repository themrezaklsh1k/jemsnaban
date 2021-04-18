package com.mobile.hcms.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobile.hcms.EmployeeDetailActivity;
import com.mobile.hcms.R;

import butterknife.ButterKnife;

/**
 * Created by jemsnaban on 12/13/16.
 */

public class PersonalDataFragment extends Fragment{

    Bundle bundle = null;

    private EmployeeDetailActivity activity;
    public PersonalDataFragment() {
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
        View view = inflater.inflate(R.layout.fragment_personal_data, container, false);

        ButterKnife.bind(this, view);



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
