package com.app.qfonapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.qfonapp.R
import com.app.qfonapp.adapter.PollAdapter
import com.app.qfonapp.base.QfonappFragment
import com.app.qfonapp.database.AppDatabase
import com.app.qfonapp.database.DbCreatePoll
import com.app.qfonapp.database.DbPollOption
import com.app.qfonapp.databinding.FragmentPollHistoryBinding
import com.app.qfonapp.model.Option
import com.app.qfonapp.model.Poll
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PollHistoryFragment : QfonappFragment() {

    private lateinit var b: FragmentPollHistoryBinding
    private var database: AppDatabase? = null
    private var adapter: PollAdapter? = null
    private val pollOptionList: MutableList<Poll> = ArrayList()
    private val isAttempt: Int = 1
    private val isAttempted: Boolean = true

    ///////////////////////////////////////////////////////////////////////////
    // Override Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_poll_history, container, doNot_)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    private fun init() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            database = AppDatabase.getAppDataBase(requireActivity())

            initActionBar()
            initAdapter()
            getPoll()
        }
    }

    private fun initActionBar() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.actionBar.lblTitle.text = getString(R.string.history)
            b.actionBar.imgBack.visibility = View.INVISIBLE
        }
    }

    private fun initAdapter() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            adapter = PollAdapter(pollOptionList, null, isAttempted)
            b.rvPoll.adapter = adapter
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("NotifyDataSetChanged")
    private fun getPoll() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            if (database != null) {
                val pollList: MutableList<DbCreatePoll> = ArrayList()
                if (pollList.isNotEmpty()) pollList.clear()
                pollList.addAll(database!!.createPollDao().getPoll(isAttempt))
                if (pollList.size > 0) {
                    b.lblError.visibility = View.GONE
                    b.scrollLayout.visibility = View.VISIBLE
                    b.rvPoll.visibility = View.VISIBLE
                    for (i in pollList.indices) {
                        val optionList: MutableList<DbPollOption> = ArrayList()
                        if (optionList.isNotEmpty()) optionList.clear()
                        optionList.addAll(
                            database!!.addPollOptionDao().getPollOption(pollList[i].id)
                        )
                        val option: MutableList<Option> = ArrayList()
                        if (option.isNotEmpty()) option.clear()
                        for (j in optionList.indices) {
                            option.add(
                                j,
                                Option(
                                    optionList[j].pollId!!,
                                    optionList[j].id,
                                    optionList[j].pollOption,
                                    optionList[j].optionAttempted
                                )
                            )
                        }
                        pollOptionList.add(i, Poll(pollList[i].id, pollList[i].title, option))
                    }
                    adapter!!.notifyDataSetChanged()
                } else {
                    b.rvPoll.visibility = View.GONE
                    b.scrollLayout.visibility = View.GONE
                    b.lblError.visibility = View.VISIBLE
                    b.lblError.text = getString(R.string.history_poll_error)
                }
            }
        }
    }
}