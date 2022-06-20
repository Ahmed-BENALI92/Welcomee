@file:Suppress("DEPRECATION")

package fr.ahmedbenali92.welcomee.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import fr.ahmedbenali92.welcomee.AppartModel
import fr.ahmedbenali92.welcomee.MainActivity
import fr.ahmedbenali92.welcomee.R
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.downloadUri
import java.util.*

class AddAppartFragment(
    private val context: MainActivity
) : Fragment() {

    private var file:Uri? = null
    private var uploadImage: ImageView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_add_appart, container, false)
        //recuperer uploadedImage pour lui associer son composant
        uploadImage = view?.findViewById(R.id.preview_image)
        //recuperer le bouton pour charger l'image
        val pickupImageButton = view?.findViewById<Button>(R.id.upload_button)

        //lorsqu'on click dessus sa ouvre les images du téléphone
        if (pickupImageButton != null) {
            pickupImageButton.setOnClickListener { pickupImage() }
        }

        //recuperer le bouton confirmer
        val confirmButton = view?.findViewById<Button>(R.id.confirm_button)
        confirmButton?.setOnClickListener { sendForm(view) }
        return view
    }

    private fun sendForm(view: View) {
        val repo = AppartRepository()
        repo.uploadImage(file!!){
            val appartName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val appartDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val appartAdresse = view.findViewById<EditText>(R.id.adresse_input).text.toString()
            val appartNomProprio = view.findViewById<EditText>(R.id.name_proprio_input).text.toString()
            val downloadImageUrl = downloadUri

            //creer un nouvelle objet AppartModel
            var appart = AppartModel(
                UUID.randomUUID().toString(),
                appartName,
                appartDescription,
                appartAdresse,
                appartNomProprio,
                downloadImageUrl.toString(),
                false
                    )
            //envoyer notre nouvelle appartement
            repo.insertAppart(appart)

        }
    }

    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 47 && resultCode == Activity.RESULT_OK) {
            //verifier si les données sont nulles
            if (data == null || data.data == null) return

            //recuperer l'image
            file = data.data

            //mettre a jour l'aperçu de l'image
            uploadImage?.setImageURI(file)



        }


    }
}


