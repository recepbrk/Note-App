package com.example.notesapp.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table") //Database ismini belirttik
data class Note(
   @PrimaryKey(autoGenerate = true) val id:Int?,         //id değişkenimize birincil anahtar verdik autogenerate özelliği ise otomatik id atayacak .
   @ColumnInfo(name = "title") val title:String?,       // sütun adlarını giriyoruz
   @ColumnInfo(name = "note")val note:String?,        // sütun adlarını giriyoruz
   @ColumnInfo(name = "date")val date:String?        // sütun adlarını giriyoruz



):java.io.Serializable
