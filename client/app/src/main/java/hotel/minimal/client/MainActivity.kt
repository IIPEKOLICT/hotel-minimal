package hotel.minimal.client

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import loshica.client.databinding.MainActivityBinding
import hotel.minimal.client.interfaces.IMainActivity
import hotel.minimal.client.viewModels.CommentViewModel
import hotel.minimal.client.viewModels.RoomViewModel
import hotel.minimal.client.views.adapters.FragmentAdapter
import hotel.minimal.client.viewModels.ConnectionViewModel
import hotel.minimal.client.viewModels.TypeViewModel
import hotel.minimal.client.views.PageTransformer
import loshica.client.R
import loshica.vendor.LOSActivity

class MainActivity : LOSActivity(), IMainActivity {

    private var layout: MainActivityBinding? = null
    private val roomViewModel: RoomViewModel by viewModels()
    private val connectionViewModel: ConnectionViewModel by viewModels()
    private val typeViewModel: TypeViewModel by viewModels()
    private val commentViewModel: CommentViewModel by viewModels()

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

            hasConnectionObserver = Observer { if (!it) finish() }

            setContentView(root)
        }
    }

    override fun onStart() {
        super.onStart()
        hasConnectionObserver?.let { connectionViewModel.hasConnection.observe(this, it) }
        connectionViewModel.checkConnection()
        typeViewModel.loadTypes()
    }

    override fun onStop() {
        super.onStop()
        hasConnectionObserver?.let { connectionViewModel.hasConnection.removeObserver(it) }
    }

    override fun onDestroy() {
        super.onDestroy()

        roomViewModel.onDestroy()
        connectionViewModel.onDestroy()
        typeViewModel.onDestroy()
        commentViewModel.onDestroy()
    }

    override fun onBackPressed() {
        val pager: ViewPager2? = layout?.mainPager

        if (pager?.currentItem != null && pager.currentItem > 0)
            pager.setCurrentItem(0, true)
        else
            finish()
    }

    override fun swipe(position: Int) {
        layout?.mainPager?.setCurrentItem(position, true)
    }
}