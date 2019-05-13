package com.example.simplefund2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.simplefund2.product.ProductEfekFragment
import com.example.simplefund2.product.ProductInfoFragment
import com.example.simplefund2.product.ProductPerformanceFragment
import kotlinx.android.synthetic.main.activity_product_profile.*

class ProductProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_profile)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        vp_main.adapter = MyPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(vp_main)

        btn_subscription.setOnClickListener {
            val intent = Intent(it.context, SubscriptionActivity::class.java)
            intent.putExtra("name", "Avrist Dana Liquid")
            intent.putExtra("asset_type", "Pasar Uang")
            intent.putExtra("risk_type", "Konservatif")
            intent.putExtra("subs_min", 10000)
            it.context.startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun getIncomingIntent() {
        if (intent.hasExtra("PortfolioID")) {
            val PortfolioID = intent.getIntExtra("PortfolioID", 0)

//            tv_PortfolioName.text = intent.getStringExtra("PortfolioName")
//            tv_nav_per_unit.text = intent.getStringExtra("NAVperUnit")
//            tv_PositionDate.text = intent.getStringExtra("PositionDate")
//            tv_1d.text = intent.getStringExtra("r1D")
//            tv_mtd.text = intent.getStringExtra("rMTD")
//            tv_ytd.text = intent.getStringExtra("rYTD")
//            tv_1mo.text = intent.getStringExtra("r1Mo")
//            tv_3mo.text = intent.getStringExtra("r3Mo")
//            tv_6mo.text = intent.getStringExtra("r6Mo")
//            tv_1y.text = intent.getStringExtra("r1Y")
//            tv_2y.text = intent.getStringExtra("r2Y")
//            tv_5y.text = intent.getStringExtra("r5Y")
//            getPerformanceChartData(PortfolioID)
        }
    }

    class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        val pages = listOf(
            ProductInfoFragment(),
            ProductPerformanceFragment(),
            ProductEfekFragment()
        )

        override fun getItem(p0: Int): Fragment {
            return pages[p0]
        }

        override fun getCount(): Int {
            return pages.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> "Informasi"
                1 -> "Kinerja"
                else -> "Efek"
            }
        }

    }

}
