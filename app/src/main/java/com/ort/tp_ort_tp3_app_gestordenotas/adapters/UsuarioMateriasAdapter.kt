package com.ort.tp_ort_tp3_app_gestordenotas.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

class UsuarioMateriasAdapter(
     var materias: MutableList<EstudianteMateria>,
    var onClick: (Int) -> Unit
): RecyclerView.Adapter<UsuarioMateriasAdapter.UsuarioMateriasHolder>() {

    class UsuarioMateriasHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View;

        init {
            this.view = v
        }

        fun setNombreCompleto(nombreMateria:String?){
            var txtNombreCompleto: TextView = view.findViewById(R.id.txtNombreMateria);
            txtNombreCompleto.text = nombreMateria;
        }

        fun setNotaMateria(nota: Int){
            var notaMateria: TextView = view.findViewById(R.id.txtNota)
            notaMateria.text = nota.toString()
        }

        fun setEstadoMateria(estado: EstadoMateria, nota: Int){
            var txtEstadoMateria: TextView = view.findViewById(R.id.txtEstado);
            var text: String = "";
            var color: Int = Color.BLACK;

            if(nota == 0){
                //estado == EstadoMateria.PENDIENTE
                text = "PENDIENTE";
                color = Color.BLUE;
            }else if( estado == EstadoMateria.EN_PROGRESO){
                text = "EN PROGRESO";
                color = Color.LTGRAY;
            } else if(nota >= 7){
                //estado == EstadoMateria.APROBADA
                text = "APROBADA";
                color = Color.GREEN;
            } else if(nota >= 4 && nota < 7){
                //estado == EstadoMateria.FINAL
                text = "FINAL";
                color = Color.RED;
            }

            txtEstadoMateria.text = text;
            txtEstadoMateria.setTextColor(color);
        }

        fun getCard(): CardView {
            val c: CardView = this.view.findViewById(R.id.cardViewEstudianteList);
            return c;
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

    private fun mostrarMenuEmergente(context: Context, view: View){

        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.menu_materia_perfil, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when(menuItem!!.itemId){
                R.id.parcial1 ->{
                    Toast.makeText(context, menuItem.title, Toast.LENGTH_SHORT).show()
                }
                R.id.parcial2 ->{
                    Toast.makeText(context, menuItem.title, Toast.LENGTH_SHORT).show()
                }
                else -> false
            }
            true
        }
        popupMenu.show()
    }

    override fun onBindViewHolder(holder: UsuarioMateriasHolder, position: Int) {
        holder.setNombreCompleto(this.materias[position].getNombreMateria())
        holder.setNotaMateria(this.materias[position].getNota())
        holder.setEstadoMateria(this.materias[position].getEstado(), this.materias[position].getNota())
        /*holder.getCard().setOnClickListener{
            onClick(position);
        }*/
        holder.itemView.setOnClickListener{
            mostrarMenuEmergente(holder.itemView.context, holder.itemView)
        }


    }
}