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
    private val _state = MutableStateFlow<ScoreboardState>(ScoreboardState.Loading)
    val state: StateFlow<ScoreboardState> = _state

    init {
        //Init
        getAllScores()

        //Listens
        listenScores()
        listenNewTop()
    }

    private fun getAllScores() {
        viewModelScope.launch {
            apiRepository.getAllScores().onStart {
                _state.value = ScoreboardState.Loading
            }.catch {
                _state.value = ScoreboardState.Error(it.message ?: "Error")
            }.collect {
                _state.value = ScoreboardState.Success(it)
            }
        }
    }

    fun postScore(player: Player) {
        viewModelScope.launch {
            apiRepository.postScore(player).onStart {
                _state.value = ScoreboardState.Loading
            }.catch {
                _state.value = ScoreboardState.Error(it.message ?: "Error")
            }.collect {
                _state.value = ScoreboardState.Success(it)
            }
        }
    }

    private fun listenScores() {
        viewModelScope.launch {
            apiRepository.players.collect {
                _state.value = ScoreboardState.Success(it)
            }
        }
    }

    private fun listenNewTop() {
        viewModelScope.launch {
            apiRepository.listenNewTopPlayer().collect {
                println("New top player: $it")
            }
        }
    }
}