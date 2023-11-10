package com.ort.tp_ort_tp3_app_gestordenotas.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria.EstudianteMateriaListFragment

class ViewPagerAdapter(
    fragmentActivity: Fragment, private val data: ArrayList<String>, private val e: Estudiante) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return this.data.size;
    }
    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = EstudianteMateriaListFragment();
        val bundle = Bundle();
        bundle.putString("key", data[position]);
        bundle.putParcelable("estudiante", e);
        fragment.arguments = bundle;
        return fragment;
    }

}