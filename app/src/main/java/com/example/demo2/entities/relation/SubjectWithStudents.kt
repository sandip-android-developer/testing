package com.example.demo2.entities.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.demo2.entities.Student
import com.example.demo2.entities.Subject

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)