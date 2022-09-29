package com.android.a27092022

import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.a27092022.databinding.FragmentHomeBinding
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeFragment : Fragment() {
    val adapter by lazy { Adapter() }
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.adapter=adapter
        val retrofit=RetrofitHelper.getRetrofit()
        val call=retrofit.create(NetworkApi::class.java)

        download(call)

        binding.refleshLayout.setOnRefreshListener {
            download(call)
        }

    }
    fun download(call:NetworkApi){
        call.getPosts().enqueue(object :retrofit2.Callback<List<ModelItem>>{

            override fun onResponse(
                call: retrofit2.Call<List<ModelItem>>,
                response: Response<List<ModelItem>>
            ) {
                if (response.isSuccessful){
                    val data= response.body()
                    adapter.submitList(data)
                    binding.refleshLayout.isRefreshing=false
                }
            }

            override fun onFailure(call: retrofit2.Call<List<ModelItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}