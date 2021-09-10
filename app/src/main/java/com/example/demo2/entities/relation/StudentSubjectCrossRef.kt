package com.example.demo2.entities.relation

import androidx.room.Entity

@Entity(tableName = "student_subject_cross_table", primaryKeys = ["studentName", "subjectName"])
data class StudentSubjectCrossRef(
    val studentName: String,
    val subjectName: String
)