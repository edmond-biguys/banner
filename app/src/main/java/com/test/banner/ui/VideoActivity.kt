package com.test.banner.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.test.banner.adapter.MultipleTypesAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.ActivityVideoBinding
import com.test.banner.indicator.NumIndicator
import com.test.banner.viewholder.VideoHolder
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.listener.OnPageChangeListener

/**
 * 仿淘宝商品详情，banner第一个放视频,然后首尾不能自己滑动，加上自定义数字指示器
 */
class VideoActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityVideoBinding
    private lateinit var player: StandardGSYVideoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater).also { 
            setContentView(it.root)
        }
        binding.banner.addBannerLifecycleObserver(this)
//            .setAdapter(MultipleTypesAdapter(this, DataBean.getTestDataVideo()))
            .setAdapter(MultipleTypesAdapter(this, DataBean.getVideos()))
            .setIndicator(NumIndicator(this))
            .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
            .addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    stopVideo(position)
                }

                override fun onPageSelected(position: Int) {
                    Log.e("--", "position:$position")
                    stopVideo(position)
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
    }

    private fun stopVideo(position: Int) {
        if (!this::player.isInitialized) {
            val viewHolder = binding.banner.adapter.viewHolder
            if (viewHolder is VideoHolder) {
                player = viewHolder.player
                if (position != 0) {
                    player.onVideoPause()
                }
            }
        } else {
            if (position != 0) {
                player.onVideoPause()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (this::player.isInitialized)
            player.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        if (this::player.isInitialized)
            player.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        //释放所有
        if (this::player.isInitialized)
            player.setVideoAllCallBack(null)
        super.onBackPressed()
    }
}
