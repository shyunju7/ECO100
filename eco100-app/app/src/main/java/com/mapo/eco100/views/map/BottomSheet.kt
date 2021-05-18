package com.mapo.eco100.views.map

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mapo.eco100.config.NetworkSettings
import com.mapo.eco100.databinding.BottomSheetMapListBinding
import com.mapo.eco100.entity.staticmodel.ZeroShop
import com.mapo.eco100.views.network.NoConnectedDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetMapListBinding? = null
    private val binding get() = _binding!!
    private lateinit var shopListAdapter: MapListAdapter
    private lateinit var listData: ArrayList<ZeroShop>
    private var mainActivityContext: Context? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mainActivityContext = requireContext()
        _binding = BottomSheetMapListBinding.inflate(inflater, container, false)

        shopListAdapter = MapListAdapter(this)
        binding.shopList.adapter = shopListAdapter


        // intent 데이터 받아와서 listData에 값 추가해줘야함

        /*val result = arguments?.getSerializable("zeroShopList")
        listData = result as ArrayList<ZeroShop>
        shopListAdapter.listData = listData
        shopListAdapter.notifyDataSetChanged()*/

        return binding.root
    }

}