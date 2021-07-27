package com.example.shield.test;

import com.dianping.agentsdk.framework.AgentInfo;
import com.dianping.agentsdk.framework.AgentInterface;
import com.dianping.agentsdk.framework.AgentListConfig;
import com.dianping.agentsdk.utils.AgentInfoHelper;

import java.util.Map;

public class MixAgentConfig implements AgentListConfig {
    private final static String AGENT_PKG_NAME = "com.example.shield.test.agent.";

    @Override
    public boolean shouldShow() {
        return true;
    }

    @Override
    public Map<String, AgentInfo> getAgentInfoList() {
        String[][][] agentArray = {
                {
                        {"test0", AGENT_PKG_NAME + "TestLoadingAgent"},
                        {"test1", AGENT_PKG_NAME + "WhiteBoardDebugAgent"},
                        {"test2", AGENT_PKG_NAME + "TestCellAgent"}
                }
        };
        return AgentInfoHelper.getAgents(agentArray);
    }

    @Override
    public Map<String, Class<? extends AgentInterface>> getAgentList() {
        return null;
    }
}
