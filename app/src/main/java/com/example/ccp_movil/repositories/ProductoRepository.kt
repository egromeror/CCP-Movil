package com.example.ccp_movil.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.ccp_movil.data.model.Producto
import com.example.ccp_movil.network.NetworkServiceAdapter

class ProductoRepository (val application: Application){
    fun refreshData(callback: (List<Producto>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getProductos({
            //Guardar los productos de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }

    /*fun getProducto(productId: Int, callback: (List<Producto>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getProductos(collectorId,{
            //Guardar el Colleccionista de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }*/
}