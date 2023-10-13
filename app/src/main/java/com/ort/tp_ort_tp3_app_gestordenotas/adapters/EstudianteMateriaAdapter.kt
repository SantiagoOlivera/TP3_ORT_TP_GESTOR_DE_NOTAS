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
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario

class EstudianteMateriaAdapter(
    var materias: MutableList<EstudianteMateria>,
    var onClick: (Int) -> Unit
) : RecyclerView.Adapter<EstudianteMateriaAdapter.EstudianteMateriaHolder>() {
    class EstudianteMateriaHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View;
        init {
            this.view = v;
        }

        fun setNombreMateria(nombreMateria:String){
            var txtNombreMateria: TextView = view.findViewById(R.id.txtNombreMateria);
            txtNombreMateria.text = nombreMateria;
        }

        fun setNota(nota: Int){
            var txtNota: TextView = view.findViewById(R.id.txtNota);
            txtNota.text = nota.toString();
        }

        fun setEstadoMateria(estado: EstadoMateria){
            var txtEstadoMateria: TextView = view.findViewById(R.id.txtEstadoMateria);
            var text: String = "";
            var color: Int = Color.BLACK;

            if( estado == EstadoMateria.PENDIENTE){
                text = "PENDIENTE";
                color = Color.BLUE;
            }else if( estado == EstadoMateria.EN_PROGRESO){
                text = "EN PROGRESO";
                color = Color.LTGRAY;
            } else if( estado == EstadoMateria.APROBADA){
                text = "APROBADA";
                color = Color.GREEN;
            } else if( estado == EstadoMateria.FINAL){
                text = "FINAL";
                color = Color.RED;
            }

            txtEstadoMateria.text = text;
            txtEstadoMateria.setTextColor(color);
        }

        fun getCard(): CardView {
            val c: CardView = this.view.findViewById(R.id.cardViewEstudianteMateriaList);
            return c;
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteMateriaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_estudiante_materia_item_list, parent, false);
        return EstudianteMateriaHolder(view);
    }

    override fun getItemCount(): Int {
        return this.materias.size;
    }

    override fun onBindViewHolder(holder: EstudianteMateriaHolder, position: Int) {

        holder.setNombreMateria(this.materias[position].getMateria().getNombre());
        holder.setNota(this.materias[position].getNota());
        holder.setEstadoMateria(this.materias[position].getEstado());
        holder.getCard().setOnClickListener{
            onClick(position);
        }
    }

}