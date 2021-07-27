package com.example.shield.linktype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dianping.agentsdk.framework.AgentListConfig;
import com.example.shield.R;
import com.example.shield.base.AbsExampleFragment;

import java.util.ArrayList;

/**
 * Created by nihao on 2017/7/14.
 */

public class LinkTypeFragment extends AbsExampleFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getContext());
        mRecyclerView.setBackgroundResource(R.color.gray_light_background);
        LinearLayout rootView = new LinearLayout(getContext());
        rootView.addView(mRecyclerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;
    }


    @Override
    public String functionName() {
        return "linkType";
    }

    @Override
    public ArrayList<AgentListConfig> generaterConfigs() {
        ArrayList<AgentListConfig> configs = new ArrayList<>();
        configs.add(new LinkTypeAgentConfig());
        return configs;
    }
}
