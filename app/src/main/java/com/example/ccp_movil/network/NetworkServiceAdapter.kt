package com.example.ccp_movil.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ccp_movil.data.model.Producto
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

class NetworkServiceAdapter(context: Context) {
    companion object{
        const val BASE_URL= "https://servicio-productos-596275467600.us-central1.run.app/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getProductos(onComplete:(resp:List<Producto>)->Unit, onError: (error: VolleyError)->Unit) {
        requestQueue.add(getRequest("Productos/Listar",
            { response ->
                Log.d("tag", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Producto>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Producto(id = UUID.fromString(item.getString("id")) ,nombre = item.getString("nombre"), descripcion = item.getString("descripcion"), url = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg"/*item.getString("url")*/, idProveedor = UUID.fromString(item.getString("idProveedor")), precioUnitario = item.getDouble("precioUnitario")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject, responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}