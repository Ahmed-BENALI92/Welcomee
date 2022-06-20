package fr.ahmedbenali92.welcomee.fragments


import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.ahmedbenali92.welcomee.AppartModel
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.appartList
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.databaseRef
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.downloadUri
import fr.ahmedbenali92.welcomee.fragments.AppartRepository.Singleton.storageReference
import java.net.URI
import java.util.*


//Cette classe va gerer la base de donnée firebase
class AppartRepository {

    //on va utilisé le pattern singleton
    object Singleton {

        // donner le lien pour acceder au bucket
        private val BUCKET_URL: String ="gs://welcomee-9bd22.appspot.com"

        // se connecter a notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // se connecter a la référence "appartement"
        val database = FirebaseDatabase.getInstance("https://welcomee-9bd22-default-rtdb.firebaseio.com/")
        val databaseRef = database.getReference("appartements")


        //creer un liste pour stocker nos appartement
        val appartList = arrayListOf<AppartModel>()

        //contenir le lien de l'image courante
        var downloadUri :Uri? = null
    }

    fun updateData(callback : () -> Unit){
        //récuperer les données et les injecter dans notre liste appartement
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les ancien appartement pour recréer une liste
                appartList.clear()
                //recolter la liste
                for(ds in snapshot.children)
                {
                    //Construire un objet appartement
                    val appartement = ds.getValue(AppartModel::class.java)
                    //vérifier que lappartement n'est pas nul
                    if(appartement != null)
                    {
                        //ajouter l'appartement a notre liste
                        appartList.add(appartement)

                    }
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //val value = snapshot.getValue()
                    //Log.d(TAG, "Ahmed Value is: " + appartList.size)
                }
                //actionner le callback
                callback()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    //créer une fonction qui va envoyer des fichiers sur le storage
    fun uploadImage(file: Uri, callback: () -> Unit) {
        // verifier que ce fichier n'est pas null
        if(file != null)
        {
            val fileName = UUID.randomUUID().toString() +".jpg"//on crée le nom de fichier aléatoirement
            val ref= storageReference.child(fileName)//Où on veut le ranger
            val uploadTask = ref.putFile(file)// associer le contenue a soumettre

            //demarrer la tache d'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot,Task<Uri>>{ task ->
                //si il y a eu un problème lors de l'envoi du fichier
                if(!task.isSuccessful)
                {
                    task.exception?.let {throw it}
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->

                // vérifier si tout a bien fonctionné
                if(task.isSuccessful)
                {
                    //recuperer l'image
                     downloadUri = task.result
                    callback()
                }
            }
        }
    }

    //mettre à jour un objet appart en bdd
    fun updateAppart(appart: AppartModel){
        databaseRef.child(appart.id).setValue(appart)
    }

    //inserer un nouvelle appartement

    fun insertAppart(appart: AppartModel) = databaseRef.child(appart.id).setValue(appart)

    //supprimer une appart de la base de donnée
    fun deleteAppart(appart: AppartModel) = databaseRef.child(appart.id).removeValue()
}