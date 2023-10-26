package com.ort.tp_ort_tp3_app_gestordenotas.fragments


import android.content.Intent
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.MainActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Administrador
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Rol
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar.UsuarioFragment
import com.ort.tp_ort_tp3_app_gestordenotas.repositories.UsuariosRepository
import java.io.Serializable
import java.util.Date


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
        var USUARIO_INVALIDO: String = "El usuario o password ingresados son incorrectos";
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var v: View
    private lateinit var btnLogin: Button;
    private lateinit var inputUsuarioOEmail: EditText;
    private lateinit var inputPassword: EditText;
    private lateinit var repository: UsuariosRepository;
    private lateinit var snackbar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_login, container, false);
        this.inputUsuarioOEmail = this.v.findViewById(R.id.inputUsuarioOEmail);
        this.inputPassword = this.v.findViewById(R.id.inputPassword);
        this.btnLogin = this.v.findViewById(R.id.btnLogin);


        return this.v;
    }

    override fun onStart(){
        super.onStart()

        btnLogin.setOnClickListener {
            val db = Firebase.firestore
            this.btnLogin.onEditorAction(EditorInfo.IME_ACTION_DONE);

            var u: Usuario? = null;
            var usuarioOEmail: String = this.inputUsuarioOEmail.text.toString();
            var password: String = this.inputPassword.text.toString();

            db.collection("Usuarios")
            .get()
            .addOnSuccessListener { usuarios ->


                //Encontrar usuario de la base
                var document = usuarios.find { u ->
                    ( u.data.get("usuario") == usuarioOEmail || u.data.get("email") == usuarioOEmail ) && u.data.get("password") == password
                }

                if(document != null){

                    var usuario: String = document.data.get("usuario") as String;
                    var email: String = document.data.get("email") as String;
                    var password: String = document.data.get("password") as String;
                    var idPersona: String = document.data.get("idPersona") as String;
                    var rol: Int = ( document.data.get("rol") as Number ).toInt();

                    //Snackbar.make(this.v, "ROL:${rol}, ESTUDIANTE: ${Rol.ESTUDIANTE.ordinal}", Snackbar.LENGTH_LONG).show();
                    if(rol === Rol.ESTUDIANTE.ordinal){
                        u = Estudiante(usuario, email, password, idPersona);
                    }else if (rol === Rol.ADMINISTRADOR.ordinal){
                        u = Administrador(usuario, email, password, idPersona);
                    }

                    //Snackbar.make(this.v, "Usuario: ${usuario}, Email:${email}, Password:${password}, IdPersona:${idPersona}", Snackbar.LENGTH_LONG).show();

                    db.collection("Personas")
                        .document(idPersona)
                        .get()
                        .addOnSuccessListener { document ->

                            var dni: String = document.data?.get("dni") as String;
                            var nombre: String = document.data?.get("nombre") as String;
                            var apellido: String = document.data?.get("apellido") as String;
                            //var fechaDeNacimiento: Date = Date(2023,10,21);
                            var fechaDeNacimiento: Date = (document.data?.get("fechaDeNacimiento") as Timestamp).toDate()

                            val p: Persona = Persona(dni, nombre, apellido, fechaDeNacimiento);

                            Snackbar.make(this.v, "RESULT:${nombre}", Snackbar.LENGTH_LONG).show();

                            u?.setPersona(p);
                            this.enterApp(u);
                        }
                }

                val intent = Intent(activity, UsuarioFragment::class.java)
                intent.putExtra("nombreUsuarioOEmail", usuarioOEmail)
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
        };
    }

    private fun enterApp(u: Usuario?) {

        if(u is Estudiante){
            //Pantalla para estudiantes
            val intent = Intent(parentFragment?.activity as MainActivity, EstudianteActivity::class.java)

            intent.putExtra("usuario", u.getUsuario());
            intent.putExtra("email", u.getEmail());
            intent.putExtra("password", u.getPassword());
            intent.putExtra("idPersona", u.getIdPersona());

            Snackbar.make(this.v, "${u.getUsuario()}", Snackbar.LENGTH_LONG).show();

            startActivity(intent);
            //val action = LoginFragmentDirections.actionLoginFragmentToEstudianteActivity(u);
            //findNavController().navigate(action);
        }else if(u is Administrador){
            //Patalla para administrador
            val action = LoginFragmentDirections.actionLoginFragmentToAdministradorActivity(u);
            findNavController().navigate(action);
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}