package hotel.minimal.client.presentation.interfaces

import hotel.minimal.client.presentation.enums.Page

interface IViewPagerActivity {
    fun swipe(page: Page)
}