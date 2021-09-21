package com.example.unsplash.auth

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://unsplash.com/oauth/authorize"
    const val TOKEN_URI = "https://unsplash.com/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"


    const val CLIENT_ID = "oh99zPwrTd0lyN5lakZQwrsAW0-ydiZ_SX0merBTRD4"
    const val CLIENT_SECRET = "GwbRGA3SSq3bzGMZ4V4U8Bo2mckLiq3zl_pIEHAsHI0"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}