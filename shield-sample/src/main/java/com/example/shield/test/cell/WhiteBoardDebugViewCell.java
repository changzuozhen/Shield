package com.example.shield.test.cell;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.dianping.agentsdk.debugtools.WhiteBoardDebugView;
import com.dianping.agentsdk.framework.WhiteBoard;
import com.dianping.shield.viewcell.BaseViewCell;

public class WhiteBoardDebugViewCell extends BaseViewCell {

    private WhiteBoardDebugView whiteBoardDebugView;
    private WhiteBoard mWhiteBoard;

    public WhiteBoardDebugView getWhiteBoardDebugView() {
        return whiteBoardDebugView;
    }

    public WhiteBoardDebugViewCell(Context context) {
        super(context);
    }

    @Override
    public int getSectionCount() {
        return 1;
    }

    @Override
    public int getRowCount(int sectionPosition) {
        return 1;
    }

    @Override
    public int getViewType(int sectionPosition, int rowPosition) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View onCreateView(ViewGroup parent, int viewType) {
        whiteBoardDebugView = new WhiteBoardDebugView(getContext());
//        whiteBoardDebugView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                300));
        whiteBoardDebugView.setData(mWhiteBoard);
        return whiteBoardDebugView;
    }

    @Override
    public void updateView(View view, int sectionPosition, int rowPosition, ViewGroup parent) {

    }

    public void setWhiteBoard(WhiteBoard whiteBoard) {
        mWhiteBoard = whiteBoard;
    }
}
