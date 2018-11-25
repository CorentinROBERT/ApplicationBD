package com.example.corentinrobert.applicationbd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity(),AnkoLogger {

    var list = listOf<MobileCourse>()
    val courseDb = CourseDb()

    private fun showList() {
        list = courseDb.requestCourse()
        txtView.text=""
        info("NB COURS : ${list.size}")
        for (c in list) {
            info("Voici un cours ${c.title} d'une durée de : ${c.time}h")
            txtView.text = txtView.text.toString() + "\nId : ${c.id} Cours : ${c.title} durée : ${c.time}h"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showList()

        txtNomCours.setOnClickListener{
            txtNomCours.setText("",TextView.BufferType.EDITABLE)
        }
        txtCoursHeure.setOnClickListener{
            txtCoursHeure.setText("",TextView.BufferType.EDITABLE)
        }
        txtIdDelete.setOnClickListener{
            txtIdDelete.setText("",TextView.BufferType.EDITABLE)
        }

        txtIdModify.setOnClickListener{
            txtIdModify.setText("",TextView.BufferType.EDITABLE)
        }
        txtNomCoursModify.setOnClickListener{
            txtNomCoursModify.setText("",TextView.BufferType.EDITABLE)
        }
        txtHeureCoursModify.setOnClickListener{
            txtHeureCoursModify.setText("",TextView.BufferType.EDITABLE)
        }



        btnAjouter.setOnClickListener{
            doAsync {
                val course1 = MobileCourse(1,txtNomCours.text.toString(), txtCoursHeure.text.toString().toInt())
                courseDb.saveCourse(course1)

            }
            showList()
            toast("Enregistrement ajouté")
            showList()
        }
        btnSupprimer.setOnClickListener(){
            doAsync{
                courseDb.deleteCourse(txtIdDelete.text.toString().toInt())
            }
            showList()
            toast("Enregistrement supprimé")
            showList()
        }

        btnModifier.setOnClickListener{
            doAsync {
                courseDb.modifyCourse(txtIdModify.text.toString().toInt(),txtNomCoursModify.text.toString(),txtHeureCoursModify.text.toString().toInt())
            }
            showList()
            toast("Enregistrement modifié")
            showList()
        }


        }
}

