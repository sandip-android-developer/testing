package com.example.demo2

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demo2.entities.Director
import com.example.demo2.entities.School
import com.example.demo2.entities.Student
import com.example.demo2.entities.Subject
import com.example.demo2.entities.relation.*

@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    @Delete
    suspend fun deleteSchool(school: School)

    @Delete
    suspend fun deleteDirector(director: Director)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Transaction
    @Query("SELECT * FROM school_table")
    fun observableAllSchool(): LiveData<List<School>>

    @Transaction
    @Query("SELECT * FROM director_table")
    fun observableDirector(): LiveData<List<Director>>


    @Transaction
    @Query("SELECT * FROM student_table")
    fun observableStudent(): LiveData<List<Student>>

    @Transaction
    @Query("SELECT * FROM subject_table")
    fun observableSubject(): LiveData<List<Subject>>

    @Transaction
    @Query("SELECT * FROM school_table WHERE schoolName=:schoolName")
     fun getSchoolAndDirectorWithSchoolName(schoolName: String): LiveData<List<SchoolAndDirector>>


    @Transaction
    @Query("SELECT * FROM school_table WHERE schoolName=:schoolName")
     fun getSchoolWithStudents(schoolName: String): LiveData<List<SchoolWithStudents>>

    @Transaction
    @Query("SELECT * FROM subject_table WHERE subjectName=:subjectName")
     fun getStudentsOfSubject(subjectName: String): LiveData<List<SubjectWithStudents>>

    @Transaction
    @Query("SELECT * FROM student_table WHERE studentName=:studentName")
     fun getSubjectOfStudent(studentName: String): LiveData<List<StudentWithSubjects>>
}