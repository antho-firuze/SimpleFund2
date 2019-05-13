package com.example.simplefund2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.view.*
import com.example.simplefund2.fragment.FaqFragment
import com.example.simplefund2.fragment.HomeFragment
import com.example.simplefund2.fragment.PortfolioFragment
import com.example.simplefund2.fragment.TransactionFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val fragmentHome: Fragment = HomeFragment()
    val fragmentPortfolio: Fragment = PortfolioFragment()
    val fragmentTransaction: Fragment = TransactionFragment()
    val fragmentFaq: Fragment = FaqFragment()
    var activeFragment: Fragment = fragmentHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navigation.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
//                R.id.nav_portfolio  -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, PortfolioFragment()).commit()
//                R.id.nav_transaction -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TransactionFragment()).commit()
//                R.id.nav_faq -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FaqFragment()).addToBackStack("FaqFragment").commit()
//            }
//            return@setOnNavigationItemSelectedListener true
//        }
//
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragmentPortfolio, "2").hide(fragmentPortfolio).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragmentTransaction, "3").hide(fragmentTransaction).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragmentFaq, "4").hide(fragmentFaq).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragmentHome, "1").commit()

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragmentHome).commit()
                    activeFragment = fragmentHome
                }
                R.id.nav_portfolio  -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragmentPortfolio).commit()
                    activeFragment = fragmentPortfolio
                }
                R.id.nav_transaction -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragmentTransaction).commit()
                    activeFragment = fragmentTransaction
                }
                R.id.nav_faq -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(fragmentFaq).commit()
                    activeFragment = fragmentFaq
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        navigationMenuRefresh()
    }

    override fun onResume() {
        super.onResume()
        navigationMenuRefresh()
    }

    fun navigationMenuRefresh() {
        var menu = navigation.menu
        if (isLogin) {
            menu.findItem(R.id.nav_portfolio).isVisible = true
            menu.findItem(R.id.nav_transaction).isVisible = true
        } else {
            menu.findItem(R.id.nav_portfolio).isVisible = false
            menu.findItem(R.id.nav_transaction).isVisible = false
        }
    }

}
