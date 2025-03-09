package com.example.ccp_movil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ccp_movil.R
import com.example.ccp_movil.data.model.Producto
import com.bumptech.glide.Glide

class ProductoAdapter (private val productList: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>(){

    inner class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val productImage : ImageView = view.findViewById(R.id.productImage)
        val produtName: TextView = view.findViewById(R.id.productName)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_item, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productList[position]
        holder.produtName.text = producto.nombre
        holder.productDescription.text = producto.descripcion
        holder.productPrice.text = producto.precioUnitario.toString()
        Glide.with(holder.productImage.context).load("https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"/*producto.url*/).into(holder.productImage)
    }

    override fun getItemCount() = productList.size
}