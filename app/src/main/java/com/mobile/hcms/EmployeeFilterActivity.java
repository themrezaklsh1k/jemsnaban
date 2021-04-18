package com.mobile.hcms;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.mobile.hcms.filter.FilterCategoryEmpActivity;
import com.mobile.hcms.filter.FilterJobActivity;
import com.mobile.hcms.filter.FilterOrganizationActivity;
import com.mobile.hcms.filter.FilterTypeActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmployeeFilterActivity extends AppCompatActivity {

    @BindView(R.id.dateofbirth)
    TextView dateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.organization)
    void onFilterOrganization(){
        Intent intent = new Intent(getApplicationContext(), FilterOrganizationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.job)
    void onFilterJob(){
        Intent intent = new Intent(getApplicationContext(), FilterJobActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.category)
    void onFilterCategory(){
        Intent intent = new Intent(getApplicationContext(), FilterCategoryEmpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.type)
    void onFilterType(){
        Intent intent = new Intent(getApplicationContext(), FilterTypeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.effectivedate)
    void onFilterEffectiveDate(){
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(EmployeeFilterActivity.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SimpleDateFormat")
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                dateOfBirth.setText(new SimpleDateFormat("dd MMMM yyyy").format(newDate.getTime()));
                //selectedBirthday = new SimpleDateFormat("dd-MM-yyyy").format(newDate.getTime());
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @OnClick(R.id.applyfilter)
    void onApplyFilter(){
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filter, menu);
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
}
