package com.aeax.smndice.ui.screens.scoreboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aeax.smndice.domain.models.game.Player
import com.aeax.smndice.domain.repositories.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    private val _scoreList = MutableStateFlow<List<Player>>(emptyList())
    val scoreList: StateFlow<List<Player>> = _scoreList.asStateFlow()

    init {
        getAllScores()
    }

    private fun getAllScores() {
        viewModelScope.launch {
            apiRepository.getAllScores().collect {
                _scoreList.value = it
            }
        }
    }

    fun postScore(player: Player) {
        viewModelScope.launch {
            apiRepository.postScore(player).collect {
                //Agregar el nuevo valor (score respuesta) en todos mis scores
                val newList = _scoreList.value.toMutableList()
                newList.add(Player(it.dataNative.id, player.name, player.score, player.level))
                _scoreList.value = newList
            }

//            getAllScores()
        }
    }
}