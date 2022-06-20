package fr.ahmedbenali92.welcomee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.ahmedbenali92.welcomee.fragments.AppartRepository
import fr.ahmedbenali92.welcomee.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //charger notre repository
        val repo = AppartRepository()

        //mettre a jour la liste de plante
        repo.updateData{
            //injecter le fragment dans notre boite(fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, CollectionFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }
}