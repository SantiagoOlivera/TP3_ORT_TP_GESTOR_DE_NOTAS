package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.ViewPagerAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria

class TabsEstudianteMateriaListFragment : Fragment() {

    private lateinit var v: View
    private lateinit var adapter: ViewPagerAdapter;
    private lateinit var tabData: ArrayList<String>;        //arrayOf("A", "B", "C", "D");
    private lateinit var tabTitle: ArrayList<String>;       //arrayOf("Tab 1", "Tab 2", "Tab 3", "Tab 4");

    companion object {
        fun newInstance() = TabsEstudianteMateriaListFragment()
    }

    private lateinit var viewModel: TabsEstudianteMateriaListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_tabs_estudiante_materia_list, container, false);

        this.initTabs();

        return this.v;
    }

    private fun initTabs() {

        var tabsLayout: TabLayout? = this.v?.findViewById(R.id.tabsLayout);
        var viewPager: ViewPager2? = this.v?.findViewById(R.id.viewPager);


        //Inicia tabs data parametro para filtrar lista y titulo
        this.tabTitle = ArrayList<String>();
        this.tabData = ArrayList<String>();
        for(am in AnioMateria.entries){
            this.tabTitle.add(am.getText());
            this.tabData.add(am.ordinal.toString());
        }

        this.adapter = ViewPagerAdapter(this, this.tabData);

        if (viewPager != null) {
            viewPager.adapter = this.adapter;
        };

        if (tabsLayout != null && viewPager != null) {
            TabLayoutMediator(tabsLayout, viewPager, { tab, position ->
                tab.text = this.tabTitle.get(position);
            }).attach();
        };
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TabsEstudianteMateriaListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}