package com.ycy.storehouse.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ycy.storehouse.LoginActivity;
import com.ycy.storehouse.R;
import com.ycy.storehouse.base.BaseFragment;
import com.ycy.storehouse.utils.SharedPreferencesUtils;

import butterknife.BindView;

public class UserFragment extends BaseFragment {

    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;

    @Override
    protected int getLayoutResource() {
        return R.layout.frament_user;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

        rlExit.setOnClickListener(v -> {
            SharedPreferencesUtils.clear();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });
    }
}
