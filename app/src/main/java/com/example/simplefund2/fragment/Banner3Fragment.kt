package com.example.simplefund2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simplefund2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_banner3.*

class Banner3Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Picasso.get().load("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png").into(img_banner)

    }


}