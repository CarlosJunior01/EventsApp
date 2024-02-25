package com.carlosjr.eventsapp.presentation.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RawRes
import com.carlosjr.eventsapp.R
import com.carlosjr.eventsapp.databinding.CustomDialogViewBinding
import com.carlosjr.eventsapp.helper.extensions.setVisible

class CustomDialog @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = CustomDialogViewBinding
        .inflate(LayoutInflater.from(context), this, true)

    fun setDialogTitle(dialogTitle: String?) = run { binding.customDialogTitle.text = dialogTitle }

    fun setDialogBtnConfirm(btnConfirmName: String) = run { binding.btnConfirm.text = btnConfirmName }

    fun setDialogBtnCancel(btnCancelName: String) = run { binding.btnCancel.text = btnCancelName }
    fun getInputName(): String {
        return binding.editTextName.text.toString()
    }

    fun setAnimationDialog(@RawRes animation: Int = R.raw.loading_success, isShow: Boolean) {
        binding.animationDialog.setAnimation(animation)
        binding.animationDialog.setVisible(isShow)
        binding.btnCloseDialog.setVisible(isShow)
    }

    fun getInputEmail(): String {
        return binding.editTextEmail.text.toString()
    }

    fun setupListeners(
        onBtnConfirmClick: (View) -> Unit,
        onBtnCancelClick: (View) -> Unit,
        onBtnCloseClick: (View) -> Unit,
    ) {
        binding.btnConfirm.setOnClickListener(onBtnConfirmClick)
        binding.btnCancel.setOnClickListener(onBtnCancelClick)
        binding.btnCloseDialog.setOnClickListener(onBtnCloseClick)
    }
}