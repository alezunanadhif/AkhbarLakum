package com.nadhif.akhbar_lakum.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nadhif.akhbar_lakum.ui.home.AboutQuranFragment
import com.nadhif.akhbar_lakum.ui.home.AlJazeeraFragment
import com.nadhif.akhbar_lakum.ui.home.CommonFragment
import com.nadhif.akhbar_lakum.ui.home.WarningForMuslimFragment
import com.nadhif.akhbar_lakum.ui.home.WorldFragment

class SectionPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> WorldFragment()
            1 -> CommonFragment()
            2 -> AboutQuranFragment()
            3 -> AlJazeeraFragment()
            4 -> WarningForMuslimFragment()
            else -> CommonFragment()
        }
    }
}