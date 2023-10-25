package com.ort.tp_ort_tp3_app_gestordenotas.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

class UsuarioMateriasAdapter(
     var materias: MutableList<EstudianteMateria>
): RecyclerView.Adapter<UsuarioMateriasAdapter.UsuarioMateriasHolder>() {

    class UsuarioMateriasHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View;

        init {
            this.view = v
        }

        fun setNombreCompleto(nombreMateria:String){
            var txtNombreCompleto: TextView = view.findViewById(R.id.txtNombreCompleto);
            txtNombreCompleto.text = nombreMateria;
        }

        fun setNotaMateria(nota: Int){
            var notaMateria: TextView = view.findViewById(R.id.txtNota)
            notaMateria.text = nota.toString()
        }

        fun setEstadoMateria(estado: EstadoMateria){
            var txtEstadoMateria: TextView = view.findViewById(R.id.txtEstado);
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


    }

    fun actualizarDatos(nuevaLista: MutableList<EstudianteMateria>){
        materias.clear()
        materias.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioMateriasHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_estudiante_materia_item_list, parent, false);
        return UsuarioMateriasHolder(view)
    }

    override fun getItemCount(): Int {
        return materias.size;
    }

    override fun onBindViewHolder(holder: UsuarioMateriasHolder, position: Int) {
        holder.setNombreCompleto(this.materias[position].getNombreMateria())
        holder.setNotaMateria(this.materias[position].getNota())
        holder.setEstadoMateria(this.materias[position].getEstado())
    }
}