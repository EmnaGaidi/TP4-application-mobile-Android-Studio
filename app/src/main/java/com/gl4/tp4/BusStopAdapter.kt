package com.gl4.tp4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule

class BusStopAdapter(private val schedules: List<Schedule>,private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<BusStopAdapter.BusStopViewHolder>() {

    class BusStopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopNameTextView: TextView = itemView.findViewById(R.id.stop_name)
        val arrivalTimeTextView: TextView = itemView.findViewById(R.id.arrival_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return BusStopViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val currentSchedule = schedules[position]
        holder.stopNameTextView.text = currentSchedule.stop_name
        holder.arrivalTimeTextView.text = currentSchedule.arrival_time.toString()
        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentSchedule.stop_name)
        }
    }

    override fun getItemCount(): Int {
        return schedules.size
    }
}
