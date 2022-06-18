package fr.ahmedbenali92.welcomee


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.ahmedbenali92.welcomee.AppartRepository.Singleton.appartList
import fr.ahmedbenali92.welcomee.AppartRepository.Singleton.databaseRef


//Cette classe va gerer la base de donnée firebase
class AppartRepository {

    //on va utilisé le pattern singleton
    object Singleton{
        // se connecter a la référence "appartement"
        val databaseRef = FirebaseDatabase.getInstance().getReference("appartements")

        //creer un liste pour stocker nos appartement
        val appartList = arrayListOf<AppartModel>()
    }

    fun updateData(){
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
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}