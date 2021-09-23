package com.example.recyclerviewexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.databinding.ItemUserBinding
import com.squareup.picasso.Picasso


class UserAdapter(var user: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(user[position]) {
                val firstName = this.firstName
                val lastName = this.lastName
                binding.tvName.text = firstName + " " + lastName
                binding.tvEmail.text = this.email
                Picasso.get().load(this.image).into(binding.ivUser)
            }
        }
    }

    override fun getItemCount(): Int = user.size

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}