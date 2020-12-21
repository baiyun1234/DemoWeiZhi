package bai.bai.bai.demo.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentManager
//import android.support.v4.app.FragmentPagerAdapter

class MyFragmentAdapter(fm: FragmentManager, fragments: MutableList<Fragment>) : FragmentPagerAdapter(fm) {

    private var fragmentList: MutableList<Fragment> = fragments

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}