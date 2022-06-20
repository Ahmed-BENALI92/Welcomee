package fr.ahmedbenali92.welcomee.adapter

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.ahmedbenali92.welcomee.MainActivity
import fr.ahmedbenali92.welcomee.AppartModel
import fr.ahmedbenali92.welcomee.AppartPopup
import fr.ahmedbenali92.welcomee.R
import fr.ahmedbenali92.welcomee.fragments.AppartRepository
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.appartList

class AppartAdapter(
    val context: MainActivity,
    private val appartListe : List<AppartModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<AppartAdapter.ViewHolder>(){
    //boite pour ranger tous les composant a controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        //image de notre plant
        val appartImage = view.findViewById<ImageView>(R.id.image_item)
        val appartName: TextView? = view.findViewById(R.id.name_item)
        val appartDescription: TextView? = view.findViewById(R.id.description_item)
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
        Log.d(TAG, "ahmed la Value is: " + appartList.size)
        val currentAppart = appartList[position]

        //recuperer le repository
        val repo = AppartRepository()

        //utiliser glide pour récuperer l'image depuis son lien
        Glide.with(context).load(Uri.parse(currentAppart.imageurl)).into(holder.appartImage)

        //mettre a jours le nom de la plant
        holder.appartName?.text = currentAppart.name

        //mettre a jours la description

        holder.appartDescription?.text = currentAppart.description
        //verifier si la plant a était liker ou non
        if(currentAppart.liked)
        {
            holder.starIcon?.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon?.setImageResource(R.drawable.ic_unlike)
        }
        // rajouter une interraction sur l'etoile
        holder.starIcon?.setOnClickListener{
            //inverse si le bouton est like ou non
            currentAppart.liked = !currentAppart.liked
            //mettre a jour l'objet appartement
            repo.updateAppart(currentAppart)
        }

        // nouvelle intéraction lors d'un clic sur un appart
        holder.itemView.setOnClickListener {
            //afficher la popup
            AppartPopup(this,currentAppart).show()
        }
    }


    override fun getItemCount(): Int = appartListe.size
}