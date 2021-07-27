package com.example.shield.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dianping.agentsdk.framework.AgentCellBridgeInterface
import com.dianping.agentsdk.framework.DriverInterface
import com.dianping.agentsdk.framework.PageContainerInterface
import com.dianping.agentsdk.framework.WhiteBoard
import com.dianping.agentsdk.manager.SectionRecyclerCellManager
import com.dianping.shield.bridge.feature.ShieldGlobalFeatureInterface
import com.dianping.shield.framework.ShieldContainerInterface
import com.dianping.shield.framework.ShieldLifeCycler
import com.dianping.shield.manager.LightAgentManager
import com.sankuai.andytools.LogUtils

/**
 * Created by hezhi on 16/3/3.
 */
abstract class AgentManagerFragment(protected val shieldLifeCycler: ShieldLifeCycler = ShieldLifeCycler()) :
    Fragment(), AgentCellBridgeInterface by shieldLifeCycler,
    ShieldContainerInterface by shieldLifeCycler,
    DriverInterface by shieldLifeCycler, ShieldGlobalFeatureInterface by shieldLifeCycler {
    init {
        shieldLifeCycler.hostFragment = this
    }

    @JvmField
    var mRecyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shieldLifeCycler.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shieldLifeCycler.onCreateView(inflater, container, savedInstanceState)
        mRecyclerView = RecyclerView(context)
        return mRecyclerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shieldLifeCycler.cellManager = SectionRecyclerCellManager(context)
//        shieldLifeCycler.pageContainer =
        shieldLifeCycler.agentManager = LightAgentManager(
            this,
            shieldLifeCycler,
            this,
            shieldLifeCycler.pageContainer
        )

        shieldLifeCycler.shieldConfigs = generaterConfigs()
        shieldLifeCycler.onActivityCreated(savedInstanceState)
        setAgentContainerView(mRecyclerView!!)
        LogUtils.d(TAG, "⚠️onActivityCreated() called with: savedInstanceState = [$savedInstanceState]")
    }

    override fun onStart() {
        super.onStart()
        shieldLifeCycler.onStart()
    }

    override fun onResume() {
        super.onResume()
        shieldLifeCycler.onResume()
    }

    override fun onPause() {
        shieldLifeCycler.onPause()
        super.onPause()
    }

    override fun onStop() {
        shieldLifeCycler.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        shieldLifeCycler.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        shieldLifeCycler.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        shieldLifeCycler.onActivityResult(requestCode, resultCode, data)
    }


    /**
     * 在运行过程中,agentlist有变化的时候调用,更新agentlist列表本身的值,区别于dispatchCellChanged,
     * 单纯只更新现有的agentlist中的agent内容
     *
     * @param savedInstanceState
     */
    override fun resetAgents(savedInstanceState: Bundle?) {
        shieldLifeCycler.shieldConfigs = generaterConfigs()
        shieldLifeCycler.resetAgents(savedInstanceState)
    }

    fun setAgentContainerView(containerView: ViewGroup) {
        shieldLifeCycler.setAgentContainerView(containerView)
    }

    /**
    "override getWhiteBoard method to custom your WhiteBoard" +
    " is easy confused " +
    "you can use override initCellManager() method instead " +
    "and initCellManager() will be called on the fragment's onCreate lifecycle
    getWhiteBoard() method is only expect to provide host whiteboard to fragment and agents
     */
    override fun getWhiteBoard(): WhiteBoard? {
        return shieldLifeCycler.whiteBoard
    }

    companion object {
        private const val TAG = "AgentManagerFragment"
    }
}