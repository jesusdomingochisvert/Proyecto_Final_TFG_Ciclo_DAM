package com.example.chatversiontfg.partnersFragmentModule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.R
import com.example.chatversiontfg.chatModule.ChatActivity
import com.example.chatversiontfg.common.entities.SocioEntity
import com.example.chatversiontfg.common.retrofit.metodos.PartnerMethods
import com.example.chatversiontfg.databinding.FragmentPartnersBinding

import com.example.chatversiontfg.partnersFragmentModule.adapter.PartnersAdapter
import com.example.chatversiontfg.common.utils.CorrutinaClass
import com.example.chatversiontfg.common.utils.ImageClass
import com.example.chatversiontfg.common.utils.listeners.SocioListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PartnersFragment : Fragment(), SocioListener {

    private lateinit var fragmentContext: Context

    private lateinit var partnersAdapter: PartnersAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: FragmentPartnersBinding

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        db = Firebase.firestore

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentPartnersBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {

        super.onStart()

        if (CongresoApplication.email.isNotEmpty()) {

            getAllPartners()

            setupRecyclerView()

        }

    }

    private fun getAllPartners(){

        CorrutinaClass().executeAction(fragmentContext) {

            val socios = PartnerMethods().getPartners()

            partnersAdapter.submitList(socios)

            binding.progressbar.visibility= View.GONE

        }

    }

    private fun setupRecyclerView() {

        partnersAdapter = PartnersAdapter(this)

        linearLayoutManager = LinearLayoutManager(fragmentContext)

        binding.rvPartners.apply {

            layoutManager = linearLayoutManager

            adapter = partnersAdapter

        }

        val userRef = db.collection("users").document(CongresoApplication.email)

        userRef.collection("chats").get().addOnSuccessListener { socios ->

            val listSocios = socios.toObjects(SocioEntity::class.java)

            partnersAdapter.submitList(listSocios)

        }

        userRef.collection("chats").addSnapshotListener { socios, error ->

            if(error == null){

                socios?.let {

                    val listSocios = it.toObjects(SocioEntity::class.java)

                    partnersAdapter.submitList(listSocios)

                }

            }

        }

    }

    override fun onClickSocio(socioEntity: SocioEntity) {

        val items = arrayOf("Ir a la web de la empresa", "Contactar", "Iniciar Chat")

        MaterialAlertDialogBuilder(requireActivity()).setItems(items) { _, i ->

                when (i) {

                    2 -> {

                        CongresoApplication.socioId = socioEntity.idSocio.toString()
                        CongresoApplication.nombreChat = socioEntity.asistente.nombre
                        CongresoApplication.imagenChat = socioEntity.asistente.imagen

                        startActivity(Intent(fragmentContext, ChatActivity::class.java))

                    }

                    1 -> abrirCorreo(socioEntity.asistente.correo)

                    0 -> abrirWeb(socioEntity.empresa.enlace)

                }

        }.show()

    }


    private fun abrirCorreo(correo: String) {

            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correo))

            intent.setType("message/rfc822")

            startActivity(Intent.createChooser(intent, "Elige un cliente de Correo:"))

    }

    private fun abrirWeb(enlace: String) {

        if (enlace.isEmpty()) {

            Toast.makeText(requireActivity(), R.string.main_error_no_website, Toast.LENGTH_LONG).show()

        } else {

            val websiteIntent = Intent().apply {

                action = Intent.ACTION_VIEW

                data = Uri.parse(enlace)

            }

            startActivity(websiteIntent)

        }

    }

}