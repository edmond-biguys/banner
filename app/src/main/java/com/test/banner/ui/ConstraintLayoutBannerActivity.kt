package com.test.banner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.banner.R
import com.test.banner.adapter.ImageTitleAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.ActivityConstraintLayoutBannerBinding
import com.youth.banner.config.BannerConfig
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.util.BannerUtils

class ConstraintLayoutBannerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityConstraintLayoutBannerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstraintLayoutBannerBinding.inflate(layoutInflater).also { 
            setContentView(it.root)
        }
        with(binding) {
            banner.setAdapter(ImageTitleAdapter(DataBean.getTestData()))
            banner.setIndicator(CircleIndicator(this@ConstraintLayoutBannerActivity))
            banner.setIndicatorSelectedColorRes(R.color.main_color)
            banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
            banner.setIndicatorMargins(
                IndicatorConfig.Margins(
                    0, 0,
                    BannerConfig.INDICATOR_MARGIN, BannerUtils.dp2px(12f)
                )
            )
            banner.addBannerLifecycleObserver(this@ConstraintLayoutBannerActivity)
        }
    }
}
