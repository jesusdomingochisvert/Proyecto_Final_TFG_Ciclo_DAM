package com.example.chatversiontfg.chatModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.chatModule.adapter.MessageAdapter
import com.example.chatversiontfg.common.utils.ImageClass
import com.example.chatversiontfg.common.utils.Message
import com.example.chatversiontfg.databinding.ActivityChatBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    private lateinit var binding: ActivityChatBinding

    private lateinit var listMessages: MutableList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = Firebase.firestore

        if(CongresoApplication.socioId.isNotEmpty() && CongresoApplication.email.isNotEmpty()) {

            initViews()

        }

    }

    private fun initViews() {

        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.adapter = MessageAdapter(CongresoApplication.email)

        binding.nameProfileChat.text = CongresoApplication.nombreChat

        ImageClass().loadImage(CongresoApplication.imagenChat, binding.profileChat, this)

        if (CongresoApplication.status == true) {

            binding.statusProfileChat.text = "Online"

            binding.statusProfileChat.visibility = View.VISIBLE

        } else {

            binding.statusProfileChat.text = "Offline"

            binding.statusProfileChat.visibility = View.INVISIBLE

        }

        binding.fabSendMsg.setOnClickListener { sendMessage() }

        val chatRef = db.collection("chats").document(CongresoApplication.socioId)

        chatRef.collection("messages")
            .orderBy("dob", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { messages ->

                listMessages = messages.toObjects(Message::class.java)

                (binding.rvChat.adapter as MessageAdapter).setData(listMessages)

            }

        chatRef.collection("messages")
            .orderBy("dob", Query.Direction.ASCENDING)
            .addSnapshotListener { messages, error ->

                if(error == null){

                    messages?.let {

                        listMessages = it.toObjects(Message::class.java)

                        (binding.rvChat.adapter as MessageAdapter).setData(listMessages)

                    }

                }

            }

    }

    private fun sendMessage(){

        val message = Message(message = binding.tietInput.text.toString(), from = CongresoApplication.email)

        if (listMessages != null) {

            for (m in listMessages) {

                m.id++

                message.id = m.id

            }

            db.collection("chats")
                .document(CongresoApplication.socioId)
                .collection("messages")
                .document(message.id.toString())
                .set(message)

            binding.tietInput.setText("")

        }

    }

}