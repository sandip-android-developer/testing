package com.example.demo2.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.demo2.entities.School
import com.example.demo2.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>
)