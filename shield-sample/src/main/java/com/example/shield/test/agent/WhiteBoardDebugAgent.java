package com.example.shield.test.agent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dianping.agentsdk.framework.DriverInterface;
import com.dianping.agentsdk.framework.PageContainerInterface;
import com.dianping.agentsdk.framework.SectionCellInterface;
import com.dianping.agentsdk.framework.WhiteBoard;
import com.dianping.shield.agent.LightAgent;
import com.example.shield.test.cell.WhiteBoardDebugViewCell;
import com.sankuai.andytools.LogUtils;

import rx.Subscription;
import rx.functions.Action1;

public class WhiteBoardDebugAgent extends LightAgent {
    private static final String TAG = "WhiteBoardDebugAgent";
    WhiteBoardDebugViewCell whiteBoardDebugViewCell;
    private Subscription loadingSubscription;
    private Subscription emptySubscription;
    private Subscription failedSubscription;
    private Subscription moreSubscription;
    private Action1 onNext = new Action1() {
        @Override
        public void call(Object o) {
            LogUtils.d(TAG, "⚠️call() called with: o = [" + o + "]");
            updateWhiteBoardView();
        }
    };
    private WhiteBoard.MessageHandler messageHandler = new WhiteBoard.MessageHandler() {
        @Override
        public Object handleMessage(Object parameter) {
            LogUtils.d(TAG, "⚠️handleMessage() called with: parameter = [" + parameter + "]");
            updateWhiteBoardView();
            return null;
        }
    };

    private void updateWhiteBoardView() {
        if (whiteBoardDebugViewCell == null || whiteBoardDebugViewCell.getWhiteBoardDebugView() == null)
            return;
        whiteBoardDebugViewCell.getWhiteBoardDebugView().setData(getWhiteBoard());
    }

    public WhiteBoardDebugAgent(Fragment fragment, DriverInterface bridge, PageContainerInterface pageContainer) {
        super(fragment, bridge, pageContainer);
        whiteBoardDebugViewCell = new WhiteBoardDebugViewCell(getContext());
        whiteBoardDebugViewCell.setWhiteBoard(getWhiteBoard());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWhiteBoard().registerMessageHandler(TestLoadingAgent.KEY_TEST, messageHandler);
        getWhiteBoard().registerMessageHandler(TestLoadingAgent.KEY_TEST_2, messageHandler);
        loadingSubscription = getWhiteBoard().getObservable(TestLoadingAgent.KEY_LOADING).subscribe(onNext);
        emptySubscription = getWhiteBoard().getObservable(TestLoadingAgent.KEY_EMPTY).subscribe(onNext);

        failedSubscription = getWhiteBoard().getObservable(TestLoadingAgent.KEY_FAILED).subscribe(onNext);

        moreSubscription = getWhiteBoard().getObservable(TestLoadingAgent.KEY_MORE).subscribe(onNext);
    }

    @Override
    public void onDestroy() {
        if (loadingSubscription != null) {
            loadingSubscription.unsubscribe();
            loadingSubscription = null;
        }

        if (emptySubscription != null) {
            emptySubscription.unsubscribe();
        }

        if (failedSubscription != null) {
            failedSubscription.unsubscribe();
        }

        if (moreSubscription != null) {
            moreSubscription.unsubscribe();
        }
        getWhiteBoard().removeMessageHandler(TestLoadingAgent.KEY_TEST);
    }

    @Override
    public SectionCellInterface getSectionCellInterface() {
        return whiteBoardDebugViewCell;
    }
}
