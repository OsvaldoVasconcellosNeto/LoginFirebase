package br.unisanta.firebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.firebase.ui.auth.AuthUI

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        onSignInResult(res)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.button2).setOnClickListener {
            iniciarLogin()
        }
    }
    //private fun createSignInIntent() {
    //    val providers = arrayListOf(
    //        AuthUI.IdpConfig.EmailBuilder().build()
    //    )
    //
    //    val signInIntent = AuthUI.getInstance()
    //        .createSignInIntentBuilder()
    //        .setAvailableProviders(providers)
    //        .build()
    //
    //    signInLauncher.launch(signInIntent)
    //}
    private fun onSignInResult(
        result: FirebaseAuthUIAuthenticationResult
    ) {
        when (result.resultCode) {
            RESULT_OK -> {
                val user = FirebaseAuth.getInstance().currentUser

                Log.d("FIREBASE", "LOGIN COM SUCESSO!")
                Log.d("FIREBASE", "Email: ${user?.email}")
                val intent = Intent(this, Principal::class.java)
                startActivity(intent)
            }

            RESULT_CANCELED -> {
                val error = result.idpResponse?.error

                if (error == null) {
                    Log.d("FIREBASE", "Login cancelado")
                } else {
                    Log.e("FIREBASE", "Falha na autenticação", error)
                }
            }

            else -> {
                Log.e("FIREBASE", "Resultado inesperado")
            }
        }
    }
    private fun iniciarLogin() {
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .setAlwaysShowSignInMethodScreen(true)
            .build()

        signInLauncher.launch(intent)
    }
}