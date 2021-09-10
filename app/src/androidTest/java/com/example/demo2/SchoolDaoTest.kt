package com.example.demo2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.demo2.entities.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class SchoolDaoTest {
    private lateinit var database: SchoolDatabase
    private lateinit var dao: SchoolDao

    @get:Rule
    var instantTaskExcutionRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SchoolDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.schoolDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertSchool() = runBlockingTest {
        val school = School("Jake Wharton School")
        dao.insertSchool(school)
        val schoolList = dao.observableAllSchool().getOrAwaitValue()
        assertThat(schoolList).contains(school)
    }

    @Test
    fun deleteSchool() = runBlockingTest {
        val school = School("Jake Wharton School")
        dao.insertSchool(school)
        dao.deleteSchool(school)
        val schoolList = dao.observableAllSchool().getOrAwaitValue()
        assertThat(schoolList).doesNotContain(school)
    }

    @Test
    fun insertDirector() = runBlockingTest {
        val director = Director("Mike Litoris", "Jake Wharton School")
        dao.insertDirector(director)
        val directorList = dao.observableDirector().getOrAwaitValue()
        assertThat(directorList).contains(director)
    }

    @Test
    fun deleteDirector() = runBlockingTest {
        val director = Director("Mike Litoris", "Jake Wharton School")
        dao.insertDirector(director)
        dao.deleteDirector(director)
        val directorList = dao.observableDirector().getOrAwaitValue()
        assertThat(directorList).doesNotContain(director)
    }

    @Test
    fun insertStudent() = runBlockingTest {
        val student = Student("Beff Jezos", 2, "Kotlin School")
        dao.insertStudent(student)
        val studentList = dao.observableStudent().getOrAwaitValue()
        assertThat(studentList).contains(student)
    }

    @Test
    fun deleteStudent() = runBlockingTest {
        val student = Student("Beff Jezos", 2, "Kotlin School")
        dao.insertStudent(student)
        dao.deleteStudent(student)
        val studentList = dao.observableStudent().getOrAwaitValue()
        assertThat(studentList).doesNotContain(student)
    }

    @Test
    fun insertSubject() = runBlockingTest {
        val subject = Subject("Dating for programmers")
        dao.insertSubject(subject)
        val subjectList = dao.observableSubject().getOrAwaitValue()
        assertThat(subjectList).contains(subject)
    }

    @Test
    fun deleteSubject() = runBlockingTest {
        val subject = Subject("Dating for programmers")
        dao.insertSubject(subject)
        dao.deleteSubject(subject)
        val subjectList = dao.observableSubject().getOrAwaitValue()
        assertThat(subjectList).doesNotContain(subject)
    }

    @Test
    fun getSchoolAndDirectorWithSchoolName() = runBlockingTest {
        val school = School("Jake Wharton School")
        dao.insertSchool(school)
        val director = Director("Mike Litoris", "Jake Wharton School")
        dao.insertDirector(director)
        val name = dao.getSchoolAndDirectorWithSchoolName(school.schoolName).getOrAwaitValue()
        assertThat(name.isNotEmpty()).isTrue()
    }

    @Test
    fun getSchoolWithStudents()= runBlockingTest {
        val school = School("Jake Wharton School")
        dao.insertSchool(school)
        val student =  Student("Beff Jezos", 2, "Kotlin School")
        dao.insertStudent(student)
        val name = dao.getSchoolWithStudents(school.schoolName).getOrAwaitValue()
        assertThat(name.isNotEmpty()).isTrue()
    }

    @Test
    fun getStudentsOfSubject()= runBlockingTest {
        val student =  Student("Beff Jezos", 2, "Kotlin School")
        dao.insertStudent(student)
        val subject = Subject("Dating for programmers")
        dao.insertSubject(subject)
        val name = dao.getStudentsOfSubject(subject.subjectName).getOrAwaitValue()
        assertThat(name.isNotEmpty()).isTrue()
    }

    @Test
    fun getSubjectOfStudent()= runBlockingTest {
        val student =  Student("Beff Jezos", 2, "Kotlin School")
        dao.insertStudent(student)
        val subject = Subject("Dating for programmers")
        dao.insertSubject(subject)
        val name = dao.getSubjectOfStudent(student.studentName).getOrAwaitValue()
        assertThat(name.isNotEmpty()).isTrue()
    }
}