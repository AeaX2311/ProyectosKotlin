package com.aeax.smndice.dao

import androidx.room.*
import com.aeax.smndice.domain.models.game.Player

@Dao
interface IPlayerDao {
    @Query("SELECT * FROM player")
    fun getAllPlayers(): List<Player>

    @Query("SELECT * FROM player WHERE id = :id")
    fun getPlayer(id: Int): Player

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlayers(players: List<Player>)

    @Delete
    fun deletePlayer(player: Player)

    @Update
    fun updatePlayer(player: Player)
}