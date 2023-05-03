package com.aeax.smndice.domain.repositories

import com.aeax.smndice.domain.models.api.post.DataPost
import com.aeax.smndice.domain.models.api.post.PostRequest
import com.aeax.smndice.domain.models.api.post.PostResponse
import com.aeax.smndice.domain.models.game.Player
import com.aeax.smndice.domain.services.interfaces.IApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiManager: IApiManager
) {
    fun getAllScores(): Flow<List<Player>> = flow {
        val result = mutableListOf<Player>()
        val response = apiManager.getAllScores()

        response.dataNative.map {
            result.add(Player(null,it.attributes?.name ?: "?????",it.attributes?.value ?: 0,1))
        }

        emit(result)
    }.flowOn(Dispatchers.IO)

    fun postScore(player: Player): Flow<PostResponse> = flow {
        emit(apiManager.postScore(PostRequest(dataPost = DataPost(player.level, player.name, player.score))))
    }.flowOn(Dispatchers.IO)
}