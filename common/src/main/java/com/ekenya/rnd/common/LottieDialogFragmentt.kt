package com.ekenya.rnd.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable


class LottieDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(): LottieDialogFragment {
            return LottieDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_lottie, container, false)
    }

    fun setProgressBarVisible(visible: Boolean) {
        if (visible) {
            dialog?.window?.setDimAmount(0.5f)
            dialog?.show()
        } else {
            dialog?.window?.setDimAmount(0f)
            dialog?.dismiss()
        }
    }
}
