package com.example.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragments.databinding.Fragment2LayoutBinding
import java.lang.RuntimeException

class Fragment2 : Fragment() {

    private lateinit var binding:Fragment2LayoutBinding
    private var activityCont:FragmentCom? = null

    /*para enviar datos a la actividad principal debe tener ciertos parametros
    para esto, se crea una interface y se heredan en la actividad principal */

    interface FragmentCom{
        fun returnData(data:String)
    }

    //cuando se crea el fragmento

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //se debe comprobar que la actividad principal implemente el comunicador
        if(context is FragmentCom){
            activityCont = context
        }
        else throw RuntimeException(context.toString() + "must implement FragmentCom")
    }

    //cuando se destruye el fragmento

    override fun onDetach() {
        super.onDetach()
        activityCont = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2LayoutBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //se crear el listener del boton

        binding.btnEnviarDato.setOnClickListener{

            /*si no es nulo ? haga {code}
            no es nulo !!*/

            activityCont!!.returnData(binding.etDato.text.toString())
        }

    }
}