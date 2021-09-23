package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragments.databinding.ActivityMainBinding
import androidx.fragment.*

class MainActivity : AppCompatActivity(),Fragment2.FragmentCom {

    private lateinit var binding: ActivityMainBinding
    private var numFrag=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFrag1.setOnClickListener{

            val bundle=Bundle()
            bundle.putInt("numero",++numFrag)
            val transaction=supportFragmentManager.beginTransaction()
            val frag=Fragment1()
            frag.arguments = bundle
            transaction.replace(R.id.frag,frag)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.btnFrag2.setOnClickListener{
            val transaction=supportFragmentManager.beginTransaction()
            val frag=Fragment2()
            transaction.replace(R.id.frag,frag)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    override fun returnData(data: String) {
        binding.tvData.text=data
    }
}