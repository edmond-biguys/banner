package com.test.banner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.banner.adapter.ImageAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.ActivityGalleryBinding
import com.youth.banner.indicator.CircleIndicator

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        with(binding) {
            /**
             * 画廊效果
             */
            banner1.setAdapter(ImageAdapter(DataBean.getTestData2()))
            banner1.setIndicator(CircleIndicator(this@GalleryActivity))
            //添加画廊效果
            banner1.setBannerGalleryEffect(50, 10)
            //(可以和其他PageTransformer组合使用，比如AlphaPageTransformer，注意但和其他带有缩放的PageTransformer会显示冲突)
            //添加透明效果(画廊配合透明效果更棒)
            //mBanner1.addPageTransformer(new AlphaPageTransformer());
            /**
             * 魅族效果
             */
            banner2.setAdapter(ImageAdapter(DataBean.getTestData()))
            banner2.setIndicator(indicator, false)
            //添加魅族效果
            banner2.setBannerGalleryMZ(20)
        }
    }
}