package com.kmt.passwordmanager.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_info_table")
data class AuthInfoRecord(

    /**
     * ID записи
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = -1L,

    /**
     * Наименование
     */
    @ColumnInfo(name = "name")
    var name: String = "",

    /**
     * Логин
     */
    @ColumnInfo(name = "login")
    var login: String = "",

    /**
     * Пароль
     */
    @ColumnInfo(name = "password")
    var password: String = "",

    /**
     * Ссылка на ресурс
     */
    @ColumnInfo(name = "url")
    var url: String = "",

    /**
     * Заметка к записи
     */
    @ColumnInfo(name = "caption")
    var caption: String = "",

    /**
     * Флаг того, был ли на интерфейсе в данной сессии пользователю отображены данные логина/пароля
     */
    var isRevealed: Boolean = false
)