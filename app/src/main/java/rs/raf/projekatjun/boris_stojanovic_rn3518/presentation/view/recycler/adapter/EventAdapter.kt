package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.recycler.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.LayoutItemEventBinding
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.recycler.diff.EventDiffCallback
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.recycler.viewholder.EventViewHolder

class EventAdapter(private val deleteListener: (Event) -> Unit, private val shareListener: (Event) -> Unit) : ListAdapter<Event, EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemBinding = LayoutItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemBinding, deleteListener, shareListener)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
        if(event.priority == "High") {
            holder.itemView.setBackgroundColor(Color.parseColor("#77FF0000"))
        }else if(event.priority == "Medium") {
            holder.itemView.setBackgroundColor(Color.parseColor("#7700FF00"))
        }else {
            holder.itemView.setBackgroundColor(Color.parseColor("#33FFFFFF"))
        }
    }

}