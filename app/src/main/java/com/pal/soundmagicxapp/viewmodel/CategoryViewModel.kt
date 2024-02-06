package com.pal.soundmagicxapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pal.soundmagicxapp.model.CategoryModel

class CategoryViewModel : ViewModel() {
    private val selectedCategory = MutableLiveData<CategoryModel>()

    fun setSelectedCategory(category: CategoryModel) {
        Log.e("##JP_04","$category pl")
        selectedCategory.value = category
    }

    fun getSelectedCategory(): LiveData<CategoryModel> {
        return selectedCategory
    }
}
