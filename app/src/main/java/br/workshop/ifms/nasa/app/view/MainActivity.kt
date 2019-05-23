package br.workshop.ifms.nasa.app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.workshop.ifms.nasa.app.R
import br.workshop.ifms.nasa.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonCuriosity.setOnClickListener {
            goToCuriosityPhotos()
        }
        binding.buttonOpportunity.setOnClickListener {
            goToOpportunityPhotos()
        }
        binding.buttonSpirit.setOnClickListener {
            goToSpiritPhotos()
        }
    }

    private fun goToCuriosityPhotos() {
        PhotosActivity.startActivity(this, "curiosity")
    }

    private fun goToOpportunityPhotos() {
        PhotosActivity.startActivity(this, "opportunity")
    }

    private fun goToSpiritPhotos() {
        PhotosActivity.startActivity(this, "spirit")
    }
}
