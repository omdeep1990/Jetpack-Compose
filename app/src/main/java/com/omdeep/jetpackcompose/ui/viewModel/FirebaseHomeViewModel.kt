package com.omdeep.jetpackcompose.ui.viewModel

import android.content.Context
import android.widget.Toast
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
    var totalUsers: ArrayList<FirebaseUser> = ArrayList()
    var usersListData = MutableLiveData<List<FirebaseUser>>()

//    var usersListData : MutableLiveData<ArrayList<FirebaseUser>> = MutableLiveData<ArrayList<FirebaseUser>>()

    fun usersList(context: Context) : LiveData<List<FirebaseUser>> {
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
                usersListData.value = totalUsers
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Data retrieving failed.", Toast.LENGTH_SHORT).show()
            }
        })
        return usersListData
    }
}