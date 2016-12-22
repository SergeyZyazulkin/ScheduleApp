package zsp.android.sa.activity;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnDismissListener;

import java.util.Timer;
import java.util.TimerTask;

import zsp.android.sa.R;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;
import zsp.android.sa.dialog.DialogPlusExtended;
import zsp.android.sa.fragment.NoScheduleFragment;
import zsp.android.sa.fragment.SchedulesFragment;
import zsp.android.sa.fragment.SubjectsFragment;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected Toolbar toolbar;
    protected DrawerLayout drawer;
    private DialogPlus currentDialogPlus;
    private Timer timer;

    protected void configureActivity() {
        initFragment();
        setToolbar();
        setSlidingMenu();
        setUserName(null);
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SubjectsFragment()).commit();
    }

    protected void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void setSlidingMenu() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setUserName(String user) {
        AppData.user = user;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView userNameTV = (TextView) header.findViewById(R.id.userName);
        MenuItem logOut = navigationView.getMenu().getItem(2);

        if (AppData.user == null) {
            userNameTV.setText("");
            logOut.setEnabled(false);
            logOut.setVisible(false);
        } else {
            userNameTV.setText(AppData.user);
            logOut.setEnabled(true);
            logOut.setVisible(true);
        }
    }

    public DialogPlus getCurrentDialogPlus() {
        return currentDialogPlus;
    }

    public void setCurrentDialogPlus(DialogPlus currentDialogPlus) {
        this.currentDialogPlus = currentDialogPlus;
    }

    public void showDialogPlus(final DialogPlus dialogPlus) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (currentDialogPlus != null && currentDialogPlus.isShowing()) {
            new DialogPlusExtended(currentDialogPlus).setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogPlus dialog) {
                    dialogPlus.show();
                    currentDialogPlus = dialogPlus;
                }
            });

            currentDialogPlus.dismiss();
        } else {
            currentDialogPlus = dialogPlus;
            dialogPlus.show();
        }
    }

    public void dismissDialogPlus() {
        if (currentDialogPlus != null && currentDialogPlus.isShowing()) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    BaseActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentDialogPlus.dismiss();
                        }
                    });
                }
            }, 0, 500);
        }
    }

    public boolean isShowingDialogPlus() {
        return currentDialogPlus != null && currentDialogPlus.isShowing();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search_item).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sign_up) {
            showDialogPlus(DialogFactory.createRegisterDialog(this));
        } else if (id == R.id.log_in) {
            showDialogPlus(DialogFactory.createLoginDialog(this));
        } else if (id == R.id.log_out) {
            setUserName(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
