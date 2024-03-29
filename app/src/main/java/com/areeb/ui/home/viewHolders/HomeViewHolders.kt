package com.areeb.ui.home.viewHolders

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.areeb.data.models.UserEntitiy
import com.areeb.data.models.WorkShopEntity
import com.areeb.ui.common.clickListener.ClickListener
import com.areeb.workshopregister.R
import com.areeb.workshopregister.databinding.HomeItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


class HomeViewHolders(private val binding: HomeItemBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    init {
        binding.applyButton.setOnClickListener(this)
    }

    private lateinit var workShopEntity: WorkShopEntity
    private lateinit var userEntitiy: UserEntitiy
    private lateinit var listener: ClickListener<WorkShopEntity>

    fun bind(workShopEntity: WorkShopEntity, userEntitiy: UserEntitiy, listener: ClickListener<WorkShopEntity>) {
        this.workShopEntity = workShopEntity
        this.userEntitiy = userEntitiy
        this.listener = listener

        settingUpTheUi()

    }

    private fun settingUpTheUi() {
        addImage()
        with(binding) {
            workShopTitle.text = workShopEntity.workshopName
            workShopDuration.text = workShopEntity.duration

            var isWorkshopApplied = false

            userEntitiy.workShopAppliedFor?.forEach {
                Log.e("userid", "check  use user ${it.id}")
                Log.e("userid", "${workShopEntity.id}")
                if (it.id == workShopEntity.id) {
                    applyButton.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.green
                        )
                    )

                    applyButton.isClickable = false
                    applyButton.text = "Applied"

                    isWorkshopApplied = true
                }
            }

            // Set the default background color to blue if the workshop is not applied
            if (!isWorkshopApplied) {
                applyButton.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.blue
                    )
                )
            }
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

    override fun onClick(v: View?) {
        if (v?.id == binding.applyButton.id) {
            listener.onClick(workShopEntity)
        }
    }
}