package com.example.simplefund2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.example.simplefund2.fragment.HomeFragment
import com.example.simplefund2.fragment.PortfolioFragment
import com.example.simplefund2.fragment.TransactionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
                R.id.nav_portfolio  -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, PortfolioFragment()).commit()
                R.id.nav_transaction -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TransactionFragment()).commit()
                R.id.nav_faq -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
            }
            return@setOnNavigationItemSelectedListener true
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.r_nav, menu)
        return true
    }


}
