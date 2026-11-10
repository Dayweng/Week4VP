package com.darren.practicew3_4.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.darren.practicew3_4.R
import com.darren.practicew3_4.model.Booking
import com.darren.practicew3_4.model.ImageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LatViewModel : ViewModel(){

    private val _imageList = MutableStateFlow(listOf(
        ImageItem(R.drawable.whatsapp_image_2026_10_07_at_12_16_36_pm__1_, "Photo 1"),
        ImageItem(R.drawable.whatsapp_image_2026_10_07_at_12_16_37_pm, "Photo 2"),
        ImageItem(R.drawable.whatsapp_image_2026_10_07_at_12_16_37_pm__1_, "Photo 3"),
        ImageItem(R.drawable.whatsapp_image_2026_10_07_at_12_16_38_pm, "Photo 4"),
        ImageItem(R.drawable.whatsapp_image_2026_10_07_at_12_17_41_pm, "Photo 5"),
        ImageItem(R.drawable.whatsapp_image_2026_10_07_at_12_17_42_pm, "Photo 6")
        )
    )
    val galleryList: StateFlow<List<ImageItem>> = _imageList.asStateFlow()

    private val _bookinglist = MutableStateFlow<List<Booking>>(emptyList())
    val bookingList: StateFlow<List<Booking>> = _bookinglist.asStateFlow()

    fun addBookingList(name:String, email:String, photo: Bitmap?){
        _bookinglist.update { it + Booking(name, email, photo) }
        _isFormOpen.value = false
    }

    fun deleteBookingList(index:Int){
        _bookinglist.update { list ->
            if(index in list.indices) list.toMutableList().apply { removeAt(index) }
            else list
        }
    }

    private val _isFormOpen = MutableStateFlow(false)
    val isFormOpen: StateFlow<Boolean> = _isFormOpen.asStateFlow()

    fun openForm(){
        _isFormOpen.value = true
    }

    fun closeForm(){
        _isFormOpen.value = false
    }
}