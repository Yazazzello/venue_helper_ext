package nl.mk.venuehelper.shared

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import nl.mk.venuehelper.R

@BindingAdapter("imageUrl")
fun AppCompatImageView.bindPhotos(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .centerCrop()
        .into(this)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}