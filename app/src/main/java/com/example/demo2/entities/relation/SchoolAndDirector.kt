package com.example.demo2.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.demo2.entities.Director
import com.example.demo2.entities.School

data class SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)
