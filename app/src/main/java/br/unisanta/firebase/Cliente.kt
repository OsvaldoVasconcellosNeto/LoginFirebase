package br.unisanta.firebase

import com.google.firebase.Timestamp

data class Cliente(
    val nome: String = "",
    val cpf: String = "",
    val ultima_compra: Timestamp? = null
)
