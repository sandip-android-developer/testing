package com.example.demo2.entities.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.demo2.entities.Student
import com.example.demo2.entities.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)
