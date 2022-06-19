package fr.ahmedbenali92.welcomee

import android.app.Dialog
import fr.ahmedbenali92.welcomee.adapter.AppartAdapter

class AppartPopup(
    private val adapter: AppartAdapter
    ) : Dialog(adapter.context){

}