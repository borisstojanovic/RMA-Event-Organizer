package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekatjun.boris_stojanovic_rn3518.R
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.FragmentEventListBinding
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract.EventContract
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.recycler.adapter.EventAdapter
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.EventsState
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel.EventListViewModel
import timber.log.Timber
import java.text.SimpleDateFormat

class EventListFragment : Fragment(R.layout.fragment_event_list) {

    private val eventViewModel: EventContract.ViewModel by sharedViewModel<EventListViewModel>()

    private var _binding: FragmentEventListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = EventAdapter ({ item ->
            eventViewModel.remove(item)
        }, {item ->
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, item.title + "\n" + SimpleDateFormat("dd/MMM/yyyy").format(item.eventDate) + "\n" + SimpleDateFormat("HH:mm").format(item.eventDate))
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        })
        binding.listRv.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(binding.listRv.context, resources.configuration.orientation);
        binding.listRv.addItemDecoration(dividerItemDecoration);
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        eventViewModel.eventsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        //eventViewModel.getAll()
    }

    private fun renderState(state: EventsState) {
        when (state) {
            is EventsState.Success -> {
                adapter.submitList(state.events)
            }
            is EventsState.Error -> {
                //error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
