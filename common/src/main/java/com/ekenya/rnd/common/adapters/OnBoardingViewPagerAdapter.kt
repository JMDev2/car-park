package com.ekenya.rnd.common.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.ekenya.rnd.common.R
import com.ekenya.rnd.common.model.OnBoardingData


class OnBoardingViewPagerAdapter(
    private val context: Context,
    private val onBoardingDataList: List<OnBoardingData>
    ) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    @SuppressLint("LongLogTag")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view : View = LayoutInflater.from(context).inflate(R.layout.onboarding_layout, null)
        val imageView : ImageView
        val title : TextView
        val desc : TextView

        Log.d("OnBoardingViewPagerAdapter", onBoardingDataList.size.toString())

        imageView = view.findViewById(R.id.imageView)
        title = view.findViewById(R.id.title)
        desc = view.findViewById(R.id.description)

        imageView.setImageResource(onBoardingDataList[position].image)
        title.text = onBoardingDataList[position].title
        desc.text = onBoardingDataList[position].desc

        container.addView(view)
        return view
    }
}