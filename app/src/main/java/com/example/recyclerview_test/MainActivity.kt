package com.example.recyclerview_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var itemAdapter: ItemAdapter

    private val itemList = DataGenerator().createItemList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonListener()

        itemAdapter = ItemAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = itemAdapter
        itemAdapter.submitList(itemList)

    }

    override fun longItemClick(item: Item) {
        Log.d("TAG", "INSIDE - LongClick")
        itemAdapter.isMultiState = true
        Log.d("TAG", "State = ${itemAdapter.isMultiState}")

        itemAdapter.submitList(itemList.toMultiMode())
    }

    private fun buttonListener() {
        binding.btnRefresh.setOnClickListener {
            itemAdapter.isMultiState = false
            itemAdapter.submitList(itemList.toSingleMode())
        }
    }
}