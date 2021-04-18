package com.mobile.hcms.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mobile.hcms.AssignmentDetailActivity;
import com.mobile.hcms.EmployeeDetailActivity;
import com.mobile.hcms.R;
import com.mobile.hcms.adapter.AssignmentAdapter;
import com.mobile.hcms.adapter.EmployeeAdapter;
import com.mobile.hcms.model.AssigmentModel;
import com.mobile.hcms.model.DummyData;
import com.mobile.hcms.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by jemsnaban on 12/13/16.
 */

public class AssignmentDataFragment extends Fragment{

    Bundle bundle = null;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    AssignmentAdapter adapter;
    List<AssigmentModel> list;

    private EmployeeDetailActivity activity;
    public AssignmentDataFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (EmployeeDetailActivity) getActivity();
        //activity.setTitle("Assignment Data");
        list = new ArrayList<>();
        adapter = new AssignmentAdapter(getActivity(), list) {
            @Override
            public void onMessageSelected(AssigmentModel position) {
                Intent intent = new Intent(getActivity(), AssignmentDetailActivity.class);
                startActivity(intent);
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_data, container, false);

        ButterKnife.bind(this, view);

        spinner.setItems( "ALL","PRIMARY", "SECONDARY",
                "TERTIARY");
        spinner.setOnItemSelectedListener((view1, position, id, item) -> {
            filterAssignment(item.toString());
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        getAllAssignments();

        return view;
    }

    void getAllAssignments(){

        list.clear();

        list.addAll(DummyData.getAssigments());
        adapter.notifyDataSetChanged();

    }

    void filterAssignment(String type){
        list.clear();

        if (type.equalsIgnoreCase("ALL")){
            list.addAll(DummyData.getAssigments());
        } else {
            List<AssigmentModel> models = DummyData.getAssigments();
            for (AssigmentModel model: models) {
                if (model.getType().equalsIgnoreCase(type)){
                    list.add(model);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
