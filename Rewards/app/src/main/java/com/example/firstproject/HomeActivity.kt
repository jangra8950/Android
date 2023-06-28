package com.example.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottom:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom=findViewById(R.id.bnb)
        val firstFragment=HomeFragment()
        val secondFragment=PersonFragment()
        val thirdFragment=SettingsFragment()

        setCurrentFragment(firstFragment)

        bottom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.person->{
                    setCurrentFragment(secondFragment)
                    true
                }
                R.id.home->{
                    setCurrentFragment(firstFragment)
                    true
                }
                R.id.settings->{
                    setCurrentFragment(thirdFragment)
                    true
                }
                else->false
            }

        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
         supportFragmentManager.beginTransaction().apply {
             replace(R.id.frame,fragment)
             commit()
         }
    }
}