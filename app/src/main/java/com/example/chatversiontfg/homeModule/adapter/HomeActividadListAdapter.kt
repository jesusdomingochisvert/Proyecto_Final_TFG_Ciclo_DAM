package com.example.chatversiontfg.homeModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.R
import com.example.chatversiontfg.common.entities.ActividadEntity
import com.example.chatversiontfg.common.utils.ImageClass
import com.example.chatversiontfg.common.utils.listeners.ActividadListener
import com.example.chatversiontfg.databinding.ItemActividadesBinding

class HomeActividadListAdapter(private var listener: ActividadListener):
    ListAdapter<ActividadEntity, RecyclerView.ViewHolder>(ActividadDiffCallback()) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_actividades, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val activivdad = getItem(position)

        with(holder as ViewHolder) {

            setListener(activivdad)

            with(binding) {

                nombreActividad.text = activivdad.nombre
                horaActividad.text = activivdad.horaInicio

                ImageClass().loadImage(activivdad.imagen,imgActividad,fragmentContext)

            }

        }

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemActividadesBinding.bind(view)

        fun setListener(actividadEntity: ActividadEntity) {

            with(binding) {

                root.setOnClickListener {
                    listener.onClickActividad(actividadEntity)
                }

            }

        }

    }

    class ActividadDiffCallback: DiffUtil.ItemCallback<ActividadEntity>() {

        override fun areItemsTheSame(oldItem: ActividadEntity, newItem: ActividadEntity): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: ActividadEntity, newItem: ActividadEntity): Boolean {

            return oldItem == newItem

        }


    }

}