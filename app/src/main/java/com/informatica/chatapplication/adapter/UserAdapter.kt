package com.informatica.chatapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.informatica.chatapplication.MessageScreenActivity
import com.informatica.chatapplication.R
import com.informatica.chatapplication.databinding.ChatUserViewBinding
import com.informatica.chatapplication.model.UserModel

class UserAdapter(
    private val context:Context,
    private val list:ArrayList<UserModel>
    ) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ChatUserViewBinding.bind(view)
    }
    ////
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_user_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        with(holder.binding)
        {
            tvUserName.text = data.userFullName
            tvUserEmail.text = data.userEmail

            llUser.setOnClickListener {
                (context as MessageScreenActivity).clickListener()
            }
        }
    }

    override fun getItemCount(): Int = list.size

}