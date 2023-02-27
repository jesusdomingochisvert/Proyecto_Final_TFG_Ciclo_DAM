package com.example.chatversiontfg.chatModule.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.R
import com.example.chatversiontfg.common.utils.Message
import com.example.chatversiontfg.databinding.DeleteOptionBinding
import com.example.chatversiontfg.databinding.ItemMsgBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.coroutineContext

class MessageAdapter(private val user: String): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private lateinit var context: Context

    private var messages: MutableList<Message> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        context = parent.context

        return MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_msg, parent, false))

    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        val message = messages[position]

        if(user == message.from){

            with(holder) {

                with(binding) {

                    clSendMsg.visibility = View.VISIBLE

                    clReceiveMsg.visibility = View.GONE

                    sendMsg.text = message.message

                    root.setOnLongClickListener {

                        val view = LayoutInflater.from(context).inflate(R.layout.delete_option, null)

                        val bindingDelete = DeleteOptionBinding.bind(view)

                        val dialog = AlertDialog.Builder(context)
                            .setTitle("Delete Message")
                            .setView(bindingDelete.root)
                            .create()

                        bindingDelete.everyone.setOnClickListener {

                            Firebase.firestore
                                .collection("chats")
                                .document(CongresoApplication.socioId)
                                .collection("messages")
                                .document(message.id.toString())
                                .delete()
                                .addOnSuccessListener {

                                    delete(message)

                                }.addOnFailureListener {

                                    Toast.makeText(context, "Error en la eliminación de mensaje", Toast.LENGTH_SHORT).show()

                                }

                            dialog.dismiss()

                        }

                        bindingDelete.forMe.setOnClickListener {

                            delete(message)

                            dialog.dismiss()

                        }

                        bindingDelete.cancel.setOnClickListener {

                            dialog.dismiss()

                        }

                        dialog.show()

                        true

                    }

                }

            }

        } else {

            with(holder) {

                with(binding) {

                    clSendMsg.visibility = View.GONE

                    clReceiveMsg.visibility = View.VISIBLE

                    receiveMsg.text = message.message

                }

            }

        }

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<Message>) {

        messages = list

        notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun delete(message: Message) {

        val index = messages.indexOf(message)

        if (index != -1) {

            messages.removeAt(index)

            notifyItemRemoved(index)

        }

        notifyDataSetChanged()

    }

    inner class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val binding = ItemMsgBinding.bind(itemView)

    }

}