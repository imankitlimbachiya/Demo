package com.app.qfonapp.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import com.app.qfonapp.R
import com.app.qfonapp.adapter.AddOptionAdapter
import com.app.qfonapp.base.QfonappFragmentDialog
import com.app.qfonapp.database.AppDatabase
import com.app.qfonapp.database.AppDatabase.Companion.getAppDataBase
import com.app.qfonapp.database.DbCreatePoll
import com.app.qfonapp.database.DbPollOption
import com.app.qfonapp.databinding.FragmentAddPollBinding
import com.app.qfonapp.event.AddPollEvent
import com.app.qfonapp.interfaces.AddPollInterface
import com.app.qfonapp.model.PollOption
import com.app.qfonapp.util.Config.doNot_
import com.app.qfonapp.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus

@AndroidEntryPoint
class AddPollFragment : QfonappFragmentDialog() {

    private lateinit var b: FragmentAddPollBinding
    private var database: AppDatabase? = null
    private var adapter: AddOptionAdapter? = null
    private var optionList: MutableList<PollOption> = ArrayList()
    private var index: Int = 0

    ///////////////////////////////////////////////////////////////////////////
    // OverRide Methods
    ///////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_add_poll, container, doNot_)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    ///////////////////////////////////////////////////////////////////////////
    // General Methods
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("SetTextI18n")
    private fun init() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            database = getAppDataBase(requireActivity())

            initActionBar()
            initText()
            initAdapter()

            b.lytAddOption.setOnClickListener {
                if (index < 5) {
                    addOption(index)
                    when (index) {
                        0 -> {
                            b.txtOptionHint.text =
                                getString(R.string.you_can_add) + " 4 " + getString(R.string.more_option)
                        }

                        1 -> {
                            b.txtOptionHint.text =
                                getString(R.string.you_can_add) + " 3 " + getString(R.string.more_option)
                        }

                        2 -> {
                            b.txtOptionHint.text =
                                getString(R.string.you_can_add) + " 2 " + getString(R.string.more_option)
                        }

                        3 -> {
                            b.txtOptionHint.text =
                                getString(R.string.you_can_add) + " 1 " + getString(R.string.more_option)
                        }

                        4 -> {
                            b.txtOptionHint.text = getString(R.string.you_can_not_add_more_option)
                        }
                    }
                    index += 1
                } else {
                    showToast(getString(R.string.you_can_not_add_more_option))
                }
            }
            b.txtCreate.setOnClickListener {
                if (optionList.size >= 2) {
                    addPoll()
                } else {
                    showToast(getString(R.string.you_have_to_add_min_two_option))
                }
            }
        }
    }

    private fun initActionBar() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            b.actionBar.lblTitle.text = getString(R.string.create_poll)
            b.actionBar.imgBack.setOnClickListener { dismiss() }
        }
    }

    private fun initText() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            if (optionList.size == 0) {
                b.edtQuestion.imeOptions = EditorInfo.IME_ACTION_DONE
            } else {
                b.edtQuestion.imeOptions = EditorInfo.IME_ACTION_NEXT
            }
        }
    }

    private fun initAdapter() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            adapter = AddOptionAdapter(requireActivity(), optionList, addPollInterface)
            b.rvOption.adapter = adapter
            initText()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Custom Methods
    ///////////////////////////////////////////////////////////////////////////

    @SuppressLint("NotifyDataSetChanged")
    private fun addOption(index: Int) {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            optionList.add(index, PollOption(""))
            adapter!!.notifyDataSetChanged()
            if (optionList.size == 0) {
                b.edtQuestion.imeOptions = EditorInfo.IME_ACTION_DONE
            } else {
                b.edtQuestion.imeOptions = EditorInfo.IME_ACTION_NEXT
            }
        }
    }

    private fun addPoll() {
        if (activity != null && !requireActivity().isDestroyed && !requireActivity().isFinishing) {
            if (database != null) {
                database!!.createPollDao().addPoll(
                    DbCreatePoll(
                        0,
                        b.edtQuestion.text.trim().toString(),
                        0,
                        Utils.getTime("dd-MMM-yyyy hh:mm:ss a"),
                        Utils.getTime("dd-MMM-yyyy hh:mm:ss a")
                    )
                )
                val id: Int = database!!.createPollDao().getLastPoll()
                for (i in optionList.indices) {
                    database!!.addPollOptionDao().addOption(
                        DbPollOption(
                            0,
                            id,
                            optionList[i].option,
                            doNot_,
                            Utils.getTime("dd-MMM-yyyy hh:mm:ss a"),
                            Utils.getTime("dd-MMM-yyyy hh:mm:ss a")
                        )
                    )
                }
                val event = AddPollEvent()
                event.reload = ""
                EventBus.getDefault().post(event)
                dismiss()
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Callbacks
    ///////////////////////////////////////////////////////////////////////////

    private val addPollInterface: AddPollInterface = object : AddPollInterface {
        override fun onDelete(position: Int) {
            index -= 1
            optionList.removeAt(position)
            adapter!!.notifyItemRemoved(position)
        }

        override fun afterTextChanged(text: String, position: Int) {
            optionList.removeAt(position)
            optionList.add(position, PollOption(text))
        }
    }
}