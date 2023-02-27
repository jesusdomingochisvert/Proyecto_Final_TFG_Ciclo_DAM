package com.example.chatversiontfg.allEventsModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.allEventsModule.adapter.AllEventsListAdapter
import com.example.chatversiontfg.common.entities.EventoEntity
import com.example.chatversiontfg.common.retrofit.metodos.EventoMethods
import com.example.chatversiontfg.common.retrofit.metodos.RestauranteMethods
import com.example.chatversiontfg.common.utils.CorrutinaClass
import com.example.chatversiontfg.common.utils.listeners.EventoListener
import com.example.chatversiontfg.databinding.FragmentAllEventsBinding
import com.example.chatversiontfg.databinding.FragmentContactoBinding
import com.example.chatversiontfg.databinding.FragmentHomeBinding
import com.example.chatversiontfg.eventoInfoModule.EventoInfoActivity
import com.example.chatversiontfg.homeModule.adapter.HomeEventoListAdapter

class AllEventsFragment : Fragment(),EventoListener {

    private lateinit var binding: FragmentAllEventsBinding
    private lateinit var fragmentContext: Context
    private lateinit var allEventsAdapter: AllEventsListAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAllEventsBinding.inflate(inflater, container, false)
        fragmentContext = this.requireActivity()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showEventos()
        setupRecyclerViewEventos()

    }
    private fun showEventos() {
        CorrutinaClass().executeAction(requireContext()) {
            val eventos = EventoMethods().getEventos()
            allEventsAdapter.submitList(eventos)
            binding.rvAllevents.visibility=VISIBLE
            binding.progressbar.visibility=GONE
        }
    }
    private fun setupRecyclerViewEventos() {
        allEventsAdapter = AllEventsListAdapter(this)
        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.VERTICAL,false)
        binding.rvAllevents.apply {
            layoutManager = linearLayoutManager
            adapter = allEventsAdapter
        }
    }
    override fun onClickEvento(eventoEntity: EventoEntity) {
        val intent = Intent(fragmentContext, EventoInfoActivity::class.java)
        intent.putExtra("id_evento", eventoEntity.id)

        if(CongresoApplication.socio){
            rellenarRecientes(eventoEntity)
        }
        startActivity(intent)
    }

    private fun rellenarRecientes(eventoEntity: EventoEntity){
        if(!CongresoApplication.recientes.contains(eventoEntity)){
            if(CongresoApplication.recientes.size == 4){
                val borrar = CongresoApplication.recientes.firstElement()
                CongresoApplication.recientes.remove(borrar)
            }
            CongresoApplication.recientes.add(eventoEntity)
        }
    }
}