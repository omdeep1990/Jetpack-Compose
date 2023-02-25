package com.omdeep.jetpackcompose.ui.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.omdeep.jetpackcompose.data.model.FirebaseMessage
import com.omdeep.jetpackcompose.utils.Constants.CHATS
import com.omdeep.jetpackcompose.utils.Constants.MESSAGES

class ChatViewModel : ViewModel() {
    val auth: FirebaseAuth = Firebase.auth
    private var dbRef: DatabaseReference = FirebaseDatabase.getInstance().reference

    var _message = MutableLiveData("")
    var message: LiveData<String> = _message

    var allChats = mutableStateListOf<FirebaseMessage>()
    var chatList: List<FirebaseMessage> = allChats

    fun updateMsgOnValueChange(newMsg: String) {
        _message.value = newMsg
    }

    fun sendMessage(message: String, uid: String) {
        val messageObject = FirebaseMessage(message, auth.currentUser?.uid)
        dbRef.child(CHATS).child(uid + auth.currentUser?.uid).child(MESSAGES).push()
            .setValue(messageObject)
            .addOnSuccessListener {
                dbRef.child(CHATS).child(auth.currentUser?.uid + uid).child(MESSAGES).push()
                    .setValue(messageObject)
            }
    }

    fun retrieveMessages(uid: String) {
        dbRef.child(CHATS).child(uid + auth.currentUser?.uid).child(MESSAGES)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    allChats.clear()
                    for (userData in snapshot.children) {
                        val msgs = userData.getValue(FirebaseMessage::class.java)
                        if (msgs != null) {
                            allChats.add(msgs)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }
}