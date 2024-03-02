package com.test.banner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.banner.adapter.ImageNetAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.BannerBinding
import com.youth.banner.indicator.RectangleIndicator
import com.youth.banner.util.BannerUtils

class BannerFragment : Fragment() {

    private lateinit var binding: BannerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            banner.setAdapter(ImageNetAdapter(DataBean.getTestData3()))
            banner.setIndicator(RectangleIndicator(activity))
            banner.setIndicatorSpace(BannerUtils.dp2px(4f))
            banner.setIndicatorRadius(0)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.banner.start()
    }

    override fun onStop() {
        super.onStop()
        binding.banner.stop()
    }

    companion object {
        fun newInstance(): Fragment {
            return BannerFragment()
        }
    }
}
