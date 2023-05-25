package com.aeax.smndice.domain.models.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "player")
class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @Nonnull
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "score")
    var score: Int,

    @ColumnInfo(name = "level")
    var level: Int
) {

}