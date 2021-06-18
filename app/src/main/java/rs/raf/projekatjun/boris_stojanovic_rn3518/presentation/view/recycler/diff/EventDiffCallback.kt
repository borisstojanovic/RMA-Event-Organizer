package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.title == newItem.title && oldItem.description == newItem.description &&
                oldItem.eventDate == newItem.eventDate && oldItem.location == newItem.location &&
                oldItem.priority == newItem.priority && oldItem.url == newItem.url
    }

}