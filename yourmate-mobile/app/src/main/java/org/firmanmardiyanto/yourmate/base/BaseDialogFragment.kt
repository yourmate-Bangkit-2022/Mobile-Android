package org.firmanmardiyanto.yourmate.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import org.firmanmardiyanto.yourmate.R

open class BaseDialogFragment : DialogFragment() {
    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setBackgroundDrawableResource(R.drawable.bg_dialog)
        }
    }

    fun show(fragmentManager: FragmentManager) {
        if (fragmentManager.findFragmentByTag(this.javaClass.name) == null) {
            super.show(fragmentManager, this.javaClass.name)
        }
    }

    override fun dismiss() {
        if (isAdded) {
            super.dismiss()
        }
    }
}