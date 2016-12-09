package zsp.android.sa.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import zsp.android.sa.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureActivity();
    }
}
