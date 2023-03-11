package com.example.chatversiontfg.activityInfoModule

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.common.retrofit.metodos.ActividadMethods
import com.example.chatversiontfg.common.utils.CorrutinaClass
import com.example.chatversiontfg.common.utils.ImageClass
import com.example.chatversiontfg.databinding.ActivityInfoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ActivityInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    private lateinit var fragmentContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityInfoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        fragmentContext = this

        CongresoApplication.idActividad = intent.extras?.getLong("id")

        comprobar()

        getActivity()

        binding.btnAsistir.setOnClickListener {

            asistir()

        }

        binding.btnBack.setOnClickListener(){

            onBackPressed()

        }

    }

    private fun asistir() {

        try {

            Toast.makeText(this, "Asistencia correcta", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {

            Log.i("error", e.message.toString())

        }

    }

    private fun comprobar(){

        try {

            CorrutinaClass().executeAction(fragmentContext){

                Toast.makeText(this, "Comprobacion correcta", Toast.LENGTH_SHORT).show()

            }

        }catch (e:Exception){

            Log.i("ERROR_RESERVA", e.message.toString())

        }

    }

    @SuppressLint("ResourceAsColor")
    private fun getActivity(){

        CorrutinaClass().executeAction(fragmentContext) {

            val actividadEntity = ActividadMethods().getActividad(CongresoApplication.idActividad)

            with(binding){

                tituloActividad.text = actividadEntity.nombre.uppercase()

                ImageClass().loadImage(actividadEntity.imagen, imgActividad, fragmentContext)

                hora.text = buildString {

                    append(actividadEntity.horaInicio)

                    append("-")

                    append(actividadEntity.horaFin)

                }

                descripcionTxt.text= actividadEntity.descripcion

                containerDialogActividad.visibility= View.VISIBLE

                imgActividad.visibility= View.VISIBLE

                progressbar.visibility= View.GONE
            }

        }

    }

}