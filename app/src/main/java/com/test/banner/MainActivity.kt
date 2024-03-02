package com.test.banner

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.test.banner.adapter.ImageAdapter
import com.test.banner.adapter.ImageTitleAdapter
import com.test.banner.adapter.ImageTitleNumAdapter
import com.test.banner.adapter.MultipleTypesAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.ActivityMainBinding
import com.test.banner.ui.ConstraintLayoutBannerActivity
import com.test.banner.ui.GalleryActivity
import com.test.banner.ui.RecyclerViewBannerActivity
import com.test.banner.ui.TVActivity
import com.test.banner.ui.TouTiaoActivity
import com.test.banner.ui.VideoActivity
import com.test.banner.ui.Vp2FragmentRecyclerviewActivity
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.config.BannerConfig
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.indicator.RoundLinesIndicator
import com.youth.banner.util.BannerUtils
import com.youth.banner.util.LogUtils

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = DataBean.getTestData2()

        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
        val adapter = ImageAdapter(datas)
        binding.banner.setAdapter(adapter) //              .setCurrentItem(0,false)
            //添加生命周期观察者
            .addBannerLifecycleObserver(this) //设置指示器
            .setIndicator(CircleIndicator(this))
            .setOnBannerListener { data: Any, position: Int ->
                Snackbar.make(binding.banner, (data as DataBean).title, Snackbar.LENGTH_SHORT)
                    .show()
                LogUtils.d("position：$position")
            }

        //添加item之间切换时的间距(如果使用了画廊效果就不要添加间距了，因为内部已经添加过了)
//        banner.addPageTransformer(new MarginPageTransformer( BannerUtils.dp2px(10)));

        //和下拉刷新配套使用
        binding.swipeRefresh.setOnRefreshListener {
            //模拟网络请求需要3秒，请求完成，设置setRefreshing 为false
            Handler().postDelayed({
                binding.swipeRefresh.isRefreshing = false

                //给banner重新设置数据（完全覆盖）
                binding.banner.setDatas(DataBean.getTestData())
            }, 2000)
        }

        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            indicator.visibility = View.GONE
            styleImage.setOnClickListener {
                swipeRefresh.isEnabled = true
                banner.setAdapter(ImageAdapter(DataBean.getTestData()))
                banner.setIndicator(CircleIndicator(this@MainActivity))
                banner.setIndicatorGravity(IndicatorConfig.Direction.CENTER)
            }

            styleImageTitle.setOnClickListener {
                swipeRefresh.isEnabled = true
                banner.setAdapter(ImageTitleAdapter(DataBean.getTestData()))
                banner.setIndicator(CircleIndicator(this@MainActivity))
                banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
                banner.setIndicatorMargins(
                    IndicatorConfig.Margins(
                        0, 0,
                        BannerConfig.INDICATOR_MARGIN, BannerUtils.dp2px(12f)
                    )
                )
            }

            styleImageTitleNum.setOnClickListener {
                swipeRefresh.isEnabled = true
                //这里是将数字指示器和title都放在adapter中的，如果不想这样你也可以直接设置自定义的数字指示器
                banner.setAdapter(ImageTitleNumAdapter(DataBean.getTestData()))
                banner.removeIndicator()
            }

            styleMultiple.setOnClickListener {
                swipeRefresh.isEnabled = true
                banner.setIndicator(CircleIndicator(this@MainActivity))
                banner.setAdapter(MultipleTypesAdapter(this@MainActivity, DataBean.getTestData()))
            }

            styleNetImage.setOnClickListener {
                swipeRefresh.isEnabled = false
                //方法一：使用自定义图片适配器
//                banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));

                //方法二：使用自带的图片适配器
                banner.setAdapter(object : BannerImageAdapter<DataBean?>(DataBean.getTestData3()) {
                    override fun onBindView(
                        holder: BannerImageHolder?,
                        data: DataBean?,
                        position: Int,
                        size: Int
                    ) {
                        //图片加载自己实现
                        Glide.with(holder!!.itemView)
                            .load(data!!.imageUrl)
                            .thumbnail(Glide.with(holder.itemView).load(R.drawable.loading))
                            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                            .into(holder.imageView)
                    }


                })
                banner.setIndicator(RoundLinesIndicator(this@MainActivity))
                banner.setIndicatorSelectedWidth(BannerUtils.dp2px(15f))
            }

            changeIndicator.setOnClickListener {
                indicator.visibility = View.VISIBLE
                //在布局文件中使用指示器，这样更灵活
                banner.setIndicator(indicator, false)
                banner.setIndicatorSelectedWidth(BannerUtils.dp2px(15f))
            }

            gallery.setOnClickListener {
                startActivity(Intent(this@MainActivity, GalleryActivity::class.java))
            }

            rvBanner.setOnClickListener {
                startActivity(Intent(this@MainActivity, RecyclerViewBannerActivity::class.java))
            }

            clBanner.setOnClickListener {
                startActivity(Intent(this@MainActivity, ConstraintLayoutBannerActivity::class.java))
            }

            vpBanner.setOnClickListener {
                startActivity(Intent(this@MainActivity, Vp2FragmentRecyclerviewActivity::class.java))
            }

            bannerVideo.setOnClickListener {
                startActivity(Intent(this@MainActivity, VideoActivity::class.java))
            }

            bannerTv.setOnClickListener {
                startActivity(Intent(this@MainActivity, TVActivity::class.java))
            }

            topLine.setOnClickListener {
                startActivity(Intent(this@MainActivity, TouTiaoActivity::class.java))
            }
        }
    }
}
