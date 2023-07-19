package com.example.main.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.main.R

class ConfirmExitDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        // Inflate the custom dialog layout
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_exit, null)

        // Get references to the custom TextViews
        val leaveTextView = dialogView.findViewById<TextView>(R.id.leave_tv)
        val cancelTextView = dialogView.findViewById<TextView>(R.id.cancel_tv)


        // Apply corner radius programmatically
        val cornerRadius = resources.getDimensionPixelSize(R.dimen.dialog_background).toFloat()
        view?.background = getRoundedBackground(cornerRadius)

        // Set the custom view for the dialog
        builder.setView(dialogView)

        val alertDialog = builder.create()

        leaveTextView.setOnClickListener {

            activity?.finishAffinity() // Finish all activities in the task
            System.exit(0) // Completely exit the application

//            viewModel.logout() // Call the logout function to clear user data
//            findNavController().navigate(R.id.loginFragment) // Navigate to the login fragment
            alertDialog.dismiss() // Dismiss the dialog after handling the click
        }

        cancelTextView.setOnClickListener {
            alertDialog.dismiss()
        }

        return alertDialog
    }


    private fun getRoundedBackground(cornerRadius: Float): Drawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(Color.WHITE)
        shape.cornerRadius = cornerRadius
        return shape
    }
}





