package com.test.banner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.test.banner.adapter.TopLineAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.ActivityTouTiaoBinding
import com.youth.banner.Banner
import com.youth.banner.transformer.ZoomOutPageTransformer
import com.youth.banner.util.LogUtils

class TouTiaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTouTiaoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTouTiaoBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        //实现1号店和淘宝头条类似的效果
        binding.banner.setAdapter(TopLineAdapter(DataBean.getTestData2()))
            .setOrientation(Banner.VERTICAL)
            .setPageTransformer(ZoomOutPageTransformer())
            .setOnBannerListener { data: Any, position: Int ->
                Snackbar.make(binding.banner, (data as DataBean).title, Snackbar.LENGTH_SHORT).show()
                LogUtils.d("position：$position")
            }
    }
}