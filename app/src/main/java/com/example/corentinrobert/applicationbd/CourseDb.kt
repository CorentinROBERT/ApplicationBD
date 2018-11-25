package com.example.corentinrobert.applicationbd

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.*
import org.jetbrains.anko.info
import android.content.ContentValues



class CourseDb(private val dbHelper: CourseDbHelper = CourseDbHelper.instance):AnkoLogger {

    fun requestCourse() = dbHelper.use {
        select(Table.MobileCourseTable.NAME,Table.MobileCourseTable.ID,Table.MobileCourseTable.TITLE, Table.MobileCourseTable.TIME).parseList(classParser<MobileCourse>())
    }

    fun saveCourse(course: MobileCourse) = dbHelper.use {
        insert(Table.MobileCourseTable.NAME,Table.MobileCourseTable.TITLE to course.title,
                Table.MobileCourseTable.TIME to course.time)
    }

    fun saveCourses(courseList: List<MobileCourse>) {
        for (c in courseList)
            saveCourse(c)
    }

    fun deleteCourse(id:Int)= dbHelper.use{
        delete(Table.MobileCourseTable.NAME, "_id = {userID}", "userID" to id)
    }

    fun modifyCourse(id:Int,nom:String,nbHeure:Int)=dbHelper.use{
        val args = ContentValues()
        args.put(Table.MobileCourseTable.TIME,nbHeure)
        args.put(Table.MobileCourseTable.TITLE,nom)
        update(Table.MobileCourseTable.NAME,args,"_id ="+id,null)
    }
}