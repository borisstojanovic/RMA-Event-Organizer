package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.LayoutItemEventBinding
import java.text.SimpleDateFormat
import java.util.*

class EventViewHolder(private val itemBinding: LayoutItemEventBinding, private val deleteListener: (Event) -> Unit, private val shareListener: (Event) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(event: Event) {
        itemBinding.titleTv.text = event.title
        itemBinding.descriptionTv.text = event.description
        itemBinding.timeTv.text = SimpleDateFormat("HH:mm").format(event.eventDate)
        itemBinding.dateTv.text = SimpleDateFormat("dd/MMM/yyyy").format(event.eventDate)
        itemBinding.urlTv.text = event.url
        itemBinding.btnDelete.setOnClickListener{
            deleteListener(event)
        }
        itemBinding.btnShare.setOnClickListener{
            shareListener(event)
        }
    }

}