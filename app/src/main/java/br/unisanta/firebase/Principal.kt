package br.unisanta.firebase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class Principal : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        val db = Firebase.firestore
        val stamp = Timestamp.now()
        val hash_cliente = hashMapOf(
            "nome" to "Natalia",
            "cpf" to "442.242.145-65",
            "ultima_compra" to stamp
        )

        db.collection("colecao_cliente")
            .document("doc_cliente2")
            .set(hash_cliente)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Log.d("FIRESTORE","SUCESSO")
                } else{
                    Log.d("FIRESTORE","FALHOU ${task.exception?.message}")
                }
            }

        findViewById<Button>(R.id.btn_sair).setOnClickListener {
            finish()
        }
    }
}