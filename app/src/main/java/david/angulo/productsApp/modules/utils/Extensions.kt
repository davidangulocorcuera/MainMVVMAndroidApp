package david.angulo.productsApp.modules.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build


import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import java.math.BigInteger
import java.security.MessageDigest


class Extensions {
}

fun String.toMD5Hash(): String {
    val md = MessageDigest.getInstance("SHA-256")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun View.setVisible(show: Boolean, invisible: Boolean = false) {
    when {
        show -> this.visibility = View.VISIBLE
        invisible -> this.visibility = View.INVISIBLE
        else -> this.visibility = View.GONE
    }
}

fun View.isVisible(): Boolean {
    when {
        this.visibility == View.VISIBLE -> return true
        this.visibility == View.INVISIBLE -> return false
        this.visibility == View.GONE -> return false
    }
    return false
}

fun ImageView.tint(@ColorRes id: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN) {
    this.setColorFilter(ContextCompat.getColor(context, id), mode)
}


fun Context.drawable(@DrawableRes id: Int?): Drawable? {
    id?.let {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) resources.getDrawable(id, null)
        else resources.getDrawable(id)
    }
    return null
}

fun TextInputLayout.showError(hasError: Boolean) {
    this.isErrorEnabled = hasError
}






