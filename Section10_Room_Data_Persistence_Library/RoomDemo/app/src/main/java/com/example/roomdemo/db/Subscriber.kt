package com.example.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_data_table")
data class Subscriber(
	
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "subscriber_name")
	val id: Int,
	
	@ColumnInfo(name = "subscriber_id")
	var name: String,
	
	@ColumnInfo(name = "subscriber_email")
	var email: String

)
