package com.toppr.sampletv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class TabFragment(private val tabControlInterface: TabControl) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSelectedTabIndex(0)

        (view.findViewById(R.id.tab_0) as ImageView).setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                updateSelectedTabIndex(0)
            }
        }

        (view.findViewById(R.id.tab_1) as ImageView).setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                updateSelectedTabIndex(1)
            }
        }

        (view.findViewById(R.id.tab_2) as ImageView).setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                updateSelectedTabIndex(2)
            }
        }

        (view.findViewById(R.id.tab_3) as ImageView).setOnFocusChangeListener { view, hasFocus ->
            if(hasFocus){
                updateSelectedTabIndex(3)
            }
        }
    }

    private fun updateSelectedTabIndex(currentSelectedTabIndex : Int){
        tabControlInterface.selectedTabIndex(currentSelectedTabIndex)
    }

    companion object{
        interface TabControl{
            fun selectedTabIndex(tabIndex : Int)
        }
    }
}