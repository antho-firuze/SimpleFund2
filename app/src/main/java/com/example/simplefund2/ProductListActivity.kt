package com.example.simplefund2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.Toast
import com.example.simplefund2.R
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.list_product2.view.*
import java.util.ArrayList

class ProductListActivity : AppCompatActivity() {

    // LIST PRODUCT
    data class Product(val name: String, val navperunit: String, val r1d: String, val risk: String, val curr: String)
    fun getDataProduct(datas: ArrayList<Product>) {
        datas.add(Product("Avrist Dana Liquid", "1,234.75", "-1.23 %", "Konservatif", "IDR"))
        datas.add(Product("Avrist Dana LQ45", "1,234.75", "-1.50 %", "Agresif", "IDR"))
        datas.add(Product("Avrist Dana LQ55", "1,003.25", "-1.80 %", "Moderat", "IDR"))
        datas.add(Product("Avrist Dana LQ65", "1,500.53", "1.5 %", "Agresif", "IDR"))
        recycleView.adapter = ListAdapter_Product(products)
    }
    val products: ArrayList<Product> = ArrayList()
    lateinit var adapter_product: ListAdapter_Product
    lateinit var layoutManager_product: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val assetType = listOf("Saham", "Pendapatan Tetap", "Campuran", "Pasar Uang", "Proteksi", "Indeks")
        val assetType_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, assetType)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, assetType)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_asset_type.adapter = assetType_adapter

        val fundType = listOf("Syariah", "Konvensional")
        val fundType_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fundType)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, assetType)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_fund_type.adapter = fundType_adapter

        layoutManager_product = LinearLayoutManager(this)
        recycleView.layoutManager = layoutManager_product
        getDataProduct(products)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    class ListAdapter_Product(val rows: ArrayList<Product>) :
        RecyclerView.Adapter<ListAdapter_Product.ListViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListViewHolder {
            return ListViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_product2, p0, false))
        }

        override fun getItemCount(): Int {
            return rows.size
        }

        override fun onBindViewHolder(p0: ListViewHolder, p1: Int) {
            val r = rows[p1]
            p0.itemView.apply {
                if (p1 % 2 == 0){
//                    layout_main.background = "@android:color/darker_gray"
                    layout_main.background = ContextCompat.getDrawable(p0.itemView.context, R.color.PowderBlue)
                }

                tv_name.text = r.name
                tv_risk.text = "(Resiko: ${r.risk})"
                tv_curr.text = r.curr
                tv_navperunit.text = r.navperunit
                tv_r1d.text = "(+${r.r1d})"

                tv_name.setOnClickListener {
                    Toast.makeText(it.context, it.tv_name.text, Toast.LENGTH_LONG).show()
                    val intent = Intent(it.context, ProductProfileActivity::class.java)
//                    val intent = Intent(it.context, PerformanceChartActivity::class.java)
//                    intent.putExtra("img_url", BASE_URL_PORTFOLIO+"${row.PortfolioCode.toUpperCase()}.png")
//                    intent.putExtra("PortfolioID", row.PortfolioID)
//                    intent.putExtra("PortfolioName", row.PortfolioNameFull.toUpperCase())
//                    intent.putExtra("PositionDate", FormDateFmt.format(row.PositionDate))
//                    intent.putExtra("NAVperUnit", CurrFmt.format(row.NAVperUnit))
//                    intent.putExtra("r1D", PercentFmt.format(row.r1D).replace(" ", ""))
//                    intent.putExtra("rMTD", PercentFmt.format(row.rMTD).replace(" ", ""))
//                    intent.putExtra("rYTD", PercentFmt.format(row.rYTD).replace(" ", ""))
//                    intent.putExtra("r1Mo", PercentFmt.format(row.r1Mo).replace(" ", ""))
//                    intent.putExtra("r3Mo", PercentFmt.format(row.r3Mo).replace(" ", ""))
//                    intent.putExtra("r6Mo", PercentFmt.format(row.r6Mo).replace(" ", ""))
//                    intent.putExtra("r1Y", PercentFmt.format(row.r1Y).replace(" ", ""))
//                    intent.putExtra("r2Y", PercentFmt.format(row.r2Y).replace(" ", ""))
//                    intent.putExtra("r5Y", PercentFmt.format(row.r5Y).replace(" ", ""))
                    it.context.startActivity(intent)
                }
            }
        }

        class ListViewHolder(v: View) : RecyclerView.ViewHolder(v) {}
    }

}
