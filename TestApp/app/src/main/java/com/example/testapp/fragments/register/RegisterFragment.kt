package com.example.testapp.fragments.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentRegisterBinding
import com.example.testapp.objects.Register
import com.example.testapp.store.DataStoreKeys
import com.example.testapp.utils.getResultFromDataStore
import com.example.testapp.utils.saveKeyToDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val binding: FragmentRegisterBinding by viewBinding()
    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    private fun start() {
        lifecycleScope.launch {
            val isLogin = getResultFromDataStore(booleanPreferencesKey(DataStoreKeys.IS_LOGIN))
            if (isLogin == true) {
                val phone = getResultFromDataStore(stringPreferencesKey(DataStoreKeys.PHONE))!!
                val password = getResultFromDataStore(
                    stringPreferencesKey(DataStoreKeys.PASSWORD)
                )
                with(binding) {
                    val code = phone.take(2)
                    val actualPhone = when (code) {
                        "+7" -> phone.replace("\\D".toRegex(), "")
                            .replaceRange(0, 1, "")

                        "+4" -> phone.replace("\\D".toRegex(), "")
                            .replaceRange(0, 2, "")

                        else -> phone.replace("\\D".toRegex(), "")
                            .replaceRange(0, 3, "")
                    }
                    phoneInput.mask = getPhone(code)
                    phoneInput.setText(actualPhone)
                    passwordInput.setText(password)
                }
            } else {
                val phone = viewModel.getPhone()
                binding.phoneInput.mask = getPhone(phone.take(2))
            }
            with(binding) {
                buttonSingIn.setOnClickListener {
                    val phoneText = phoneInput.text.toString()
                    val password = passwordInput.text.toString()
                    regist(phoneText, password)
                }
            }
        }
    }

    private fun regist(phone: String, password: String) {
        lifecycleScope.launch {
            val newPhone = phone.replace("\\D".toRegex(), "")
            val isLogin = viewModel.auth(Register(newPhone, password))
            if (isLogin) {
                saveKeyToDataStore(stringPreferencesKey(DataStoreKeys.PHONE), phone)
                saveKeyToDataStore(stringPreferencesKey(DataStoreKeys.PASSWORD), password)
                saveKeyToDataStore(booleanPreferencesKey(DataStoreKeys.IS_LOGIN), true)
                val action =
                    RegisterFragmentDirections.actionRegisterFragmentToMainFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.invalid_parameters),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getPhone(phone: String): String {
        return when (phone) {
            "+7" -> "+7 XXX XXX-XX-XX"
            "+4" -> "+44 XXXX-XXXXXX"
            else -> "+375 XX-XXX-XXXX"
        }
    }
}