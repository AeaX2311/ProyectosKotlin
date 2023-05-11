package com.aeax.smndice.domain.repositories

import com.aeax.smndice.domain.models.api.post.DataPost
import com.aeax.smndice.domain.models.api.common.SocketHandler
import com.aeax.smndice.domain.models.api.post.PostRequest
import com.aeax.smndice.domain.models.api.post.PostResponse
import com.aeax.smndice.domain.models.game.Player
import com.aeax.smndice.domain.services.interfaces.IApiManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiManager: IApiManager,
    private val moshi: Moshi
) {
    private val _players: MutableStateFlow<List<Player>> = MutableStateFlow(listOf())
    val players: StateFlow<List<Player>> = _players.asStateFlow()

    init {
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }

    fun getAllScores(): Flow<List<Player>> = flow {
        val result = mutableListOf<Player>()
        val response = apiManager.getAllScores()

        response.dataNative.map {
            result.add(Player(null,it.attributes?.name ?: "?????",it.attributes?.value ?: 0,1))
        }

        emit(result)
    }.flowOn(Dispatchers.IO)

    fun postScore(player: Player): Flow<List<Player>> = flow {
        val response = apiManager.postScore(PostRequest(dataPost = DataPost(player.level, player.name, player.score)))
        val myPlayer = Player(
            id= response.dataNative.id,
            name = player.name,
            score = player.score,
            level = player.level,
        )

        val socket = SocketHandler.getSocket()
        socket.emit("newTop",playerToJson(myPlayer))
        emit(_players.value)

//        emit(apiManager.postScore(PostRequest(dataPost = DataPost(player.level, player.name, player.score))))
    }.flowOn(Dispatchers.IO)

    fun listenNewTopPlayer(): Flow<Player> = channelFlow {
        val socket = SocketHandler.getSocket()
        socket.on("notification.top") { it ->
            val player = jsonToPlayer( it[0].toString())

            if(player != null) {
                val find = players.value.find { p -> p.id == player.id }
                if(find == null){
                    val mutableList = mutableListOf<Player>()
                    mutableList.addAll(players.value)
                    mutableList.add(player)
                    mutableList.sortByDescending { it.score }
                    _players.value = mutableList
                    trySend(player)
                }
            }
        }
        awaitClose()
    }

    private fun playerToJson(player: Player): String{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter(Player::class.java)
        return  jsonAdapter.toJson(player)
    }

    private fun jsonToPlayer(player: String): Player?{
        val jsonAdapter: JsonAdapter<Player> = moshi.adapter(Player::class.java)
        return  jsonAdapter.fromJson(player)
    }
}