package bai.bai.bai.demo.activity

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.util.Log
import bai.bai.bai.demo.R
import bai.bai.bai.demo.fragment.Fragment1
import bai.bai.bai.demo.fragment.MyFragmentAdapter
import bai.bai.bai.demo.fragment.Fragment2
import kotlinx.android.synthetic.main.activity_my_fragment.*

class MyFragmentActivity : FragmentActivity()
        , Fragment1.OnFragmentInteractionListener
        , Fragment2.OnFragmentInteractionListener {

    private lateinit var mFragments: MutableList<Fragment>
    private lateinit var mFragment1: Fragment1
    private lateinit var mFragment2: Fragment2
    private lateinit var mFragmentAdapter: MyFragmentAdapter

    private lateinit var mFragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fragment)



//        initFragment()
//        initListener()

        initFragment2()
        initListener2()

//        initListener3()

    }

    //region fragment与viewPager连用
    private fun initFragment() {
        mFragment1 = Fragment1.newInstance("bai", "yun")
        mFragment2 = Fragment2.newInstance("111", "222")
        mFragments = mutableListOf()
        mFragments.add(mFragment1)
        mFragments.add(mFragment2)
    }

    private fun initListener() {
        mFragmentAdapter = MyFragmentAdapter(this.supportFragmentManager, mFragments)
        vp_fragment.adapter = mFragmentAdapter
        vp_fragment.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }
    //endregion

    //region 纯代码添加、隐藏fragment
    private fun initFragment2(){
        mFragment1 = Fragment1.newInstance("bai", "yun")
        mFragment2 = Fragment2.newInstance("bai", "yun")
        mFragmentTransaction = supportFragmentManager.beginTransaction()
        mFragmentTransaction.add(R.id.ll_fragment, mFragment1, "fragment111")
        mFragmentTransaction.commit()
    }

    private fun initListener2(){
        btn_show_or_hide.setOnClickListener {
            val sfm = supportFragmentManager.beginTransaction()
//            sfm.hide(supportFragmentManager.findFragmentByTag("fragment111"))
//            sfm.add(R.id.ll_fragment, mFragment2, "fragment222")

            //添加fragment时可以add也可以replace，add切换fragment时不会重新创建，是什么样子切换回来还是什么样子；用replace的效果就是：切换fragment时每次都会重新创建初始化。
            sfm.replace(R.id.ll_fragment, mFragment2, "fragment222")
            sfm.addToBackStack(null)//添加到fragmentManager的BackStack中，与之对应的是popBackStack()
            sfm.commit()
        }
    }
    //endregion

    //region xml添加，代码隐藏
    private fun initListener3(){
//        btn_show_or_hide.setOnClickListener {
//            val ft = supportFragmentManager.beginTransaction()
//            ft.hide(supportFragmentManager.findFragmentById(R.id.fragment_1))
//            ft.commit()
//        }
    }
    //endregion



    override fun onFragmentInteraction222(string: String) {
        Log.d("baibai", "onFragmentInteraction222")
        Log.d("baibai", string)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}
