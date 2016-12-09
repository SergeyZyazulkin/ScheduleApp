package zsp.android.sa.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnDismissListener;

import java.util.Timer;
import java.util.TimerTask;

import zsp.android.sa.R;
import zsp.android.sa.dialog.DialogPlusExtended;
import zsp.android.sa.menu.SlidingMenuFragment;

public class BaseActivity extends AppCompatActivity {

    private DialogPlus currentDialogPlus;
    private Timer timer;
    protected Toolbar toolbar;
    protected SlidingMenu menu;

    protected void configureActivity() {
        setToolbar();
        setSlidingMenu();
    }

    protected void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
    }

    protected void setSlidingMenu() {
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenu();
            }
        });

        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidth(16);
        menu.setBehindOffset(170);
        menu.setFadeDegree(0.35f);
        menu.setMode(SlidingMenu.LEFT);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(R.layout.fragment_sliding_menu);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, SlidingMenuFragment.newInstance())
                .commit();
    }

    public void toggleMenu() {
        menu.toggle();
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
}
