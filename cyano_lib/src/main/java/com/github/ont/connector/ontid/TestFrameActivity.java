package com.github.ont.connector.ontid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.github.ont.connector.R;
import com.github.ont.connector.base.CyanoBaseActivity;


/**
 * @author zhugang
 */
public class TestFrameActivity extends CyanoBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_frame);
        initView();
    }

    private void initView() {
        OntIdFragment mTab2 = new OntIdFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager. beginTransaction();
        transaction.replace(R.id.frame, mTab2);
        transaction.commit();
    }
}
