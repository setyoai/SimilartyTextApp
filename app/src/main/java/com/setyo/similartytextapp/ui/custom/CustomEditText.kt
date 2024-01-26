package com.setyo.similartytextapp.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import com.setyo.similartytextapp.R

class CustomEditText: AppCompatEditText {

    private var isError: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textAlignment = TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val input = s.toString()
                when (inputType) {
                    EMAIL -> {
                        isError = if (input.length < 9) {
                            setError(context.getString(R.string.email_error), null)
                            true
                        } else {
                            false
                        }
                    }
                    PASSWORD -> {
                        isError = if (input.length < 5) {
                            setError(context.getString(R.string.password_error), null)
                            true
                        } else {
                            false
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {
                val input = s.toString()
                when (inputType) {
                    EMAIL -> {
                        isError = if (input.length < 9) {
                            setError(context.getString(R.string.email_error), null)
                            true
                        } else {
                            false
                        }
                    }
                    PASSWORD -> {
                        isError = if (input.length < 5) {
                            setError(context.getString(R.string.password_error), null)
                            true
                        } else {
                            false
                        }
                    }
                }
            }
        })
    }

    companion object {
        const val EMAIL = 0x00000021
        const val PASSWORD = 0x00000081
    }
}