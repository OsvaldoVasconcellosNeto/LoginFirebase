package br.unisanta.firebase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.firestore

class AddColecao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_colecao)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = Firebase.firestore

        findViewById<Button>(R.id.btn_add_adicionar).setOnClickListener {

            val nome = findViewById<EditText>(R.id.edt_Nome).text.toString()
            val cpf = findViewById<EditText>(R.id.edt_Cpf).text.toString()

            if (nome.isEmpty() || cpf.isEmpty()) {
                Toast.makeText(
                    this,
                    "Preencha todos os campos!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val stamp = Timestamp.now()

            val hash_cliente = hashMapOf(
                "nome" to nome,
                "cpf" to cpf,
                "ultima_compra" to stamp
            )

            db.collection("colecao_cliente")
                .add(hash_cliente)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        // Limpa os campos
                        findViewById<EditText>(R.id.edt_Nome).text.clear()
                        findViewById<EditText>(R.id.edt_Cpf).text.clear()

                        // Popup de sucesso
                        Toast.makeText(
                            this,
                            "Cliente cadastrado com sucesso!",
                            Toast.LENGTH_LONG
                        ).show()

                        Log.d("FIRESTORE", "SUCESSO")

                    } else {

                        // Popup de erro
                        Toast.makeText(
                            this,
                            "Erro ao cadastrar: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()

                        Log.d("FIRESTORE", "FALHOU ${task.exception?.message}")
                    }
                }
        }

        findViewById<Button>(R.id.btn_add_sair).setOnClickListener {
            finish()
        }

    }
}