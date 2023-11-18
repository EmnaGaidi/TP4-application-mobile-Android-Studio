package com.gl4.tp4

// DetailsActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val stopName = intent.getStringExtra("STOP_NAME")

        // Utilisez une coroutine pour accéder à la base de données sur un thread d'arrière-plan
        GlobalScope.launch(Dispatchers.Main) {
            val arrivalTimes = withContext(Dispatchers.IO) {
                getArrivalTimesForStop(stopName)
            }
            // Mettez à jour l'interface utilisateur sur le thread principal
            displayArrivalTimes(arrivalTimes)
        }
    }

    private suspend fun getArrivalTimesForStop(stopName: String?): List<String> {
        val busScheduleApplication = application as BusScheduleApplication
        val scheduleList = busScheduleApplication.database.Dao().getByStopName(stopName ?: "")
        return scheduleList.map { it.arrival_time.toString() }
    }

    private fun displayArrivalTimes(arrivalTimes: List<String>) {
        // Adapter cette méthode pour afficher les heures d'arrivée dans votre interface utilisateur
        // Utilisez une ListView, un RecyclerView, ou tout autre composant selon vos besoins
    }
}
