package fr.ahmedbenali92.welcomee.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ahmedbenali92.welcomee.MainActivity
import fr.ahmedbenali92.welcomee.R
import fr.ahmedbenali92.welcomee.adapter.AppartAdapter
import fr.ahmedbenali92.welcomee.adapter.appartitemDecoration
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.appartList

class HomeFragment(
    private val context : MainActivity
) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_home,container,false)


        //recuperer le recyclerview
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView?.adapter = AppartAdapter(context ,appartList,R.layout.item_horizental_plant)


        // recuperer le second recyclerview
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = AppartAdapter(context,appartList ,R.layout.item_vertical_plant)
        verticalRecyclerView?.addItemDecoration(appartitemDecoration())
        return view
    }
}