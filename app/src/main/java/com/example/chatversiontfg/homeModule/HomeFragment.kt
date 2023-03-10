package com.example.chatversiontfg.homeModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.common.entities.ActividadEntity
import com.example.chatversiontfg.common.entities.EventoEntity
import com.example.chatversiontfg.common.entities.RestauranteEntity
import com.example.chatversiontfg.common.retrofit.metodos.*
import com.example.chatversiontfg.databinding.FragmentHomeBinding
import com.example.chatversiontfg.eventoInfoModule.EventoInfoActivity
import com.example.chatversiontfg.homeModule.adapter.HomeEventoListAdapter
import com.example.chatversiontfg.homeModule.adapter.HomeRestauranteListAdapter
import com.example.chatversiontfg.common.utils.CorrutinaClass
import com.example.chatversiontfg.common.utils.listeners.ActividadListener
import com.example.chatversiontfg.common.utils.listeners.EventoListener
import com.example.chatversiontfg.common.utils.listeners.RestauranteListener
import com.example.chatversiontfg.homeModule.adapter.HomeActividadListAdapter
import com.example.chatversiontfg.restauranteInfoModule.RestauranteInfoActivity
import com.example.chatversiontfg.activityInfoModule.ActivityInfoActivity
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class HomeFragment : Fragment(), EventoListener,RestauranteListener,ActividadListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var fragmentContext: Context
    private lateinit var homeEventoListAdapter: HomeEventoListAdapter
    private lateinit var homeRestauranteListAdapter: HomeRestauranteListAdapter
    private lateinit var homeActividadListAdapter: HomeActividadListAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        fragmentContext = this.requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sp = requireActivity().getSharedPreferences("socio", Context.MODE_PRIVATE)
        val socio = sp.getBoolean("socio",true)
        if (socio) {
            mostrarEventos()
        }else{
            binding.progressbarevents.visibility=VISIBLE
            mostrarActividades()

        }
        showRestaurantes()
        setupRecyclerViewRestaurantes()
        setupImagesCarousel()
    }

    private fun mostrarActividades() {
        binding.recentEventsActivities.text="Activities"
        binding.rvRecentEvents.visibility=INVISIBLE
        binding.rvActivities.visibility=VISIBLE
        showActividades()
        setupRecyclerViewActividades()
    }

    private fun mostrarEventos() {
        homeEventoListAdapter = HomeEventoListAdapter(this)
        binding.recentEventsActivities.text="Recent Events"
        binding.rvActivities.visibility=INVISIBLE
        showEventos()
        setupRecyclerViewEventos()
    }

    private fun showEventos() {
        if (CongresoApplication.recientes.isEmpty())binding.txtRecents.visibility=VISIBLE
        else homeEventoListAdapter.submitList(CongresoApplication.recientes)

    }

    private fun showRestaurantes() {
        CorrutinaClass().executeAction(requireContext()) {
            val restaurantes = RestauranteMethods().getRestaurantes()
            homeRestauranteListAdapter.submitList(restaurantes)
            binding.progressbarevents.visibility=GONE
            binding.rvEatZones.visibility=VISIBLE
            binding.progressbareatzones.visibility=GONE
        }
    }

    private fun showActividades() {
        CorrutinaClass().executeAction(fragmentContext) {
            val actividades = ActividadMethods().getActividades()
            homeActividadListAdapter.submitList(actividades)
        }
    }
    private fun setupRecyclerViewEventos() {
        linearLayoutManager =
            LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecentEvents.apply {
            layoutManager = linearLayoutManager
            adapter = homeEventoListAdapter
        }

        binding.progressbarevents.visibility=GONE

    }
    private fun setupRecyclerViewRestaurantes() {
        homeRestauranteListAdapter = HomeRestauranteListAdapter(this)
        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvEatZones.apply {
            layoutManager = linearLayoutManager
            adapter = homeRestauranteListAdapter
        }
    }
    private fun setupRecyclerViewActividades() {
        homeActividadListAdapter = HomeActividadListAdapter(this)
        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvActivities.apply {
            layoutManager = linearLayoutManager
            adapter = homeActividadListAdapter
        }
        binding.rvActivities.visibility=VISIBLE
        binding.progressbarevents.visibility=GONE
    }
    private fun setupImagesCarousel() {
        val images = mutableListOf<CarouselItem>()
        val carousel = binding.imgCarouselPublicidad

        CorrutinaClass().executeAction(fragmentContext) {
            val patrocinadores = PatrocinadorMethods().getPatrocinadores()

            for (p in patrocinadores!!){
                val imagen = CarouselItem(p.empresaCif.logo,p.empresaCif.nombre)
                images.add(imagen)
            }
            carousel.addData(images)
        }
    }

    override fun onClickEvento(eventoEntity: EventoEntity) {

        val intent = Intent(fragmentContext, EventoInfoActivity::class.java)

        intent.putExtra("id_evento", eventoEntity.id)

        startActivity(intent)

    }

    override fun onClickRestaurante(restauranteEntity: RestauranteEntity) {

        val intent = Intent(fragmentContext, RestauranteInfoActivity::class.java)

        intent.putExtra("id_restaurante", restauranteEntity.id)

        startActivity(intent)

    }

    override fun onClickActividad(actividadEntity: ActividadEntity) {

        val intent = Intent(fragmentContext, ActivityInfoActivity::class.java)

        intent.putExtra("id", actividadEntity.id)

        startActivity(intent)

    }

}