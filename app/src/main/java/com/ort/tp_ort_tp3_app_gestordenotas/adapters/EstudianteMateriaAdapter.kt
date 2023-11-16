package com.ort.tp_ort_tp3_app_gestordenotas.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

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

                estado == EstadoMateria.APROBADA
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteMateriaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_estudiante_materia_item_list, parent, false);
        return EstudianteMateriaHolder(view);
    }

    override fun getItemCount(): Int {
        return this.materias.size;
    }

    private fun mostrarMenuEmergente(
        context: Context,
        view: View,
        nota1: Int,
        nota2: Int
    ){
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.menu_materia_perfil, popupMenu.menu)

        val parcial1Item = popupMenu.menu.findItem(R.id.parcial1)
        val parcial2Item = popupMenu.menu.findItem(R.id.parcial2)

        parcial1Item.title = "Nota Parcial 1: $nota1"
        parcial2Item.title = "Nota Parcial 2: $nota2"

        popupMenu.setOnMenuItemClickListener { menuItem ->

            when(menuItem!!.itemId){
                R.id.parcial1 -> {
                    Toast.makeText(context, "Nota Parcial 1: $nota1", Toast.LENGTH_SHORT).show()
                }
                R.id.parcial2 -> {
                    Toast.makeText(context, "Nota Parcial 2: $nota2", Toast.LENGTH_SHORT).show()
                }
                else -> false
            }
            true
        }
        popupMenu.show()
    }

    override fun onBindViewHolder(holder: EstudianteMateriaHolder, position: Int) {

        holder.setNombreMateria(this.materias[position].getMateria()!!.getNombre());
        holder.setNota(this.materias[position].getNota());
        holder.setEstadoMateria(this.materias[position].getEstado(), this.materias[position].getNota());
        holder.getCard().setOnClickListener{
            onClick(position);
        }

        val db = Firebase.firestore

        val e = this.materias[position].getEstudiante()
        val idPers = e?.getPersona()?.getIdPersona()
        val em: EstudianteMateria? = materias.find { it.getEstudiante()?.getPersona()?.getIdPersona() == idPers }
        val materiaId = em?.getMateria()?.getId()

         db.collection("EstudianteMateria")
            .whereEqualTo("idPersona", idPers)
             .whereEqualTo("idMateria", materiaId)
            .get()
             .addOnSuccessListener { documents ->
                 for (result in documents){
                     val idEm = result.id

                     val em = result.data

                     val documentRef = db.collection("EstudianteMateria").document(idEm).collection("Parciales")

                     documentRef
                         .get()
                         .addOnSuccessListener { documentos ->
                             for (d in documentos) {
                                 val datos = d.data

                                     val nota1 = datos["notaParcial1"].toString()?.toIntOrNull() ?: 0
                                     val nota2 = datos["notaParcial2"].toString()?.toIntOrNull() ?: 0

                                 Log.d("EstudianteMateriaAdapter", "nota 1: $nota1  nota 2: $nota2")

                                     if (nota1 != null && nota2 != null) {
                                         holder.itemView.setOnClickListener {
                                             mostrarMenuEmergente(
                                                 holder.itemView.context,
                                                 holder.itemView,
                                                 nota1,
                                                 nota2
                                             )
                                         }
                                     } else {
                                         Log.e("EstudianteMateriaAdapter", "nota1/nota2 da nulo")
                                     }
                             }
                         }
                         .addOnFailureListener { e ->
                             Log.e("EstudianteMateriaAdapter", "Error al encontrar los parciales", e)
                         }
                 }
             }
             .addOnFailureListener{excepcion ->
                 Log.e("EstudianteMateriaAdapter", "Error al encontrar el estudianteMateria", excepcion)
             }
    }

}