package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekatjun.boris_stojanovic_rn3518.R
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.ActivityAddEventBinding
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract.MainContract
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.fragments.AddEventFragment
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel.MainViewModel

class AddEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEventBinding

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
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
        supportFragmentManager.beginTransaction()
            .add(R.id.fcvAddEventActivity, AddEventFragment())
            .commit()
    }
}
