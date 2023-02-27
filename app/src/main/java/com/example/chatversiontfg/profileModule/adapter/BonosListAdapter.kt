package com.example.chatversiontfg.profileModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.R
import com.example.chatversiontfg.common.entities.BonoEntity
import com.example.chatversiontfg.common.entities.RestauranteEntity
import com.example.chatversiontfg.common.utils.listeners.RestauranteListener
import com.example.chatversiontfg.databinding.ItemBonoBinding

class BonosListAdapter(private var listener: RestauranteListener):
    ListAdapter<BonoEntity, RecyclerView.ViewHolder>(BonoDiffCallback()) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_bono, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val bono = getItem(position)

        with(holder as ViewHolder) {

            setListener(bono.puestoComida)

            with(binding){

                nombreRestaurante.text = bono.puestoComida.nombre
                tipoComida.text = bono.puestoComida.tipoComida

            }

        }
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemBonoBinding.bind(view)

        fun setListener(restauranteEntity: RestauranteEntity) {
            with(binding) {
                itemBono.setOnClickListener {
                   listener.onClickRestaurante(restauranteEntity)
                }

            }

        }

    }
}


class BonoDiffCallback: DiffUtil.ItemCallback<BonoEntity>() {

    override fun areItemsTheSame(oldItem: BonoEntity, newItem: BonoEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BonoEntity, newItem: BonoEntity): Boolean {
        return oldItem == newItem
    }

}

