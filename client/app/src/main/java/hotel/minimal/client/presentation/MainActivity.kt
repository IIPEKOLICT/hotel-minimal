package hotel.minimal.client.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import hotel.minimal.client.R
import hotel.minimal.client.databinding.MainActivityBinding
import hotel.minimal.client.presentation.interfaces.IMainActivity
import hotel.minimal.client.presentation.adapters.FragmentAdapter
import hotel.minimal.client.presentation.viewModels.ConnectionViewModel
import hotel.minimal.client.presentation.animation.PageTransformer
import hotel.minimal.client.presentation.enums.Page
import loshica.vendor.LOSActivity

class MainActivity : LOSActivity(), IMainActivity {

    private var layout: MainActivityBinding? = null
    private val connectionViewModel: ConnectionViewModel by viewModels()

    private var hasConnectionObserver: Observer<Boolean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = MainActivityBinding.inflate(layoutInflater)
        val fragmentAdapter = FragmentAdapter(this)

        with(layout!!) {
            mainPager.adapter = fragmentAdapter
            mainPager.currentItem = 0
            mainPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    supportActionBar?.title = resources.getStringArray(R.array.main_tabs)[position]
                }
            })
            mainPager.setPageTransformer(PageTransformer())

            TabLayoutMediator(mainTab, mainPager) { tab: TabLayout.Tab, position: Int ->
                tab.text = resources.getStringArray(R.array.main_tabs)[position]
            }.attach()

            hasConnectionObserver = Observer {
                if (!it) {
                    Toast.makeText(
                        applicationContext,
                        R.string.connection_error,
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
            }

            setContentView(root)
        }
    }

    override fun onStart() {
        super.onStart()

        hasConnectionObserver?.let { connectionViewModel.hasConnection.observe(this, it) }
        connectionViewModel.checkConnection()
    }

    override fun onStop() {
        super.onStop()
        hasConnectionObserver?.let { connectionViewModel.hasConnection.removeObserver(it) }
    }

    override fun onBackPressed() {
        val pager: ViewPager2? = layout?.mainPager

        if (pager?.currentItem != null && pager.currentItem > 0) {
            pager.setCurrentItem(pager.currentItem - 1, true)
        } else {
            finish()
        }
    }

    override fun swipe(page: Page) {
        layout?.mainPager?.setCurrentItem(page.ordinal, true)
    }
}