package com.example.shield.divider;

import com.dianping.agentsdk.framework.AgentListConfig;
import com.example.shield.base.AbsExampleFragment;

import java.util.ArrayList;

/**
 * Created by bingweizhou on 17/7/12.
 */

public class DividerFragment extends AbsExampleFragment {
    @Override
    public ArrayList<AgentListConfig> generateConfigs() {
        ArrayList<AgentListConfig> configs = new ArrayList<>();
        configs.add(new DividerAgentConfig());
        return configs;
    }

    @Override
    public String functionName() {
        return "Divider";
    }
}
