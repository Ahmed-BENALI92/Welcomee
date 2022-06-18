package fr.ahmedbenali92.welcomee.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.ahmedbenali92.welcomee.MainActivity
import fr.ahmedbenali92.welcomee.PlantModel
import fr.ahmedbenali92.welcomee.R

class PlantAdapter(
    private val context: MainActivity,
    private val plantList : List<PlantModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){
    //boite pour ranger tous les composant a controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        //image de notre plant
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName: TextView? = view.findViewById(R.id.name_item)
        val plantDescription: TextView? = view.findViewById(R.id.description_item)
        val starIcon : ImageView? = view.findViewById(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les infos de la plante
        val currentPlant = plantList[position]
        //utiliser glide pour récuperer l'image depuis son lien
        Glide.with(context).load(Uri.parse(currentPlant.imageurl)).into(holder.plantImage)

        //mettre a jours le nom de la plant
        holder.plantName?.text = currentPlant.name

        //mettre a jours la description

        holder.plantDescription?.text = currentPlant.description
        //verifier si la plant a était liker ou non
        if(currentPlant.liked)
        {
            holder.starIcon?.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon?.setImageResource(R.drawable.ic_unlike)
        }
    }

    override fun getItemCount(): Int = plantList.size
}