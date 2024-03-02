package com.test.banner.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.banner.adapter.MyRecyclerViewAdapter
import com.test.banner.databinding.ActivityRecyclerviewBannerBinding

class BannerListFragment : Fragment() {

    private lateinit var binding: ActivityRecyclerviewBannerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityRecyclerviewBannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            text.text = "当前页:$index"
            netRv.layoutManager = LinearLayoutManager(activity)
            netRv.adapter = MyRecyclerViewAdapter(activity)
        }

    }

    companion object {
        private var index = 0
        fun newInstance(i: Int): Fragment {
            index = i
            return BannerListFragment()
        }
    }
}
