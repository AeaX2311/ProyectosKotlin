package com.aeax.curpproject.ui.register.traditionalregister

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.aeax.curpproject.data.RESERVED_WORDS
import com.aeax.curpproject.ui.register.wizardregister.models.FormStatus
import com.aeax.curpproject.models.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.timerTask

class TraditionalRegisterViewModel : ViewModel() {
    private val regexUnaccented = "\\p{InCombiningDiacriticalMarks}+".toRegex()

    private val _uiPersonState = MutableStateFlow(Person())
    val uiPersonState: StateFlow<Person> = _uiPersonState

    private val _uiFormStatusState = MutableStateFlow<FormStatus>(FormStatus.Init)
    val uiFormStatusState: StateFlow<FormStatus> = _uiFormStatusState

    init {
        initState()
    }

    private fun initState() {
        _uiFormStatusState.value = FormStatus.Loading
        _uiPersonState.value = Person(
            name = "ALAN ABIUD",
            lastNameP = "CASTRO",
            lastNameM = "CRUZ",
            birthDate = "2001-11-23",
            gender = "H",
            state = "TS",
            curp = ""
        )
        Timer().schedule(timerTask { _uiFormStatusState.value = FormStatus.Loaded }, 1200)
    }

    fun setName(name: String) {
        _uiPersonState.value = _uiPersonState.value.copy(name = name)
        validateAll()
    }

    fun setLastNameP(lastNameP: String) {
        _uiPersonState.value = _uiPersonState.value.copy(lastNameP = lastNameP)
        validateAll()
    }

    fun setLastNameM(lastNameM: String) {
        _uiPersonState.value = _uiPersonState.value.copy(lastNameM = lastNameM)
        validateAll()
    }

    fun setBirthDate(birthDate: String) {
        _uiPersonState.value = _uiPersonState.value.copy(birthDate = birthDate)
        validateAll()
    }

    fun setGender(gender: String) {
        _uiPersonState.value = _uiPersonState.value.copy(gender = gender)
        validateAll()
    }

    fun setState(state: String) {
        _uiPersonState.value = _uiPersonState.value.copy(state = state)
        validateAll()
    }

    private fun validateAll() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateCurp() {
        try {
            val myPerson = uiPersonState.value
            var curp = myPerson.lastNameP.substring(0, 1) //Primera letra del apellido paterno
            curp += findVocal(myPerson.lastNameP) //Primera vocal del apellido paterno
            curp += myPerson.lastNameM.substring(0, 1) //Primera letra del apellido materno
            curp += myPerson.name.substring(0, 1) //Primera letra del nombre

            curp = curp.unaccent() //Eliminar acentos
            curp = deleteInconvenientWords(curp) //Eliminar palabras no permitidas

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(myPerson.birthDate, formatter)
            curp += date.format(DateTimeFormatter.ofPattern("yy")) //Año de nacimiento
            curp += date.format(DateTimeFormatter.ofPattern("MM")) //Mes de nacimiento
            curp += date.format(DateTimeFormatter.ofPattern("dd")) //Dia de nacimiento

            curp += myPerson.gender //Genero a un solo digito

            curp += myPerson.state //Estado a dos digitos

            curp += findInternalConsonant(myPerson.lastNameP) //Primera consonante interna del apellido paterno
            curp += findInternalConsonant(myPerson.lastNameM) //Primera consonante interna del apellido materno
            curp += findInternalConsonant(myPerson.name) //Primera consonante interna del nombre

            curp += if (date.year < 2000) "1" else "A"//Agrega un 1 si es menor de 2000, A si es mayor (Homoclave)

            curp = curp.uppercase() //Pasar a mayusculas

            curp += generateVerifyDigit(curp) //Digito verificador

            curp = curp.replace("Ñ", "X") //Reemplaza las Ñ por X

            _uiPersonState.value = _uiPersonState.value.copy(curp = curp)

            _uiFormStatusState.value = FormStatus.Success(message = _uiPersonState.value.curp, isError = false)
        } catch (e: Exception) {
            _uiPersonState.value = _uiPersonState.value.copy(curp = "")
            _uiFormStatusState.value = FormStatus.Error(message = "Error al generar el CURP", isError = true)
        }
    }

    private fun findVocal(item: String): String {
        for (i in item.indices) {
            if (item[i] == 'A' || item[i] == 'E' || item[i] == 'I' || item[i] == 'O' || item[i] == 'U') {
                return item[i].toString()
            }
        }
        return "X" //No deberia llegar jamas
    }

    private fun findInternalConsonant(item: String): String {
        for (i in item.indices) {
            if (i == 0 || i == item.length - 1) continue //No se toma en cuenta la primera y ultima letra
            if (item[i] != 'A' && item[i] != 'E' && item[i] != 'I' && item[i] != 'O' && item[i] != 'U') {
                return item[i].toString()
            }
        }
        return "X" //No deberia llegar jamas
    }

    private fun generateVerifyDigit(curp: String): String {
        val letters = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        var sum = 0
        var digit: Int

        for (i in curp.indices) {
            digit = letters.indexOf(curp[i])
            sum += digit * (18 - i)
        }

        val mod = sum % 10
        return if (mod == 0) "0" else (10 - mod).toString()
    }

    private fun deleteInconvenientWords(curp: String) :String {
        return if(RESERVED_WORDS.contains(curp.uppercase())) { curp[0] + "X" + curp.substring(2, curp.length) } else curp
    }

    private fun CharSequence.unaccent(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return regexUnaccented.replace(temp, "")
    }
}