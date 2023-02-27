package com.example.chatversiontfg.partnersFragmentModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.R
import com.example.chatversiontfg.common.entities.SocioEntity
import com.example.chatversiontfg.common.utils.ImageClass
import com.example.chatversiontfg.databinding.ItemAsistente1Binding
import com.example.chatversiontfg.common.utils.listeners.SocioListener

class PartnersAdapter(private var listener: SocioListener): ListAdapter<SocioEntity,RecyclerView.ViewHolder>(PartnerDiffCallback()) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_asistente1, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val socio = getItem(position)

        with(holder as ViewHolder) {

            setListener(socio)

            with(binding) {

                nombreUsuario.text = socio.asistente.nombreUsuario
                empresa.text = socio.empresa.nombre
                cargoEmpresa.text = socio.cargo

                ImageClass().loadImage(socio.asistente.imagen,imagenAsistente,fragmentContext)

            }
        }
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemAsistente1Binding.bind(view)

        fun setListener(socioEntity: SocioEntity) {

            with(binding) {

                itemasistente.setOnLongClickListener {

                    listener.onClickSocio(socioEntity)

                    true

                }

            }

        }

    }

    class PartnerDiffCallback:DiffUtil.ItemCallback<SocioEntity>() {

        override fun areItemsTheSame(oldItem: SocioEntity, newItem: SocioEntity): Boolean {
            return oldItem.idSocio == newItem.idSocio
        }

        override fun areContentsTheSame(oldItem: SocioEntity, newItem: SocioEntity): Boolean {
            return oldItem == newItem
        }

    }

}

