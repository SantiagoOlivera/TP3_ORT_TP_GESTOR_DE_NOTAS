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
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.AdministradorActivity
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.MainActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Administrador
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Rol
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.repositories.UsuariosRepository
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch



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
    private lateinit var factory: Factory;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.factory = Factory();
        this.v = inflater.inflate(R.layout.fragment_login, container, false);
        this.inputUsuarioOEmail = this.v.findViewById(R.id.inputUsuarioOEmail);
        this.inputPassword = this.v.findViewById(R.id.inputPassword);
        this.btnLogin = this.v.findViewById(R.id.btnLogin);


        return this.v;
    }

    override fun onStart(){
        super.onStart()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener {

            var usuarioOEmail: String = this.inputUsuarioOEmail.text.toString();
            var password: String = this.inputPassword.text.toString();
            this.btnLogin.onEditorAction(EditorInfo.IME_ACTION_DONE);


            //Snackbar.make(this.v, "Usuario: ${usuario}, Email:${email}, Password:${password}, IdPersona:${idPersona}", Snackbar.LENGTH_LONG).show();


            val parentJob = Job();
            val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + parentJob);
            scope.launch {
                var u: Usuario? = factory.getUsuario(usuarioOEmail, password);
                if(u!= null){
                    enterApp(u);

                }
            }


        };
    }

    private fun enterApp(u: Usuario?) {
        if(u != null){

            var intent: Intent? = null;

            if(u is Estudiante){

                //Pantalla para estudiantes
                intent = Intent(parentFragment?.activity as MainActivity, EstudianteActivity::class.java)

                intent.putExtra("usuario", u.getUsuario());
                intent.putExtra("email", u.getEmail());
                intent.putExtra("password", u.getPassword());
                intent.putExtra("idPersona", u.getIdPersona());

                Snackbar.make(this.v, "${u.getUsuario()}", Snackbar.LENGTH_LONG).show();

                //val action = LoginFragmentDirections.actionLoginFragmentToEstudianteActivity(u);
                //findNavController().navigate(action);
            }else if(u is Administrador){

                //Patalla para administrador
                intent = Intent(parentFragment?.activity as MainActivity, AdministradorActivity::class.java)

                intent.putExtra("usuario", u.getUsuario());
                intent.putExtra("email", u.getEmail());
                intent.putExtra("password", u.getPassword());
                intent.putExtra("idPersona", u.getIdPersona());


                //val action = LoginFragmentDirections.actionLoginFragmentToAdministradorActivity(u);
                //findNavController().navigate(action);
            }

            if(intent != null){
                startActivity(intent);
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel

    }

}