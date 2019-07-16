package com.example.simplefund2.fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplefund2.R
import com.example.simplefund2.isLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            Toast.makeText(this, "Login", Toast.LENGTH_LONG).show()

            isLogin = true
            finish()
        }
    }
}
