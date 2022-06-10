package org.firmanmardiyanto.yourmate.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.firmanmardiyanto.yourmate.R

class LoadingDialog private constructor() : BaseDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_loading_dialog, container, false)
    }

    companion object {
        fun create(): LoadingDialog {
            return LoadingDialog().apply { isCancelable = false }
        }
    }
}