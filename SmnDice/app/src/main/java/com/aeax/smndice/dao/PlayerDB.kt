package com.aeax.smndice.dao

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.aeax.smndice.domain.models.game.Player

@Database(
    entities = [Player::class],
    version = 1,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2, spec = PlayerDB::class)
//    ],
//    exportSchema = true
)
abstract class PlayerDB : RoomDatabase() {
    abstract fun playerDao(): IPlayerDao

//    @RenameTable(fromTableName = "player", toTableName = "top")
//    class Migration1To2 : AutoMigrationSpec{}
}