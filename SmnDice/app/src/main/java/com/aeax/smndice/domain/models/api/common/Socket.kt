package com.aeax.smndice.domain.models.api.common

import com.aeax.smndice.domain.services.interfaces.IApiManager.Companion.BASE_URL
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket(BASE_URL)
        } catch (e: URISyntaxException) {
            println(e.message)
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}