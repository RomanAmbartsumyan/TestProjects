package com.example.unsplash.fragments.enter_fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentEnterBinding
import com.example.unsplash.utils.onBackPressedExit
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse

class EnterFragment : Fragment(R.layout.fragment_enter) {
    private val binding: FragmentEnterBinding by viewBinding()

    private val viewModel: EnterFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedExit()
        binding.buttonLogin.setOnClickListener {
            viewModel.openLoginPage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTH_REQUEST_CODE && data != null) {
            val tokenExchangeRequest = AuthorizationResponse.fromIntent(data)
                ?.createTokenExchangeRequest()
            val exception = AuthorizationException.fromIntent(data)
            when {
                tokenExchangeRequest != null && exception == null ->
                    viewModel.onAuthCodeReceived(tokenExchangeRequest)
                exception != null -> viewModel.onAuthCodeFailed(exception)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val AUTH_REQUEST_CODE = 342
    }
}