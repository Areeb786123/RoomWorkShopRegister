package com.areeb.ui.home.viewHolders

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity
import com.areeb.workshopregister.R
import com.areeb.workshopregister.databinding.HomeItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class HomeViewHolders(private val binding: HomeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var workShopEntity: WorkShopEntity

    fun bind(workShopEntity: WorkShopEntity, userEntitiy: UserEntitiy? = null) {
        this.workShopEntity = workShopEntity

        settingUpTheUi()

    }

    private fun settingUpTheUi() {
        addImage()
        with(binding) {
            workShopTitle.text = workShopEntity.workshopName
            workShopDuration.text = workShopEntity.duration


//            userEntitiy.workShopAppliedFor?.forEach {
//                if (it.id == workShopEntity.id) {
//                    applyButton.setBackgroundColor(
//                        ContextCompat.getColor(
//                            binding.root.context,
//                            R.color.green
//                        )
//                    )
//
//                    applyButton.isClickable = false
//                    applyButton.text = "Applied"
//
//                } else {
//                    applyButton.setBackgroundColor(
//                        ContextCompat.getColor(
//                            binding.root.context,
//                            R.color.blue
//                        )
//                    )
//                }
//            }
        }
    }

    private fun addImage() {
        Glide.with(binding.root.context).load(workShopEntity.image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.workshopImage.visibility = View.INVISIBLE
                    return true

                }

                @SuppressLint("CheckResult")
                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.workshopImage.visibility = View.VISIBLE
                    return false

                }

            }).apply(RequestOptions().error(R.drawable.ic_launcher_background))
            .into(binding.workshopImage)
    }

    companion object {
        fun from(viewGroup: ViewGroup): HomeViewHolders {
            return HomeViewHolders(
                HomeItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

            )
        }
    }

}