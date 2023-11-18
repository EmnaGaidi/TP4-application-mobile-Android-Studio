package com.gl4.tp4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var busStopAdapter: BusStopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Utiliser les coroutines pour accéder à la base de données sur un thread d'arrière-plan
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                val busScheduleApplication = application as BusScheduleApplication
                busScheduleApplication.database.Dao().getAll()
            }

            // Mettez à jour l'adaptateur avec les données de la base de données
            if (result != null) {
                busStopAdapter = BusStopAdapter(result) { stop_name ->
                    // Ouvrir DetailsActivity avec le nom de l'arrêt sélectionné
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra("STOP_NAME", stop_name)
                    startActivity(intent)
                }
                recyclerView.adapter = busStopAdapter
            }
        }
    }
}