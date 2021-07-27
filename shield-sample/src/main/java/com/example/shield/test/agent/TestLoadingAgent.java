package com.example.shield.test.agent;

import android.support.v4.app.Fragment;

import com.dianping.agentsdk.framework.DriverInterface;
import com.dianping.agentsdk.framework.PageContainerInterface;
import com.dianping.agentsdk.framework.SectionCellInterface;
import com.dianping.shield.agent.LightAgent;
import com.example.shield.test.cell.MixLoadingCell;

public class TestLoadingAgent extends LightAgent implements MixLoadingCell.MixLoadingListener {
    public static final String KEY_TEST = "test";
    public static final String KEY_TEST_2 = "test2";
    public static final String KEY_LOADING = "loading";
    public static final String KEY_EMPTY = "empty";
    public static final String KEY_FAILED = "failed";
    public static final String KEY_MORE = "more";
    public static final String KEY_DONE = "done";

    private MixLoadingCell mixLoadingCell;

    public TestLoadingAgent(Fragment fragment, DriverInterface bridge, PageContainerInterface pageContainer) {
        super(fragment, bridge, pageContainer);
        mixLoadingCell = new MixLoadingCell(getContext());
        mixLoadingCell.setOnMixLoadingListener(this);
    }

    @Override
    public SectionCellInterface getSectionCellInterface() {
        return mixLoadingCell;
    }

    @Override
    public void onLoading() {
        getWhiteBoard().putBoolean(KEY_LOADING, true);
    }

    @Override
    public void onEmpty() {
        getWhiteBoard().putBoolean(KEY_EMPTY, true);
    }

    @Override
    public void onFailed() {
        getWhiteBoard().putBoolean(KEY_FAILED, true);
    }

    @Override
    public void onMore() {
        getWhiteBoard().putBoolean(KEY_MORE, true);
    }

    @Override
    public void onDone() {
        getWhiteBoard().putBoolean(KEY_DONE, true);
    }

    @Override
    public void onTest() {
        getWhiteBoard().queryMessage(TestLoadingAgent.KEY_TEST, "parameter_test");
        getWhiteBoard().queryMessage(TestLoadingAgent.KEY_TEST_2, "parameter_test2");
    }
}
