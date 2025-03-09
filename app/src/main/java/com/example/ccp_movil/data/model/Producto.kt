package com.example.ccp_movil.data.model

import java.util.UUID

data class Producto (
    val id: UUID,
    val nombre: String,
    val descripcion: String,
    val url: String,
    val idProveedor: UUID,
    val precioUnitario: Double
)