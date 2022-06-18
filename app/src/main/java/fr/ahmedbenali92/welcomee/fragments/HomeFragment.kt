package fr.ahmedbenali92.welcomee.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.ahmedbenali92.welcomee.MainActivity
import fr.ahmedbenali92.welcomee.PlantModel
import fr.ahmedbenali92.welcomee.R
import fr.ahmedbenali92.welcomee.adapter.PlantAdapter
import fr.ahmedbenali92.welcomee.adapter.plantitemDecoration

class HomeFragment(
    private val context : MainActivity
) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_home,container,false)
        //une Liste qui va stocker les plantes
        val plantList = arrayListOf<PlantModel>()
        // Enregistrer une premiere plante
        plantList.add(
            PlantModel("Appartement Londre",
                "En face de big ben","Londre","https://cdn.pixabay.com/photo/2014/07/31/21/41/apartment-406901_960_720.jpg",true)
        )
        // Enregistrer une seconde plante
        plantList.add(
            PlantModel("Appartement Paris",
                "Appartement 3 chambre sur les champs ","Paris","https://cdn.pixabay.com/photo/2016/11/18/17/20/living-room-1835923_960_720.jpg",false)
        )
        // Enregistrer une 3 plante
        plantList.add(
            PlantModel("Appartement Marrakeck",
                "MarMarra Hotel","Maroc","https://cdn.pixabay.com/photo/2016/07/26/18/30/kitchen-1543493__340.jpg",false)
        )
        // Enregistrer une 4 plante
        plantList.add(
            PlantModel("Appartement Bronx",
                "c'est beau","New York","https://cdn.pixabay.com/photo/2019/07/23/22/17/apartment-4358755__340.jpg",false)
        )
        //recuperer le recyclerview
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        if (horizontalRecyclerView != null) {
            horizontalRecyclerView.adapter = PlantAdapter(context ,plantList,R.layout.item_horizental_plant)
        }

        // recuperer le second recyclerview
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = PlantAdapter(context,plantList ,R.layout.item_vertical_plant)
        verticalRecyclerView?.addItemDecoration(plantitemDecoration())
        return view
    }
}