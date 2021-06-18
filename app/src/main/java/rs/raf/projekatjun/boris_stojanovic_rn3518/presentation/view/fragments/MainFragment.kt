package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import rs.raf.projekatjun.boris_stojanovic_rn3518.R
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.FragmentMainBinding
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.activities.AddEventActivity
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.activities.EventListActivity

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners(){
        binding.listButton.setOnClickListener {
            activity?.let{
                val intent = Intent (it, EventListActivity::class.java)
                it.startActivity(intent)
            }
        }

        binding.addEventButton.setOnClickListener {
            activity?.let{
                val intent = Intent (it, AddEventActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}