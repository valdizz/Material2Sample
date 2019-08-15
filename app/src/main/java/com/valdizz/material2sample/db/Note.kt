package com.valdizz.material2sample.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table", indices = [Index(value = ["note", "date"])])
data class Note(@PrimaryKey(autoGenerate = true) val id: Long, val note: String, val date: Long)