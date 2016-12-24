package zsp.android.sa.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnDismissListener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import reqlib.client.ServerParameters;
import reqlib.hmi.ScheduleClientInterface;
import reqlib.hmi.request.FindRequest;
import reqlib.hmi.request.RefreshRequest;
import reqlib.hmi.request.UpdateRequest;
import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.adapter.SubjectsViewAdapter;
import zsp.android.sa.callback.FindCallbackHandler;
import zsp.android.sa.callback.RefreshCallbackHandler;
import zsp.android.sa.callback.UpdateCallbackHandler;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;
import zsp.android.sa.dialog.DialogPlusExtended;
import zsp.android.sa.fragment.DaySubjectsFragment;
import zsp.android.sa.fragment.NoScheduleFragment;
import zsp.android.sa.fragment.SchedulesFragment;
import zsp.android.sa.fragment.SubjectsFragment;
import zsp.android.sa.util.StateController;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Menu optionsMenu;
    protected Toolbar toolbar;
    protected DrawerLayout drawer;
    protected SearchView searchView;
    private DialogPlus currentDialogPlus;
    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppData.clear = false;

        AppData.clientInterface = new ScheduleClientInterface(
                new ServerParameters("https://hmi-schedule.herokuapp.com/", 30000, 30000));
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (AppData.clear == null) {
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    protected void configureActivity() {
        setToolbar();
        setSlidingMenu();
        initFragment();
    }

    private void initFragment() {
        addFragment(R.id.fragment_container, new NoScheduleFragment(), false);
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

    public void addFragment(int containerId, Fragment fragment, boolean backStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(containerId, fragment);

        if (backStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public void replaceFragment(int containerId, Fragment fragment, boolean backStack, boolean pop) {
        if (pop) {
            AppData.animation = false;
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();

            for (int i = 0; i < count; ++i) {
                fm.popBackStackImmediate();
            }

            AppData.animation = true;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(containerId, fragment);

        if (backStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public void setUserName(String user) {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String saved = sp.getString(user, null);

        if (user != null && saved != null) {
            AppData.savedSchedule = new Gson().fromJson(saved, ScheduleJson.class);
        } else {
            AppData.savedSchedule = null;
            AppData.editingSchedule = null;
            AppData.currentSchedule = null;
        }

        if (AppData.currentFragment instanceof NoScheduleFragment) {
            AppData.currentSchedule = AppData.savedSchedule;

            if (AppData.savedSchedule != null) {
                AppData.editingSchedule = AppData.savedSchedule.getCopy();
            }

            List<Fragment> fragmentList = ((SubjectsFragment) AppData.currentFragment).getAdapter().getmFragmentList();

            for (int i = 0; i < 7; ++i) {
                DaySubjectsFragment dsf = (DaySubjectsFragment) fragmentList.get(i);

                if (dsf.getRc() != null) {
                    dsf.getRc().setAdapter(new SubjectsViewAdapter(this, i + 1));
                    dsf.getRc().invalidate();
                }
            }
        }

        AppData.user = user;
        StateController.update(this);
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
        } else if (isShowingDialogPlus()) {
            dismissDialogPlus();
        } else if (AppData.changes) {
            showDialogPlus(DialogFactory.createWarningDialog(this));
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        optionsMenu = menu;
        getMenuInflater().inflate(R.menu.main2, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search_item).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        StateController.update(this);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            AppData.search = query;
            searchView.clearFocus();
            searchView.setQuery(null, false);
            optionsMenu.getItem(0).collapseActionView();

            if (AppData.currentFragment instanceof SubjectsFragment && AppData.changes) {
                AppData.startSearch = true;
                showDialogPlus(DialogFactory.createWarningDialog(this));
            } else {
                FindRequest findRequest = new FindRequest(query);
                showDialogPlus(DialogFactory.createProgressDialog(this, "Поиск расписаний..."));
                AppData.clientInterface.find(findRequest, new FindCallbackHandler(this));
            }
        }
    }

    public void saveSavedSchedule() {
        getPreferences(MODE_PRIVATE).edit().putString(AppData.user, new Gson().toJson(AppData.savedSchedule)).apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.edit_item) {
            if (item.getTitle().equals("Сохранить")) {
                UpdateRequest updateRequest = new UpdateRequest(AppData.editingSchedule);
                showDialogPlus(DialogFactory.createProgressDialog(this, "Сохранение расписания..."));
                AppData.clientInterface.update(updateRequest, new UpdateCallbackHandler(this));
            } else {
                AppData.editing = !AppData.editing;
                AppData.changes = false;
                StateController.update(this);
            }

            return true;
        } else if (id == R.id.update_item) {
            if (AppData.currentFragment instanceof SchedulesFragment) {
                FindRequest findRequest = new FindRequest(AppData.search);
                showDialogPlus(DialogFactory.createProgressDialog(this, "Поиск расписаний..."));
                AppData.clientInterface.find(findRequest, new FindCallbackHandler(this));
            } else {
                RefreshRequest refreshRequest = new RefreshRequest(AppData.currentSchedule.get_id());
                showDialogPlus(DialogFactory.createProgressDialog(this, "Обновление расписания..."));
                AppData.clientInterface.refresh(refreshRequest, new RefreshCallbackHandler(this));
            }
        }

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
            if (AppData.changes) {
                AppData.logout = true;
                showDialogPlus(DialogFactory.createWarningDialog(this));
            } else {
                setUserName(null);
            }
        } else if (id == R.id.home) {
            if (AppData.changes) {
                AppData.home = true;
                showDialogPlus(DialogFactory.createWarningDialog(this));
            } else {
                AppData.editing = false;
                StateController.update(this);

                if (!(AppData.currentFragment instanceof NoScheduleFragment)) {
                    replaceFragment(R.id.fragment_container, new NoScheduleFragment(), false, true);
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
