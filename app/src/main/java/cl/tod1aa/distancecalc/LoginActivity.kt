package cl.tod1aa.distancecalc

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loginButton = findViewById<Button>(R.id.loginButton)
        val register = findViewById<Button>(R.id.loginRegisterButton)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)


        register.setOnClickListener {
            val intent: Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        auth = Firebase.auth
        fun login(email:String, password:String){
            loginButton.text = "Ingresando..."
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        loginButton.text = "Ingresar"
                        //Abre el menu activity en caso de que se inicie sesión correctamente
                        val intent: Intent = Intent(this, MenuActivity::class.java)
                        startActivity(intent)
                    }else{
                        loginButton.text = "Ingresar"
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Error en la autenticación",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
        loginButton.setOnClickListener{
            //TODO: Validar inputs
            login(email.text.toString(), password.text.toString() )
        }
        val launchTemperature = findViewById<Button>(R.id.launchTemperature)
        launchTemperature.setOnClickListener {
            val intent: Intent = Intent(this, TemperatureActivity::class.java)
            startActivity(intent)
        }
    }
}