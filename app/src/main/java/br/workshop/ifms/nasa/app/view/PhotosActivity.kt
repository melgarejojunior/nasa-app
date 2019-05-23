package br.workshop.ifms.nasa.app.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import br.workshop.ifms.nasa.app.R
import br.workshop.ifms.nasa.app.databinding.ActivityPhotosBinding
import br.workshop.ifms.nasa.app.remote.ApiEndpoints
import br.workshop.ifms.nasa.app.remote.NetworkUtils
import br.workshop.ifms.nasa.app.remote.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class PhotosActivity : AppCompatActivity() {


    private val roverName: String by lazy { intent.getStringExtra(ROVER_EXTRA) }
    private lateinit var binding: ActivityPhotosBinding
    private lateinit var adapter: PhotoAdapter
    private val datePicked = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        binding.calendarButton.setOnClickListener {
            openCalendar()
        }
        adapter = PhotoAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun openCalendar() {
        datePicked.run {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                onDateChosen()
            }
            val dateDialog: Dialog = DatePickerDialog(
                this@PhotosActivity, listener,
                get(Calendar.YEAR),
                get(Calendar.MONTH),
                get(Calendar.DAY_OF_MONTH)
            )
            dateDialog.show()
        }


    }

    private fun onDateChosen() {
        binding.calendarText.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(datePicked.time)
        getData()
    }

    private fun getData() {
        val retrofitClient = NetworkUtils.getRetrofitInstance(API_BASE_URL)
        val endpoint = retrofitClient.create(ApiEndpoints::class.java)
        val callback =
            endpoint.getPhotos(
                roverName,
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(datePicked.time),
                API_KEY
            )

        callback.enqueue(object : Callback<PhotoList> {
            override fun onFailure(call: Call<PhotoList>, t: Throwable) {
                Toast.makeText(this@PhotosActivity, R.string.error_request, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PhotoList>, response: Response<PhotoList>) {
                adapter.setItems(response.body()?.photos ?: listOf())
            }

        })
    }


    companion object {
        private const val API_BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"
        private const val ROVER_EXTRA = "ROVER_EXTRA"
        private const val API_KEY = "DEMO_KEY"

        fun startActivity(context: Context, roverName: String) {
            context.startActivity(
                Intent(context, PhotosActivity::class.java).apply {
                    putExtra(ROVER_EXTRA, roverName)
                }
            )
        }
    }
}