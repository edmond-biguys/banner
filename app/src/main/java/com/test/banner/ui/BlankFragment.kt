package com.test.banner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.test.banner.R
import com.test.banner.adapter.ImageNetAdapter
import com.test.banner.bean.DataBean
import com.test.banner.databinding.TestBinding
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.util.BannerUtils

class BlankFragment : Fragment() {
    private lateinit var binding: TestBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val linearLayout = view!!.findViewById<LinearLayout>(R.id.ll_view)
//
//        //通过new的方式创建banner
//        val banner: Banner<*, *> = Banner<Any?, Any?>(activity)
//        banner.setAdapter(ImageNetAdapter(DataBean.getTestData3()))
//        banner.addBannerLifecycleObserver(this)
//        banner.setIndicator(CircleIndicator(activity))
//
//        //将banner加入到父容器中，实际使用不一定一样
//        linearLayout.addView(
//            banner,
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            BannerUtils.dp2px(120f)
//        )
    }

    companion object {
        fun newInstance(): Fragment {
            return BlankFragment()
        }
    }
}
