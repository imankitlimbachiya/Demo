package com.app.demo.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.app.demo.R
import com.app.demo.base.DemoActivity
import com.app.demo.databinding.ActivityOtpBinding
import com.app.demo.utils.Configs.*

class OtpActivity : DemoActivity() {

    private lateinit var m: ActivityOtpBinding
    private lateinit var myEditTexts: Array<EditText?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        m = DataBindingUtil.setContentView(this, R.layout.activity_otp)
        init()
    }

    override fun onResume() {
        Handler(Looper.getMainLooper()).postDelayed({
            val string = "5688"
            val separated = string.split("".toRegex()).toTypedArray()
            m.edtOtpOne.setText(separated[1])
            m.edtOtpTwo.setText(separated[2])
            m.edtOtpThree.setText(separated[3])
            m.edtOtpFour.setText(separated[4])
        }, T_THREE.toLong())
        super.onResume()
    }

    private fun init() {

        m.edtOtpOne.addTextChangedListener(GenericTextWatcher(m.edtOtpOne))
        m.edtOtpTwo.addTextChangedListener(GenericTextWatcher(m.edtOtpTwo))
        m.edtOtpThree.addTextChangedListener(GenericTextWatcher(m.edtOtpThree))
        m.edtOtpFour.addTextChangedListener(GenericTextWatcher(m.edtOtpFour))

        myEditTexts = arrayOf(m.edtOtpOne, m.edtOtpTwo, m.edtOtpThree, m.edtOtpFour)

        m.edtOtpOne.addTextChangedListener(PinTextWatcher(ZERO))
        m.edtOtpTwo.addTextChangedListener(PinTextWatcher(ONE))
        m.edtOtpThree.addTextChangedListener(PinTextWatcher(TWO))
        m.edtOtpFour.addTextChangedListener(PinTextWatcher(THREE))

        m.edtOtpOne.setOnKeyListener(PinOnKeyListener(ZERO))
        m.edtOtpTwo.setOnKeyListener(PinOnKeyListener(ONE))
        m.edtOtpThree.setOnKeyListener(PinOnKeyListener(TWO))
        m.edtOtpFour.setOnKeyListener(PinOnKeyListener(THREE))
    }

    inner class GenericTextWatcher(private val view: View?) : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view!!.id) {
                R.id.edtOtpOne -> if (text.length == ONE) {
                    m.edtOtpTwo.requestFocus()
                    setRequestFocus(m.edtOtpTwo)
                    removeRequestFocus(m.edtOtpOne)
                }/* else {
                    hideSoftKeyboard(m.edtOtpOne)
                    removeRequestFocus(m.edtOtpOne)
                }*/
                R.id.edtOtpTwo -> if (text.length == ONE) {
                    m.edtOtpThree.requestFocus()
                    setRequestFocus(m.edtOtpThree)
                    removeRequestFocus(m.edtOtpTwo)
                } else if (text.isEmpty()) {
                    m.edtOtpOne.requestFocus()
                    setRequestFocus(m.edtOtpOne)
                    removeRequestFocus(m.edtOtpTwo)
                }
                R.id.edtOtpThree -> if (text.length == ONE) {
                    m.edtOtpFour.requestFocus()
                    setRequestFocus(m.edtOtpFour)
                    removeRequestFocus(m.edtOtpThree)
                } else if (text.isEmpty()) {
                    m.edtOtpTwo.requestFocus()
                    setRequestFocus(m.edtOtpTwo)
                    removeRequestFocus(m.edtOtpThree)
                }
                R.id.edtOtpFour -> if (text.isEmpty()) {
                    m.edtOtpThree.requestFocus()
                    removeRequestFocus(m.edtOtpFour)
                    setRequestFocus(m.edtOtpThree)
                } else {
                    hideSoftKeyboard(m.edtOtpFour)
                    removeRequestFocus(m.edtOtpFour)
                }
            }
        }

        override fun beforeTextChanged(
            argZero: CharSequence, argOne: Int, argTwo: Int, argThree: Int
        ) {
            // TODO Auto-generated method stub
        }

        override fun onTextChanged(
            argZero: CharSequence, argOne: Int, argTwo: Int, argThree: Int
        ) {
            // TODO Auto-generated method stub
        }
    }

    private fun setRequestFocus(editText: EditText) {
        editText.isFocusableInTouchMode = _do
        editText.isFocusable = _do
        editText.requestFocus()
    }

    private fun removeRequestFocus(editText: EditText) {
        editText.isFocusableInTouchMode = _do
        editText.isFocusable = _do
        editText.requestFocus()
    }

    private fun hideSoftKeyboard(editText: EditText) {
        val inputMethodManager =
            editText.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, ZERO)
        editText.isFocusableInTouchMode = _do
        editText.isFocusable = _do
        editText.requestFocus()
    }

    inner class PinTextWatcher internal constructor(
        private val currentIndex: Int
    ) : TextWatcher {

        private var isFirst = _doNot
        private var isLast = _doNot
        private var newTypedString = NULL

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            newTypedString = s.subSequence(start, start + count).toString().trim { it <= ' ' }
        }

        override fun afterTextChanged(s: Editable) {
            var text = newTypedString
            /* Detect paste event and set first char */
            if (text.length > ONE) text =
                text[ZERO].toString() // TODO: We can fill out other EditTexts
            myEditTexts[currentIndex]!!.removeTextChangedListener(this)
            myEditTexts[currentIndex]!!.setText(text)
            myEditTexts[currentIndex]!!.setSelection(text.length)
            myEditTexts[currentIndex]!!.addTextChangedListener(this)
            if (text.length == ONE) moveToNext() else if (text.isEmpty()) moveToPrevious()
            if (isAllEditTextsFilled) {
                showToast(this@OtpActivity, "Otp Successfully Loaded")
            }
        }

        private fun moveToNext() {
            if (!isLast) myEditTexts[currentIndex + ONE]!!.requestFocus()
            if (isAllEditTextsFilled/* && isLast*/) { // isLast is optional
                myEditTexts[currentIndex]!!.clearFocus()
                hideKeyboard()
                if (!isLast) myEditTexts[currentIndex + ONE]!!.clearFocus()
            }
        }

        private fun moveToPrevious() {
            // if (!isFirst) myEditTexts[currentIndex - ONE]!!.requestFocus()
            if (!isFirst) {
                if (myEditTexts[currentIndex]!!.text.length == ONE) {
                    myEditTexts[currentIndex]!!.text.clear()
                } else {
                    myEditTexts[currentIndex]!!.requestFocus()
                }
            }
        }

        private val isAllEditTextsFilled: Boolean
            get() {
                for (editText in myEditTexts) if (
                    editText!!.text.toString().trim { it <= ' ' }.isEmpty()
                ) return _doNot
                return _do
            }

        private fun hideKeyboard() {
            if (currentFocus != null) {
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, ZERO)
            }
        }

        init {
            if (currentIndex == ZERO) isFirst =
                _do else if (currentIndex == myEditTexts.size - ONE) isLast = _do
        }
    }

    inner class PinOnKeyListener internal constructor(
        private val currentIndex: Int
    ) : View.OnKeyListener {
        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (myEditTexts[currentIndex]
                        ?.text.toString().isEmpty() && currentIndex != ZERO
                ) myEditTexts[currentIndex - ONE]!!.requestFocus()
            }
            return _doNot
        }
    }
}