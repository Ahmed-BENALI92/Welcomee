package fr.ahmedbenali92.welcomee

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import fr.ahmedbenali92.welcomee.adapter.AppartAdapter

class AppartPopup(
    private val adapter: AppartAdapter
    ) : Dialog(adapter.context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_apparts_details)
    }
}