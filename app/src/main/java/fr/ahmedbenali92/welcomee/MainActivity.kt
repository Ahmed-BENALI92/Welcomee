package fr.ahmedbenali92.welcomee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.ahmedbenali92.welcomee.fragments.AddAppartFragment
import fr.ahmedbenali92.welcomee.fragments.AppartRepository
import fr.ahmedbenali92.welcomee.fragments.HomeFragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this),R.string.home_page_title)
        //importer la bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this),R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this),R.string.Collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_plant_item -> {
                    loadFragment(AddAppartFragment(this),R.string.add_appart_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }


        }


    private fun loadFragment(fragment: Fragment,string: Int) {
        //charger notre repository
        val repo = AppartRepository()
        //actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text= resources.getString(string)
        //mettre a jour la liste de plante
        repo.updateData{
            //injecter le fragment dans notre boite(fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}


