package br.unisanta.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ListColecao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_colecao)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = Firebase.firestore

        val recyclerView = findViewById<RecyclerView>(
            R.id.lista_clientes
        )

        recyclerView.layoutManager = LinearLayoutManager(this)

        db.collection("colecao_item")
            .get()
            .addOnSuccessListener { resultado ->

                val listaClientes = mutableListOf<Cliente>()

                for (documento in resultado) {

                    val cliente = documento.toObject(Cliente::class.java)

                    listaClientes.add(cliente)
                }

                recyclerView.adapter = ClienteAdapter(listaClientes)

            }
            .addOnFailureListener { erro ->

                Toast.makeText(
                    this,
                    "Erro ao buscar itens: ${erro.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

        findViewById<Button>(R.id.btn_list_sair).setOnClickListener {
            finish()
        }
    }
}