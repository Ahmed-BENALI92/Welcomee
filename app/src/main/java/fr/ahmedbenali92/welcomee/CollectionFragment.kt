package fr.ahmedbenali92.welcomee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ahmedbenali92.welcomee.adapter.AppartAdapter
import fr.ahmedbenali92.welcomee.adapter.appartitemDecoration
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.appartList

class CollectionFragment(
    private val context: MainActivity
) : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection,container,false)

        //recuper ma recyclerview

        val collectionRecyclerView = view?.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView?.adapter = AppartAdapter(context,appartList.filter { it.liked },R.layout.item_vertical_plant)
        collectionRecyclerView?.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView?.addItemDecoration(appartitemDecoration())

        return view
    }
}