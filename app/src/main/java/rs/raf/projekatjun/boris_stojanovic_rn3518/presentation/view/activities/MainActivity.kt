package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekatjun.boris_stojanovic_rn3518.R
import rs.raf.projekatjun.boris_stojanovic_rn3518.databinding.ActivityMainBinding
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
            .add(R.id.fcvMainActivity, MainFragment())
            .commit()
    }
}
