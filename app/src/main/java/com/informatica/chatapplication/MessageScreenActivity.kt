package com.informatica.chatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.informatica.chatapplication.adapter.UserAdapter
import com.informatica.chatapplication.databinding.ActivityMessageScreenBinding
import com.informatica.chatapplication.ktx.gotoActivity
import com.informatica.chatapplication.ktx.toast
import com.informatica.chatapplication.model.UserModel

class MessageScreenActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMessageScreenBinding
    private lateinit var userList : ArrayList<UserModel>
    private lateinit var dbRef :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){

        userList = arrayListOf<UserModel>()
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
               userList.clear()
                if (snapshot.exists())
                {
                    for (user in snapshot.children){
                        val empData = user.getValue(UserModel::class.java)
                        userList.add(empData!!)
                    }
                    binding.rvUsers.apply {
                        layoutManager = LinearLayoutManager(this@MessageScreenActivity)
                        adapter = UserAdapter(this@MessageScreenActivity,userList)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
               toast("Something went wrong")
            }

        })

    }

    fun clickListener(){

        gotoActivity(ChatActivity::class.java)
    }
}