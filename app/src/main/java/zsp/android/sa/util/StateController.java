package zsp.android.sa.util;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.data.AppData;
import zsp.android.sa.fragment.DaySubjectsFragment;
import zsp.android.sa.fragment.NoScheduleFragment;
import zsp.android.sa.fragment.SchedulesFragment;
import zsp.android.sa.fragment.SubjectsFragment;

public class StateController {

    public static void update(BaseActivity activity) {
        updateUserName(activity);
        updateOptionsMenu(activity);
        updateFragments(activity);
    }

    private static void updateUserName(BaseActivity activity) {
        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView userNameTV = (TextView) header.findViewById(R.id.userName);
        MenuItem logOut = navigationView.getMenu().getItem(3);

        if (AppData.user == null) {
            userNameTV.setText("");
            logOut.setEnabled(false);
            logOut.setVisible(false);
            AppData.editing = false;
        } else {
            userNameTV.setText(AppData.user);
            logOut.setEnabled(true);
            logOut.setVisible(true);
        }
    }

    private static void updateOptionsMenu(BaseActivity activity) {
        if (AppData.currentFragment != null && !AppData.editing &&
                (AppData.currentFragment instanceof SchedulesFragment ||
                AppData.currentFragment instanceof SubjectsFragment)) {

            if (activity.optionsMenu != null) {
                if (!(AppData.currentFragment instanceof NoScheduleFragment) || AppData.savedSchedule != null) {
                    activity.optionsMenu.getItem(1).setVisible(true);
                    activity.optionsMenu.getItem(1).setEnabled(true);
                } else {
                    activity.optionsMenu.getItem(1).setVisible(false);
                    activity.optionsMenu.getItem(1).setEnabled(false);
                }
            }
        } else {
            activity.optionsMenu.getItem(1).setVisible(false);
            activity.optionsMenu.getItem(1).setEnabled(false);
        }

        if (AppData.currentFragment != null && AppData.user != null &&
                AppData.currentFragment instanceof SubjectsFragment &&
                (!(AppData.currentFragment instanceof NoScheduleFragment )|| AppData.savedSchedule != null)) {

            if (activity.optionsMenu != null) {
                if (AppData.editing) {
                    if (AppData.changes) {
                        activity.optionsMenu.getItem(2).setTitle("Сохранить");
                    } else {
                        activity.optionsMenu.getItem(2).setTitle("Режим просмотра");
                    }
                } else {
                    activity.optionsMenu.getItem(2).setTitle("Режим редактирования");
                }

                activity.optionsMenu.getItem(2).setVisible(true);
                activity.optionsMenu.getItem(2).setEnabled(true);
            }
        } else {
            if (activity.optionsMenu != null) {
                activity.optionsMenu.getItem(2).setVisible(false);
                activity.optionsMenu.getItem(2).setEnabled(false);
            }
        }
    }

    private static void updateFragments(BaseActivity activity) {
        if (AppData.currentFragment != null) {
            if (AppData.currentFragment instanceof SchedulesFragment) {
                FloatingActionButton fab = (FloatingActionButton) AppData.currentFragment
                        .getView().findViewById(R.id.fbAddSchedule);

                if (AppData.user != null) {
                    fab.setVisibility(View.VISIBLE);
                } else {
                    fab.setVisibility(View.GONE);
                }
            } else if (AppData.currentFragment instanceof SubjectsFragment) {
                if (!(AppData.currentFragment instanceof NoScheduleFragment) || AppData.savedSchedule != null) {
                    if (AppData.currentFragment instanceof NoScheduleFragment) {
                        View view = AppData.currentFragment.getView();

                        if (view != null) {
                            view.findViewById(R.id.coordLayout).setVisibility(View.VISIBLE);
                            view.findViewById(R.id.no_schedule).setVisibility(View.GONE);
                        }
                    }

                    List<Fragment> fragments = ((SubjectsFragment) AppData.currentFragment)
                            .getAdapter().getmFragmentList();

                    for (Fragment fragment : fragments) {
                        DaySubjectsFragment f = (DaySubjectsFragment) fragment;
                        FloatingActionButton fab = f.getFbAddSubject();
                        FloatingActionButton fab2 = f.getFbSaveSchedule();

                        if (fab != null && fab2 != null) {
                            if (AppData.user != null) {
                                if (AppData.editing) {
                                    fab.setVisibility(View.VISIBLE);
                                    fab2.setVisibility(View.GONE);
                                } else {
                                    fab.setVisibility(View.GONE);
                                    fab2.setVisibility(View.VISIBLE);
                                }
                            } else {
                                fab.setVisibility(View.GONE);
                                fab2.setVisibility(View.GONE);
                            }
                        }

                        if (fab2 != null && AppData.currentFragment instanceof NoScheduleFragment) {
                            fab2.setVisibility(View.GONE);
                        }

                        RecyclerView rc = ((DaySubjectsFragment) fragment).getRc();

                        if (rc != null) {
                            for (int i = 0; i < rc.getLayoutManager().getItemCount(); ++i) {
                                View viewItem = rc.getLayoutManager().findViewByPosition(i);

                                if (viewItem == null) {
                                    break;
                                }

                                Button button = (Button) viewItem.findViewById(R.id.btDelete);
                                ImageView imageView = (ImageView) viewItem.findViewById(R.id.ivDelete);

                                if (AppData.editing) {
                                    button.setEnabled(true);
                                    imageView.setVisibility(View.VISIBLE);
                                } else {
                                    button.setEnabled(false);
                                    imageView.setVisibility(View.INVISIBLE);
                                }
                            }
                        }
                    }
                } else {
                    View view = AppData.currentFragment.getView();

                    if (view != null) {
                        view.findViewById(R.id.coordLayout).setVisibility(View.GONE);
                        view.findViewById(R.id.no_schedule).setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}
