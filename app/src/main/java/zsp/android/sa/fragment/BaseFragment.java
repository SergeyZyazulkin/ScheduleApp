package zsp.android.sa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;

import zsp.android.sa.activity.MainActivity;
import zsp.android.sa.data.AppData;
import zsp.android.sa.util.StateController;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppData.currentFragment = this;
        StateController.update((MainActivity) getActivity());
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!AppData.animation) {
            Animation a = new Animation() {
            };
            a.setDuration(0);
            return a;
        }

        return super.onCreateAnimation(transit, enter, nextAnim);
    }
}
