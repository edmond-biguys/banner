package com.test.banner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.banner.adapter.MyRecyclerViewAdapter
import com.test.banner.databinding.ActivityRecyclerviewBannerBinding

class RecyclerViewBannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerviewBannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBannerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.netRv.layoutManager = LinearLayoutManager(this)
        binding.netRv.adapter = MyRecyclerViewAdapter(this)
    }
}
