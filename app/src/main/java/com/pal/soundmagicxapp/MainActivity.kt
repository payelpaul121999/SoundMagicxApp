package com.pal.soundmagicxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.pal.soundmagicxapp.activity.ActivitySongList
import com.pal.soundmagicxapp.adapter.CategoryAdapter
import com.pal.soundmagicxapp.model.CategoryModel
import com.pal.soundmagicxapp.viewmodel.CategoryViewModel

class MainActivity : AppCompatActivity() {
    lateinit var categoryAdapter: CategoryAdapter

    private val categoryViewModel: CategoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
    }
  private  fun getData(){
        FirebaseFirestore.getInstance().collection("category").get().addOnSuccessListener {
           val data = it.toObjects(CategoryModel::class.java)
            Log.e("##JP_02","$data")
            setAdapter(data)
        }
    }
   private fun setAdapter(categoryList : List<CategoryModel>){
        val recyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
       categoryAdapter =  CategoryAdapter(categoryList)
        recyclerView.adapter = categoryAdapter
       categoryAdapter.setOnItemClickListener {
           val intent = Intent(this, ActivitySongList::class.java)
           intent.putExtra("category", it)
           startActivity(intent)
       }
    }
}

