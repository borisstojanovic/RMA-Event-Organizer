package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekatjun.boris_stojanovic_rn3518.R
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.FragmentAddEventBinding
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract.MainContract
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.AddEventState
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.LocationState
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class AddEventFragment : Fragment(R.layout.fragment_add_event){
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private lateinit var eventTime: Date

    private var _binding: FragmentAddEventBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEventBinding.inflate(inflater, container, false)
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
        initViews()
        initListeners()
    }

    private fun initViews() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.priority,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                binding.spinner.adapter = adapter
            }
        }
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.cities,
            android.R.layout.simple_list_item_1)
        binding.edtLocation.setAdapter(adapter)

    }

    private fun initListeners() {
        binding.datePickerButton.setOnClickListener{
            initDatePicker()
        }

        binding.addEventSaveButton.setOnClickListener{
            val temp: UUID = UUID.randomUUID()
            val eventToAdd = Event(
                id = temp.mostSignificantBits + temp.leastSignificantBits,
                title = binding.edtTitle.text.toString(),
                priority = binding.spinner.selectedItem.toString(),
                location = binding.edtLocation.text.toString(),
                eventTime = eventTime,
                description = binding.edtDescription.text.toString(),
                eventDate = Date.from(this.makeDateFromString(binding.datePickerButton.text.toString()).
                atStartOfDay(ZoneId.systemDefault()).toInstant()),
                url = binding.edtUrl.text.toString()
            )
            mainViewModel.addEvent(eventToAdd)
        }

        binding.btnSetTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.btnSetTime.text = SimpleDateFormat("HH:mm").format(cal.time)
                eventTime = cal.time
                Timber.e(eventTime.toString())
            }
            TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }

        binding.btnLocation.setOnClickListener{
            if(resources.getStringArray(R.array.cities).contains(binding.edtLocation.text.toString())) {
                mainViewModel.fetch(binding.edtLocation.text.toString())
                val timezone = "Europe/" + binding.edtLocation.text.toString()
                mainViewModel.get(timezone)
            }else{
                Toast.makeText(context, "Select a valid location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initObservers() {
        mainViewModel.locationState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        mainViewModel.addDone.observe(viewLifecycleOwner, Observer{
            Timber.e(it.toString())
            renderAddState(it)
        })
    }

    private fun renderAddState(state: AddEventState) {
        when(state) {
            is AddEventState.Success -> {
                Toast.makeText(context, "Event added", Toast.LENGTH_SHORT)
                        .show()
                activity?.finish()
            }
            is AddEventState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun renderState(state: LocationState) {
        when (state) {
            is LocationState.Success -> {
                showLoadingState(false)
                binding.btnLocation.text = state.location.datetime.toString()
            }
            is LocationState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is LocationState.DataFetched -> {
                showLoadingState(false)
            }
            is LocationState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.loadingPb.isVisible = loading
    }

    private fun initDatePicker() {
        val dateSetListener = OnDateSetListener { datePicker, year, month, day ->
            val newMonth = month + 1
            val date = makeDateString(day, newMonth, year)
            binding.datePickerButton.text = date
        }
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        val style: Int = AlertDialog.THEME_HOLO_LIGHT
        val datePickerDialog = context?.let { DatePickerDialog(it, style, dateSetListener, year, month, day) }
        datePickerDialog?.show()
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String? {
        return  getMonthFormat(month) + " " + getDayFormat(day) + " " + year
    }

    private fun makeDateFromString(stringDate: String) : LocalDate{
        val formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return LocalDate.parse(stringDate, formatter)
    }

    private fun getMonthFormat(month: Int): String {
        if (month == 1) return "Jan"
        if (month == 2) return "Feb"
        if (month == 3) return "Mar"
        if (month == 4) return "Apr"
        if (month == 5) return "May"
        if (month == 6) return "Jun"
        if (month == 7) return "Jul"
        if (month == 8) return "Aug"
        if (month == 9) return "Sep"
        if (month == 10) return "Oct"
        if (month == 11) return "Nov"
        return if (month == 12) "Dec" else "Jan"

    }

    private fun getDayFormat(day: Int) : String{
        if(day < 10)return "0$day"
        return "" + day
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

