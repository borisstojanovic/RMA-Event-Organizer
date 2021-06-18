package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekatjun.boris_stojanovic_rn3518.R
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.ActivityEventListBinding
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract.EventContract
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.fragments.EventListFragment
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel.EventListViewModel

class EventListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventListBinding

    private val eventViewModel: EventContract.ViewModel by viewModel<EventListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onBackPressed() {
        val mgr = supportFragmentManager
        if (mgr.backStackEntryCount == 0) {
            super.onBackPressed();
        } else {
            mgr.popBackStack();
        }
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        eventViewModel.getAll()
        supportFragmentManager.beginTransaction()
            .add(R.id.fcvEventListActivity, EventListFragment())
            .commit()
    }
}
