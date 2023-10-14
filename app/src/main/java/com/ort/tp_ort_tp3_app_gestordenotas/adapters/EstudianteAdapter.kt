package com.ort.tp_ort_tp3_app_gestordenotas.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

class EstudianteAdapter(
    var estudiantes: MutableList<Estudiante>,
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<EstudianteAdapter.EstudianteHolder>() {
    class EstudianteHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View;
        init {
            this.view = v;
        }

        fun setNombreCompleto(nombreMateria:String){
            var txtNombreCompleto: TextView = view.findViewById(R.id.txtNombreCompleto);
            txtNombreCompleto.text = nombreMateria;
        }
        fun setDNI(dni:String){
            var txtDNI: TextView = view.findViewById(R.id.txtDNI);
            txtDNI.text = dni;
        }

        fun getCard(): CardView {
            val c: CardView = this.view.findViewById(R.id.cardViewEstudianteList);
            return c;
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_estudiante_item_list, parent, false);
        return EstudianteHolder(view);
    }

    override fun getItemCount(): Int {
        return this.estudiantes.size;
    }

    override fun onBindViewHolder(holder: EstudianteHolder, position: Int) {

        holder.setNombreCompleto(this.estudiantes[position].getPersona().getNombreCompleto());
        holder.setDNI(this.estudiantes[position].getPersona().getDNI());
        holder.getCard().setOnClickListener{
            onClick(position);
        }
    }

}