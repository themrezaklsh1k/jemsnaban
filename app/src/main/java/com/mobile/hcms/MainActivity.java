package com.mobile.hcms;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Drawer result;

    boolean doubleBackToExitPressedOnce = false;

    public static final int MODEM = -999;
    public static final int DASHBOARD = 1000;
    public static final int PERSONAL = 1001;
    public static final int PERFORMANCE = 1002;
    public static final int TIME = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawer();

        switchFragment(DASHBOARD);
    }

    public void switchFragment(int contentId) {
        switchFragment(contentId, null);
    }

    public void switchFragment(int contentId, Bundle bundle) {
        try {
            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.content, getContent(contentId, bundle));
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Fragment getContent(int contentId, Bundle bundle) {
        Fragment fragment = null;
        switch (contentId) {
            case DASHBOARD:
                fragment = new DashboardFragment();
                break;
            case PERSONAL:
                fragment = new PersonalInfoFragment();
                break;
            case PERFORMANCE:
                fragment = new PerformanceFragment();
                break;
            case TIME:
                fragment = new TimeManagementFragment();
                break;
            default:
                fragment = new DashboardFragment();
                break;
        }

        if (bundle != null)
            fragment.setArguments(bundle);

        return fragment;
    }

    void setupDrawer(){
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg_drawer)
                .withOnAccountHeaderListener((view, profile, currentProfile) -> {
                    return true;
                })
                .withSelectionListEnabledForSingleProfile(false)
                .build();
        headerResult.addProfile(new ProfileDrawerItem()
                .withName("Technopartner Indonesia")
                .withEmail("guntur@technopartner.id")
                .withIdentifier(1)
                .withIcon(R.drawable.technopartner)
                .withTypeface(Typeface.createFromAsset(
                        getApplicationContext().getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf")),
                0);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_home)
                .withTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf"))
                .withIcon(getResources().getDrawable(R.drawable.nav_dashboard))
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.drawer_item_sent)
                .withTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf"))
                .withIcon(getResources().getDrawable(R.drawable.nav_person));
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.drawer_item_send)
                .withTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf"))
                .withIcon(getResources().getDrawable(R.drawable.nav_sales));
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.drawer_item_phonebook)
                .withTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf"))
                .withIcon(getResources().getDrawable(R.drawable.nav_time));

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    Timber.e("position: %s", String.valueOf(position));
                    switch (position){
                        case 1: switchFragment(DASHBOARD); break;
                        case 2: switchFragment(PERSONAL); break;
                        case 3: switchFragment(PERFORMANCE); break;
                        case 4: switchFragment(TIME); break;

                        case -1: logoutNow();break;
                        default: break;
                    }

                    result.closeDrawer();
                    return true;
                })
                .withSelectedItem(2)
                .build();

        result.addStickyFooterItem(new PrimaryDrawerItem().withName("Logout").
                withTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf"))
                .withIcon(getResources().getDrawable(R.drawable.nav_logout))
                .withBadge(new StringHolder("v." + version))
        );

        result.openDrawer();
        result.closeDrawer();
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

        result.getDrawerLayout();
    }

    public void logoutNow() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Logout HCM System")
                .setMessage("Are you sure you want to logout HCM System?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {

                    Intent intent = new Intent(getApplicationContext(), FrontPageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {

                })
                .show();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);

        if (fragment instanceof DashboardFragment) {
            if (doubleBackToExitPressedOnce) {
                System.exit(1);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
        }else if (fragment instanceof  PersonalInfoFragment
                || fragment instanceof TimeManagementFragment
                || fragment instanceof PerformanceFragment
                ){
            switchFragment(DASHBOARD, null);
            result.setSelection(2);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        result.closeDrawer();
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
        }

        return super.onOptionsItemSelected(item);
    }
}
