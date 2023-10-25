package com.ort.tp_ort_tp3_app_gestordenotas.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Administrador
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar.UsuarioFragment
import com.ort.tp_ort_tp3_app_gestordenotas.repositories.UsuariosRepository

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

            this.btnLogin.onEditorAction(EditorInfo.IME_ACTION_DONE);

            var usuarioOEmail: String = this.inputUsuarioOEmail.text.toString();
            var password: String = this.inputPassword.text.toString();

            var u: Usuario? = UsuariosRepository.login(usuarioOEmail, password);

            if(u != null){
                if(u is Estudiante){
                    //Pantalla para estudiantes
                    val action = LoginFragmentDirections.actionLoginFragmentToEstudianteActivity(u);
                    findNavController().navigate(action);
                }else if(u is Administrador){
                    //Patalla para administrador
                    val action = LoginFragmentDirections.actionLoginFragmentToAdministradorActivity(u);
                    findNavController().navigate(action);
                }

                val intent = Intent(activity, UsuarioFragment::class.java)
                intent.putExtra("nombreUsuarioOEmail", usuarioOEmail)
                startActivity(intent)
            }else{
                //this.v.hideKeyboard();
                Snackbar.make(this.v, USUARIO_INVALIDO, Snackbar.LENGTH_LONG).show();
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}