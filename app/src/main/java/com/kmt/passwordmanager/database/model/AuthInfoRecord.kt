package com.kmt.passwordmanager.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_info_table")
data class AuthInfoRecord(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = -1L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "login")
    var login: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "url")
    var url: String = "",

    @ColumnInfo(name = "caption")
    var caption: String = ""
)