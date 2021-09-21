package com.example.unsplash.fragments.enter_fragment

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.unsplash.R
import com.example.unsplash.auth.AuthRepository
import com.example.unsplash.utils.SingleLiveEvent
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest

class EnterFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository = AuthRepository()
    private val authService: AuthorizationService = AuthorizationService(getApplication())
    private val openAuthPageLiveEvent = SingleLiveEvent<Intent>()
    private val toastLiveEvent = SingleLiveEvent<Int>()
    private val loadingMutableLiveData = MutableLiveData(false)
    private val authSuccessLiveEvent = SingleLiveEvent<Unit>()

    val openAuthPageLiveData: LiveData<Intent>
        get() = openAuthPageLiveEvent

    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    val toastLiveData: LiveData<Int>
        get() = toastLiveEvent

    val authSuccessLiveData: LiveData<Unit>
        get() = authSuccessLiveEvent


    fun onAuthCodeReceived(tokenRequest: TokenRequest) {
        loadingMutableLiveData.postValue(true)
        authRepository.performTokenRequest(
            authService = authService,
            tokenRequest = tokenRequest,
            onComplete = {
                loadingMutableLiveData.postValue(false)
                authSuccessLiveEvent.postValue(Unit)
            },
            onError = {
                loadingMutableLiveData.postValue(false)
            }
        )
    }

    fun onAuthCodeFailed(exception: AuthorizationException) {
        toastLiveEvent.postValue(R.string.app_name)
    }


    fun openLoginPage() {

        val openAuthPageIntent = authService.getAuthorizationRequestIntent(
            authRepository.getAuthRequest()
        )

        openAuthPageLiveEvent.postValue(openAuthPageIntent)
    }
}