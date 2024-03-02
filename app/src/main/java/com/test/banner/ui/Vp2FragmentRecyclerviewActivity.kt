package com.test.banner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.test.banner.adapter.ImageAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.ActivityVp2FragmentRecyclerviewBinding
import com.test.banner.util.TabLayoutMediator
import com.youth.banner.indicator.CircleIndicator

class Vp2FragmentRecyclerviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVp2FragmentRecyclerviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVp2FragmentRecyclerviewBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.vp2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        BannerListFragment.newInstance(position)
                    }
                    1 -> {
                        BlankFragment.newInstance()
                    }
                    else -> {
                        BannerFragment.newInstance()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 3
            }
        }
        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab: TabLayout.Tab, position: Int ->
            tab.setText(
                "页面$position"
            )
        }.attach()
        binding.banner.addBannerLifecycleObserver(this)
            .setAdapter(ImageAdapter(DataBean.getTestData()))
            .setIntercept(false)
            .setIndicator(CircleIndicator(this))
    }
}
