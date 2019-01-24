package com.github.ont.connector.ontid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.ont.connector.R;
import com.github.ont.connector.base.CyanoBaseActivity;
import com.github.ont.connector.base.CyanoBaseFragment;
import com.github.ont.connector.utils.CommonUtil;
import com.github.ont.connector.utils.SDKCallback;
import com.github.ont.connector.utils.SDKWrapper;
import com.github.ont.connector.utils.SPWrapper;


/**
 * @author zhugang
 */
public class OntIdFragment extends CyanoBaseFragment implements View.OnClickListener {
    private static final String TAG = "IdentityFragment";

    private LinearLayout layoutNoIdentity;
    private LinearLayout layoutHasIdentity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ontid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutNoIdentity = (LinearLayout) view.findViewById(R.id.layout_no_identity);
        layoutHasIdentity = (LinearLayout) view.findViewById(R.id.layout_has_identity);
        initViewNoIdentity(view);
    }

    private void initViewNoIdentity(View view) {
        View btnNew = view.findViewById(R.id.btn_new);
        View btnImport = view.findViewById(R.id.btn_import);
        btnNew.setOnClickListener(this);
        btnImport.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (CommonUtil.isFastClick()) {
            int i = view.getId();
            if (i == R.id.btn_new) {
                baseActivity.startActivity(new Intent(baseActivity, CreateOntIdActivity.class));
//                createOntID();
            } else if (i == R.id.btn_import) {
                baseActivity.startActivity(new Intent(baseActivity, ImportOntIdActivity.class));
            }
        }
    }

    private void createOntID() {
        baseActivity.setGetDialogPwd(new CyanoBaseActivity.GetDialogPassword() {
            @Override
            public void handleDialog(String pwd) {
                baseActivity.showLoading();
                SDKWrapper.createIdentityWithAccount(new SDKCallback() {
                    @Override
                    public void onSDKSuccess(String tag, Object message) {
                        baseActivity.dismissLoading();
                    }

                    @Override
                    public void onSDKFail(String tag, String message) {
                        baseActivity.dismissLoading();
                    }
                }, TAG, "", pwd);
            }
        });
        baseActivity.showPasswordDialog("Create Identity");

    }


    @Override
    public void onStart() {
        super.onStart();
        SPWrapper.setContext(baseActivity);
        if (TextUtils.isEmpty(SPWrapper.getDefaultOntId())) {
            layoutNoIdentity.setVisibility(View.VISIBLE);
            layoutHasIdentity.setVisibility(View.GONE);
        } else {
            layoutNoIdentity.setVisibility(View.GONE);
            layoutHasIdentity.setVisibility(View.VISIBLE);
            startActivity(new Intent(baseActivity, OntIdWebActivity.class));
            baseActivity.finish();
        }
    }


}
