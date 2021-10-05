package com.example.module12.dialogs

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.module12.R
import com.example.module12.fragments.interfaces.AutoInterface

class Dialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = resources.getString(R.string.add_car)
        val cancel = resources.getString(R.string.cancel)
        val add = resources.getString(R.string.add)

        val view = parentFragment!!.layoutInflater.inflate(R.layout.fragment_dialog, null)
        val viewBrand = view.findViewById<EditText>(R.id.editTextBrandCar)
        val viewModel = view.findViewById<EditText>(R.id.editTextModelCar)
        val viewCheckBox = view.findViewById<CheckBox>(R.id.checkBox)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setView(view)
            .setPositiveButton(add) { _, _ ->
                val brand = viewBrand.text.toString()
                val model = viewModel.text.toString()
                val isElectroCar = viewCheckBox.isChecked
                (parentFragment as AutoInterface).addNewAuto(brand, model, isElectroCar)
            }
            .setNegativeButton(cancel) { _, _ -> }
            .create()

        dialog.setOnShowListener {
            (it as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
        }

        validText(viewBrand, viewModel, dialog)
        validText(viewModel, viewBrand, dialog)

        return dialog
    }

    private fun validText(editText1: EditText, editText2: EditText, dialog: AlertDialog) {
        editText1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                    editText1.text.isNotBlank() && editText2.text.isNotBlank()
            }
        })
    }
}