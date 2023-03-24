package com.aeax.curpproject.models

sealed class Routes(val path: String) {
    object TraditionalRegister : Routes("traditionalRegister")
    object Info : Routes("info")
    object SelectorMode : Routes("selectorMode")
    object WizardRegister : Routes("wizardRegister")
}