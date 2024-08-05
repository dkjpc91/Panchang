package com.mithilakshar.mithilapanchang.UI.View

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.mithilapanchang.Adapters.ProductAdapter
import com.mithilakshar.mithilapanchang.Models.Product
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.Room.Updates
import com.mithilakshar.mithilapanchang.Room.UpdatesDao
import com.mithilakshar.mithilapanchang.Utility.BillingManager
import com.mithilakshar.mithilapanchang.databinding.ActivityBillingBinding
import kotlinx.coroutines.launch

class BillingActivity : AppCompatActivity() {
    lateinit var binding: ActivityBillingBinding

    private lateinit var updatesDao: UpdatesDao

    private lateinit var billingManager: BillingManager
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityBillingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()

        billingManager = BillingManager(this)


    }

    private fun setupUI() {
        val recyclerView: RecyclerView = binding.billingrecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(emptyList()){

            purchaseProduct(it)
        }
        recyclerView.adapter = productAdapter

    }

    fun updateProductList(products: List<Product>) {

        Log.d("BillingManager", "Updating product list with ${products.size} items")

        // Log details of each product
        products.forEach { product ->
            Log.d("BillingManager", "Product Details - Title: ${product.title}, Description: ${product.description}, Price: ${product.price}, SKU: ${product.sku}")
        }

      runOnUiThread {
          productAdapter.updateData(products)

      }


    }

    private fun purchaseProduct(skuId: String) {
        billingManager.launchPurchaseFlow(this, skuId)
    }

    override fun onDestroy() {
        super.onDestroy()
        billingManager.onDestroy()
    }

     fun updatebillingdao(updates: Updates){

        lifecycleScope.launch {
            updatesDao.insert(updates)
        }

    }
}