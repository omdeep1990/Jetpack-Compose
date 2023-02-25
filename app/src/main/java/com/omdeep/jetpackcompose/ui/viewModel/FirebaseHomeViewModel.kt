package com.omdeep.jetpackcompose.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.omdeep.jetpackcompose.data.model.FirebaseUser

class FirebaseHomeViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private var dbRef : DatabaseReference = FirebaseDatabase.getInstance().reference
    var totalUsers = mutableStateListOf<FirebaseUser>()
    var usersListData : List<FirebaseUser> = totalUsers

    fun usersList(context: Context) {
        dbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                totalUsers.clear()
                for (users in snapshot.children) {
                    val currentUser = users.getValue(FirebaseUser::class.java)
                    if (currentUser != null) {
                        if (auth.currentUser!!.uid != currentUser.uid) {
                            totalUsers.add(currentUser)
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Data retrieving failed.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}