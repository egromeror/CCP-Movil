package com.example.ccp_movil.cache

import android.app.Application
import com.example.ccp_movil.data.model.Producto

public class Global : Application() {
    companion object {
        @JvmField
        var productList: List<Producto> = arrayListOf<Producto>()
    }
}