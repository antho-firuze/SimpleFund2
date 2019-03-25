package com.example.simplefund2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.example.simplefund2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_marketupdate.view.*
import kotlinx.android.synthetic.main.list_product.view.*
import ss.com.bannerslider.ImageLoadingService
import ss.com.bannerslider.Slider
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder


class HomeFragment : Fragment() {

    // LIST PRODUCT
    data class Product(val name: String, val navperunit: String, val r1d: String)
    fun getDataProduct(datas: ArrayList<Product>) {
        datas.add(Product("Avrist Dana Liquid", "1,234.75", "-1.23 %"))
        datas.add(Product("Avrist Dana LQ45", "1,234.75", "-1.50 %"))
        datas.add(Product("Avrist Dana LQ55", "1,003.25", "-1.80 %"))
        datas.add(Product("Avrist Dana LQ65", "1,500.53", "1.5 %"))
        recycleView.adapter = ListAdapter_Product(products)
//        Handler().postDelayed({
//            if (::adapter_product.isInitialized) {
//                adapter_product.notifyDataSetChanged()
//            } else {
//                recycleView.adapter = ListAdapter_Product(products)
//            }
//        }, 2000)
    }
    val products: ArrayList<Product> = ArrayList()
    lateinit var adapter_product: ListAdapter_Product
    lateinit var layoutManager_product: LinearLayoutManager

    // LIST MARKET UPDATE
    data class MarketUpdate(val title: String, val date: String, val img_url: String)
    fun getDataMarketUpdate(datas: ArrayList<MarketUpdate>) {
        datas.add(MarketUpdate("Reksadana Saham dan Indeks Tertekan Aksi Profit Taking Sejak Awal 2019, Kenapa?", "Today 08:30 WIB", "https://picsum.photos/200/?image=0"))
        datas.add(MarketUpdate("Manulife Aset Manajemen Indonesia: Tekanan ekonomi memudar, pasar makin kondusif", "Today 09:30 WIB", "https://picsum.photos/200/?image=10"))
        datas.add(MarketUpdate("IHSG menguat tipis hingga akhir perdagangan sesi I", "Today 10:30 WIB", "https://picsum.photos/200/?image=20"))
        datas.add(MarketUpdate("Kurs rupiah menguat ke Rp 14.208 per dollar AS", "Today 12:30 WIB", "https://picsum.photos/200/?image=30"))
        recycleView_MU.adapter = ListAdapter_MU(marketupdates)
//        Handler().postDelayed({
//            if (::adapter_mu.isInitialized) {
//                adapter_mu.notifyDataSetChanged()
//            } else {
//                recycleView_MU.adapter = ListAdapter_MU(marketupdates)
//            }
//        }, 2000)
    }
    val marketupdates: ArrayList<MarketUpdate> = ArrayList()
    lateinit var adapter_mu: ListAdapter_MU
    lateinit var layoutManager_mu: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        Slider.init(PicassoImageLoadingService())
//        banner_slider1.setAdapter(MainSliderAdapter())

        Picasso.get().load("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg").into(banner_slider1)

        layoutManager_product = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycleView.layoutManager = layoutManager_product
        getDataProduct(products)

        layoutManager_mu = LinearLayoutManager(context)
        recycleView_MU.layoutManager = layoutManager_mu
        getDataMarketUpdate(marketupdates)
    }

    class MainSliderAdapter: SliderAdapter() {
        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder?) {
            when (position){
                0 -> viewHolder?.bindImageSlide("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg")
                1 -> viewHolder?.bindImageSlide("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg")
                2 -> viewHolder?.bindImageSlide("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png")
            }
        }
    }

    class PicassoImageLoadingService: ImageLoadingService {
        override fun loadImage(url: String?, imageView: ImageView?) {
            Picasso.get().load(url).fit().into(imageView)
        }

        override fun loadImage(resource: Int, imageView: ImageView?) {
            Picasso.get().load(resource).fit().into(imageView)
        }

        override fun loadImage(url: String?, placeHolder: Int, errorDrawable: Int, imageView: ImageView?) {
            Picasso.get().load(url).fit().placeholder(placeHolder).error(errorDrawable).into(imageView)
        }
    }

    class ListAdapter_Product(val rows: ArrayList<Product>) : RecyclerView.Adapter<ListAdapter_Product.ListViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
            return ListViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_product, p0, false))
        }

        override fun getItemCount(): Int {
            return rows.size
        }

        override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
            val r = rows[p1]
            p0.itemView.apply {
                tv_name.text = r.name
                tv_navperunit.text = r.navperunit
                tv_r1d.text = r.r1d
            }
        }

        class ListViewHolder(v: View) : RecyclerView.ViewHolder(v) {}
    }

    class ListAdapter_MU(val rows: ArrayList<MarketUpdate>) : RecyclerView.Adapter<ListAdapter_MU.ListViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
            return ListViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_marketupdate, p0, false))
        }

        override fun getItemCount(): Int {
            return rows.size
        }

        override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
            val r = rows[p1]
            p0.itemView.apply {
                tv_title.text = r.title
                tv_date.text = r.date
                Picasso.get().load(r.img_url).fit().into(img_market)
            }
        }

        class ListViewHolder(v: View) : RecyclerView.ViewHolder(v) {}
    }
}
