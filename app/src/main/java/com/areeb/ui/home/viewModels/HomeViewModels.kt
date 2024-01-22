package com.areeb.ui.home.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.data.models.WorkShopEntity
import com.areeb.data.repository.WorkShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModels @Inject constructor(private val workShopRepository: WorkShopRepository) :
    ViewModel() {

    private val _workshopData = MutableLiveData<List<WorkShopEntity>>()
    val workShopList: LiveData<List<WorkShopEntity>> get() = _workshopData

    init {
        getAllWorkshops()
    }

     fun getAllWorkshops() {
        viewModelScope.launch {
            workShopRepository.getAllWorkshops().collectLatest {
                withContext(Dispatchers.IO) {
                    if (it.isEmpty()) {
                        addDummyData()
                    }

                }
                _workshopData.value = it
            }
        }
    }

    suspend  fun addDummyData() {

        val dummyData = listOfWorkShop()
        workShopRepository.addWorkShopList(dummyData)



    }


    private fun listOfWorkShop(): List<WorkShopEntity> {
        val w1 = WorkShopEntity(
            workshopName = "Android",
            duration = "2hrs",
            image = "https://i.ytimg.com/vi/l2UDgpLz20M/maxresdefault.jpg"
        )
        val w3 = WorkShopEntity(
            workshopName = "react",
            duration = "2hrs",
            image = "https://miro.medium.com/v2/resize:fit:1050/1*i3hzpSEiEEMTuWIYviYweQ.png"
        )
        val w4 = WorkShopEntity(
            workshopName = "node.js",
            duration = "2hrs",
            image = "https://www.thinktanker.io/wp-content/uploads/2021/03/share-nodejs-logo.png"
        )
        val w5 = WorkShopEntity(
            workshopName = "ruby on rails",
            duration = "2hrs",
            image = "https://www.devopsschool.com/blog/wp-content/uploads/2022/03/eb9e3b7dab09358e7cf13f188f64f9f4.png"
        )
        val w6 = WorkShopEntity(
            workshopName = "ios",
            duration = "2hrs",
            image = "https://miro.medium.com/v2/resize:fit:1200/1*8AE7QsUjaog7rQEd1s5JAg.jpeg"
        )
        val w7 = WorkShopEntity(
            workshopName = "kotlin",
            duration = "2hrs",
            image = "https://beecrowd.io/wp-content/uploads/2022/08/Beecrowd-Agosto-3-02-2560.jpg"
        )

        val w8 = WorkShopEntity(
            workshopName = "go Lang",
            duration = "2hrs",
            image = "https://golang-challenge.org/wp-content/uploads/2022/12/wp7041189.jpg"
        )


        val w9 = WorkShopEntity(
            workshopName = "java",
            duration = "2hrs",
            image = "https://www.developer.com/wp-content/uploads/2022/12/java-programming-tutorials-tips.png"
        )


        val w10 = WorkShopEntity(
            workshopName = "angular",
            duration = "2hrs",
            image = "https://miro.medium.com/v2/resize:fit:750/1*YPSX0nSww0SsNGCQyBNOpA.jpeg"
        )




        return listOf(w1, w3, w4, w5, w6, w7, w8, w9, w10)
    }


}