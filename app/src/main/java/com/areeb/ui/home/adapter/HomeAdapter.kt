package com.areeb.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity
import com.areeb.ui.home.viewHolders.HomeViewHolders

class HomeAdapter(val workShopList: List<WorkShopEntity>, userEntitiy: UserEntitiy? = null) :
    RecyclerView.Adapter<HomeViewHolders>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolders {
        return HomeViewHolders.from(parent)
    }

    override fun getItemCount(): Int {
        return workShopList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolders, position: Int) {
        holder.bind(workShopEntity = workShopList[position])
    }
}