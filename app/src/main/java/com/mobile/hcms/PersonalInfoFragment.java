package com.mobile.hcms;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;

import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mobile.hcms.adapter.EmployeeAdapter;
import com.mobile.hcms.filter.FilterNameActivity;
import com.mobile.hcms.model.AlphabetItemComparator;
import com.mobile.hcms.model.DummyData;
import com.mobile.hcms.model.EmployeeModel;
import com.viethoa.RecyclerViewFastScroller;
import com.viethoa.models.AlphabetItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by jemsnaban on 12/13/16.
 */

public class PersonalInfoFragment extends Fragment {//implements SwipeRefreshLayout.OnRefreshListener{

    //@BindView(R.id.swipe)
    //SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    //@BindView(R.id.fast_scroller)
    //RecyclerViewFastScroller fastScroller;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    EmployeeAdapter adapter;
    List<EmployeeModel> list;

    private MainActivity activity;
    public PersonalInfoFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();

        list = new ArrayList<>();
        adapter = new EmployeeAdapter(getActivity(), list) {
            @Override
            public void onMessageSelected(EmployeeModel position) {
                Intent intent = new Intent(getActivity(), EmployeeDetailActivity.class);
                startActivity(intent);
            }
        };
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        ButterKnife.bind(this, view);

        activity.setTitle("Personal Info");

        progressBar.setVisibility(View.GONE);
        //swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        //fastScroller.setRecyclerView(recyclerView);

        getAllEmployees();

        return view;
    }

    public void getAllEmployees(){
        list.clear();
        List<EmployeeModel> result = DummyData.getEmployees();
        list.addAll(result);

        ArrayList<AlphabetItem> mAlphabetItems = new ArrayList<>();
        List<String> strAlphabets = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            if (name == null || name.trim().isEmpty())
                continue;

            String word = name.substring(0, 1);
            if (!strAlphabets.contains(word)) {
                strAlphabets.add(word);
                mAlphabetItems.add(new AlphabetItem(i, word, false));
            }
        }

        boolean flag = false;
        ArrayList<AlphabetItem> alphabetItems = new ArrayList<>();
        for (char i = 'A' ; i <= 'Z'; i++){
            for (AlphabetItem a : mAlphabetItems){
                if (a.word.equals(String.valueOf(i))){
                    flag = true;
                    break;
                }
            }
            if (!flag)
                alphabetItems.add(new AlphabetItem(-1, String.valueOf(i), false));

            flag = false;
        }

        alphabetItems.addAll(mAlphabetItems);
        Collections.sort(alphabetItems, new AlphabetItemComparator());

        //fastScroller.setUpAlphabet(alphabetItems);

        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.filtername)
    void onFilterName(){
        Intent intent = new Intent(getActivity(), FilterNameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /*@Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_employee, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_sort){
            Intent in = new Intent(getActivity(), EmployeeFilterActivity.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
