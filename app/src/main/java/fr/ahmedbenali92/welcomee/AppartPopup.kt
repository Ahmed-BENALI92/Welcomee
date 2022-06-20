package fr.ahmedbenali92.welcomee

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.ahmedbenali92.welcomee.adapter.AppartAdapter
import fr.ahmedbenali92.welcomee.fragments.AppartRepository

class AppartPopup(
    private val adapter: AppartAdapter,
    private val currentAppart: AppartModel
    ) : Dialog(adapter.context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_apparts_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStartButton()
    }

    private fun upDateStar(button: ImageView){
        if(currentAppart.liked)
        {
            button.setImageResource(R.drawable.ic_star)
        }
        else{
            button.setImageResource(R.drawable.ic_unlike)
        }
    }

    private fun setupStartButton() {

        //Recuper notre starbutton
        val starButton = findViewById<ImageView>(R.id.start_button)
        upDateStar(starButton)
        //interaction
        starButton.setOnClickListener{
            currentAppart.liked = !currentAppart.liked
            val repo = AppartRepository()
            repo.updateAppart(currentAppart)
            upDateStar(starButton)

        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            //supprimer l'appartement de la base de donnée
            val repo = AppartRepository()
            repo.deleteAppart(currentAppart)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            dismiss()
        }
    }

    private fun setupComponents() {
       //actualiser l'image de l'appartement
        val appartImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentAppart.imageurl)).into(appartImage)
        //actualiser le noom de la plante
        findViewById<TextView>(R.id.popup_appart_name).text = currentAppart.name
        //actualiser la description de l'appartement
        findViewById<TextView>(R.id.popu_appart_description_subtitle).text = currentAppart.description
        //actualiser l'adresse de l'appartement
        findViewById<TextView>(R.id.popu_appart_adresse_subtitle).text = currentAppart.adresse
        //actualiser Nom du logeur
        findViewById<TextView>(R.id.popu_appart_name_proprio_subtitle).text = currentAppart.nameproprio
    }
}