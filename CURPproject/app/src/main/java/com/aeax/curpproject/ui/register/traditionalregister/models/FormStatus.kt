package com.aeax.curpproject.ui.register.traditionalregister.models

sealed class FormStatus {
    object Init: FormStatus()
    object Loading : FormStatus()
    object Loaded : FormStatus()
    class Success(val message: String, val isError: Boolean) : FormStatus()
    class Error(val message: String, val isError: Boolean) : FormStatus()
}