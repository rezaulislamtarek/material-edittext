package xyz.arifz.materialedittext

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MaterialEditText : ConstraintLayout {

    private lateinit var textInputLayout: TextInputLayout
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
        R.style.TextInputLayoutStyle,
        defStyle
    ) {
        init(context, attrs, R.style.TextInputLayoutStyle)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyle: Int) {
        setupView(context)
        setupAttributes(context, attrs)
    }

    private fun setupView(context: Context) {
        textInputLayout = TextInputLayout(context)
        textInputEditText = TextInputEditText(context)

        val set = ConstraintSet()
        textInputLayout.id = View.generateViewId()
        textInputLayout.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        addView(textInputLayout)
        set.clone(this)
        set.connect(textInputLayout.id, ConstraintSet.START, this.id, ConstraintSet.START, 20)
        set.connect(textInputLayout.id, ConstraintSet.END, this.id, ConstraintSet.END, 20)
        set.applyTo(this)

        textInputEditText.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        textInputEditText.background = null
        textInputEditText.setLines(1)
        textInputEditText.maxLines = 1
        textInputEditText.isSingleLine = true
        textInputLayout.addView(textInputEditText)
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
                    textInputLayout.hint = hint
                } else textInputLayout.hint = hint
            } catch (e: Exception) {
                e.printStackTrace()
            }
            a.recycle()
        }
    }


}