package br.unisanta.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

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

        findViewById<Button>(R.id.btn_go_add).setOnClickListener{
            val intentAdd = Intent(this, AddColecao::class.java)
            startActivity(intentAdd)
        }

        findViewById<Button>(R.id.btn_go_list).setOnClickListener{
            val intentList = Intent(this, ListColecao::class.java)
            startActivity(intentList)
        }

        findViewById<Button>(R.id.btn_sair).setOnClickListener {
            finish()
        }
    }
}