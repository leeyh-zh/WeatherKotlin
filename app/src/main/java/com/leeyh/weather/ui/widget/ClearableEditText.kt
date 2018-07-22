package com.leeyh.weather.ui.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.EditText
import com.leeyh.weather.R
import com.leeyh.weather.util.StringUtils

class ClearableEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : EditText(context, attrs, defStyleAttr) {
    val POSITION_START = 0
    val POSITION_END = 2
    private val DEFAULT_CLEAR_BUTTON_PADDING = 5.0f
    private val DEFAULT_CLEAR_BUTTON_SIZE = 16.0f
    private val DEFAULT_CLEAR_BUTTON_POSITION = 2
    private var mClearButtonAlwaysVisible = false

    private var mClearButtonPosition = 0
    private var mClearButtonHeight = 0
    private var mClearButtonPadding = 0
    private var mClearButtonWidth = 0
    private var mClearButtonDrawable: Drawable? = null
    private var mGestureDetector: GestureDetector? = null
    private var mOnContentClearListener: OnContentClearListener? = null

    interface OnContentClearListener {
        fun onContentClear(clearableEditText: ClearableEditText)
    }

    init {
        val density = context.resources.displayMetrics.density
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ClearableEditText, defStyleAttr, R.style.ClearableEditText)
        mClearButtonPosition = typedArray.getInt(R.styleable.ClearableEditText_clearButtonPosition, DEFAULT_CLEAR_BUTTON_POSITION)
        if (!(mClearButtonPosition == POSITION_START || mClearButtonPosition == POSITION_END)) {
            mClearButtonPosition = DEFAULT_CLEAR_BUTTON_POSITION
        }
        mClearButtonAlwaysVisible = typedArray.getBoolean(R.styleable.ClearableEditText_clearButtonAlwaysVisible, false)
        mClearButtonPadding = typedArray.getDimensionPixelSize(R.styleable.ClearableEditText_clearButtonPadding, (DEFAULT_CLEAR_BUTTON_PADDING * density).toInt())
        mClearButtonDrawable = typedArray.getDrawable(R.styleable.ClearableEditText_clearButtonDrawable)
        if (mClearButtonDrawable == null) {
            mClearButtonDrawable = resources.getDrawable(R.drawable.ic_home)
        }
        mClearButtonWidth = typedArray.getDimensionPixelSize(R.styleable.ClearableEditText_clearButtonWidth, -1)
        mClearButtonHeight = typedArray.getDimensionPixelSize(R.styleable.ClearableEditText_clearButtonHeight, -1)
        if (mClearButtonWidth == -1 || mClearButtonHeight == -1) {
            val i = (DEFAULT_CLEAR_BUTTON_SIZE * density).toInt()
            mClearButtonHeight = i
            mClearButtonWidth = i
        }
        mClearButtonDrawable!!.setBounds(POSITION_START, POSITION_START, mClearButtonWidth, mClearButtonHeight)
        typedArray.recycle()
        mGestureDetector = GestureDetector(context, ClearButtonGestureListener())
        addTextChangedListener(ClearableTextWatcher())
        setClearButtonVisible(false)
    }

    inner class ClearButtonGestureListener () : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            if (!(compoundDrawables[0] == null && compoundDrawables[2] == null)) {
                val left: Int
                val rectWidth = mClearButtonDrawable!!.bounds.width()
                val rectHeight = mClearButtonDrawable!!.bounds.height()
                if (mClearButtonPosition == 0) {
                    left = paddingLeft
                } else {
                    left = width - paddingRight - rectWidth
                }
                val top = (height + paddingTop - paddingBottom - rectHeight) / 2
                val right = left + rectWidth
                val bottom = top + rectHeight
                if (e.x > left.toFloat() && e.x < right.toFloat() && e.y > top.toFloat() && e.y < bottom.toFloat()) {
                    clearContent()
                }
            }
            return false
        }
    }

    private fun setClearButtonVisible(visible: Boolean?) {
        if (visible!! || mClearButtonAlwaysVisible) {
            compoundDrawablePadding = mClearButtonPadding
            setClearButton(mClearButtonPosition, mClearButtonDrawable)
            return
        }
        setClearButton(mClearButtonPosition, null)
    }

    private fun setClearButton(position: Int, drawable: Drawable?) {
        if (position == 0) {
            setCompoundDrawables(drawable, null, null, null)
        } else if (position == 2) {
            setCompoundDrawables(null, null, drawable, null)
        }
    }

    private fun clearContent() {
        setText(StringUtils.EMPTY_STRING)
        setClearButtonVisible(false)
        mOnContentClearListener!!.onContentClear(this)
    }

    private inner class ClearableTextWatcher () : TextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s == null || s.length == 0) {
                setClearButtonVisible(false)
            } else if (isFocused) {
                setClearButtonVisible(true)
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val superResult = super.onTouchEvent(event)
        if (compoundDrawables[if (mClearButtonPosition == 0) POSITION_START else POSITION_END] != null) {
            mGestureDetector!!.onTouchEvent(event)
        }
        return superResult
    }

    fun getClearButtonDrawable(): Drawable? {
        return mClearButtonDrawable
    }

    fun setClearButtonDrawable(drawable: Drawable?) {
        if (drawable == null) {
            throw NullPointerException("Drawable can not be null.")
        }
        mClearButtonDrawable = drawable
        if (compoundDrawables[if (mClearButtonPosition == 0) POSITION_START else POSITION_END] != null) {
            setClearButtonVisible(java.lang.Boolean.valueOf(true))
        }
    }

    fun setClearButtonAlwaysVisible(visible: Boolean) {
        mClearButtonAlwaysVisible = visible
        setClearButtonVisible(true)
    }

    fun setOnContentClearListener(l: OnContentClearListener) {
        mOnContentClearListener = l
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (!focused || text.toString().length == 0) {
            setClearButtonVisible(false)
        } else {
            setClearButtonVisible(true)
        }
    }

    fun setClearButtonPosition(position: Int) {
        if (position == 2 || position == 0) {
            setClearButton(position, getClearButtonDrawable())
            mClearButtonPosition = position
            return
        }
        throw IllegalArgumentException("Position can only be one of: POSITION_START or POSITION_END.")
    }
}