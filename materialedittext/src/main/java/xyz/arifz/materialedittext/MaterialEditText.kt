package xyz.arifz.materialedittext

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MaterialEditText : TextInputLayout {

    init {
        setTheme()
    }

    private lateinit var textInputEditText: TextInputEditText

    constructor(context: Context) : super(context) {
        init(context, null, R.style.TextInputLayoutStyle)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs,
        R.style.TextInputLayoutStyle
    ) {
        init(context, attrs, R.style.TextInputLayoutStyle)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context, attrs, R.style.TextInputLayoutStyle)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        setupView(context)
        setupAttributes(context, attrs)
    }

    private fun setTheme() {
        boxBackgroundColor = ContextCompat.getColor(context, R.color.color_white)
        boxBackgroundMode = BOX_BACKGROUND_OUTLINE
        boxStrokeWidth = 1
        boxStrokeWidthFocused = 1
        boxStrokeColor = ContextCompat.getColor(context, R.color.color_blue_crayola)
        setHintTextAppearance(R.style.TextInputLayoutHintTextStyle)
//        setBoxCornerRadii(5f,5f,5f,5f)
    }

    private fun setupView(context: Context) {
        textInputEditText = TextInputEditText(context)

        textInputEditText.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        textInputEditText.background = null
        textInputEditText.setLines(1)
        textInputEditText.maxLines = 1
        textInputEditText.isSingleLine = true
        textInputEditText.imeOptions = EditorInfo.IME_ACTION_DONE
        addView(textInputEditText)
        textInputEditText.setPadding(20, 20, 20, 20)
    }

    private fun setupAttributes(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
            try {
                var hint = a.getString(R.styleable.MaterialEditText_hint)
                val isRequired = a.getBoolean(R.styleable.MaterialEditText_isRequired, false)
                if (isRequired) {
                    if (hint.isNullOrEmpty())
                        hint = ""
                    hint += " *"
                    this.hint = hint
                } else this.hint = hint

                val isReadOnly = a.getBoolean(R.styleable.MaterialEditText_isReadOnly, false)
                setReadOnly(isReadOnly)

                val radius = a.getFloat(R.styleable.MaterialEditText_radius, 5f)

                setCustomPadding(radius)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            a.recycle()
        }
    }

    private fun setCustomPadding(radius: Float) {
        setBoxCornerRadii(radius, radius, radius, radius)
    }

    private fun setReadOnly(state: Boolean) {
        if (state) {
            this.isClickable = false
            this.isFocusable = false
            this.isFocusableInTouchMode = false
            this.textInputEditText.isCursorVisible = false
            this.textInputEditText.isClickable = false
            this.textInputEditText.isFocusable = false
            this.textInputEditText.isFocusableInTouchMode = false
            setReadOnlyColor()
        }
    }

    private fun setReadOnlyColor() {
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled, android.R.attr.state_enabled)
            ),
            intArrayOf(
                ContextCompat.getColor(context, R.color.color_light_grey),
                ContextCompat.getColor(context, R.color.color_light_grey)
            )
        )
        setBoxStrokeColorStateList(colorStateList)
        boxBackgroundColor = ContextCompat.getColor(context, R.color.color_white)
        editText?.setTextColor(ContextCompat.getColor(context, R.color.color_light_grey))
    }

    var text: Editable?
        set(value) {
            textInputEditText.text = value
        }
        get() {
            return textInputEditText.text
        }

}