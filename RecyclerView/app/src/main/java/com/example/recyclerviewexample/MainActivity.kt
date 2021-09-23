package com.example.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var rvAdapter: UserAdapter
    private lateinit var usersList: ArrayList<User>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {    
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        createUser()
    }

    private fun initRecycler() {
        usersList = arrayListOf()
        binding.rv.layoutManager = LinearLayoutManager(this)
        rvAdapter = UserAdapter(usersList)
        binding.rv.adapter = rvAdapter
    }

    private fun loadData(inFile: String): String {
        var content = ""
        try {
            val stream = assets.open(inFile)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            content = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content
    }

    private fun createUser() {
        val data = loadData("users_list.json")
        try {
            val usersJSON = JSONArray(data)
            for (i in 0 until usersJSON.length()) {
                val userJSON = usersJSON.getJSONObject(i)
                val user = User(
                    userJSON.getString("firstName"),
                    userJSON.getString("lastName"),
                    userJSON.getString("email"),
                    userJSON.getString("image")
                )
                usersList.add(user)
            }
            rvAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}