package com.example.ccp_movil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ccp_movil.adapters.ProductoAdapter
import com.example.ccp_movil.cache.Global
import com.example.ccp_movil.data.RetrofitServiceFactory
import kotlinx.coroutines.launch

class CatalogoProductos: AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_productos)

        recyclerView = findViewById(R.id.recyclerViewAlbumes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {

            try {
                val productos = service.listCatalogoProductos()
                Global.productList = productos
            }
            catch (e: Exception) {
                // Code for handling the exception
            }

            adapter = ProductoAdapter(Global.productList)
            recyclerView.adapter = adapter
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegresar: Button = findViewById(R.id.btnRegresarInicio)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}