package com.aeax.curpproject.ui.register.wizardregister.models

sealed class FormStatus {
    object Init: FormStatus()
    object Loading : FormStatus()
    object Loaded : FormStatus()
    object NameScreen : FormStatus()
    object DateScreen : FormStatus()
    object GenderScreen : FormStatus()
    object StateScreen : FormStatus()
    class Success(val message: String, val isError: Boolean) : FormStatus()
    class Error(val message: String, val isError: Boolean) : FormStatus()
}