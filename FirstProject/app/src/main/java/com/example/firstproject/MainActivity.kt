package com.example.firstproject


import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.unit.Constraints
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import android.os.Bundle as Bundle1




class MainActivity : AppCompatActivity() {

    private lateinit var fragmentManager:FragmentManager
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigation:NavigationView
    private lateinit var btn_menu:ImageView

    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation=findViewById(R.id.navigation)
        drawerLayout=findViewById(R.id.drawer_layout)
        actionBarDrawerToggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        btn_menu=findViewById(R.id.back)
        btn_menu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START)
            else
                drawerLayout.openDrawer(GravityCompat.START)
        }

        fragmentManager=supportFragmentManager
        val firstFragment=FirstFragment()
        val secondFragment=SecondFragment()
        val thirdFragment=ThirdFragment()

        setCurrentFragment(firstFragment)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.person->{setCurrentFragment(firstFragment)
                    true}
                R.id.email->{setCurrentFragment(secondFragment)
                    true}
                R.id.phone->{setCurrentFragment(thirdFragment)
                    true}
                else->false
            }
            
        }

        navigation.setNavigationItemSelectedListener {it->
            when(it.itemId){
                R.id.drawer_person->{setCurrentFragment(firstFragment)
                    Toast.makeText(this, "First Element opened", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true}

                R.id.drawer_email->{setCurrentFragment(secondFragment)
                    val snack = Snackbar.make(this.findViewById(R.id.flFragment),"This is a Snackbar Opened",Snackbar.LENGTH_LONG).setAction("DISMISS", View.OnClickListener {
                        Snackbar.make(this.findViewById(R.id.flFragment),"Dissmissed Successfully",Snackbar.LENGTH_LONG).show()
                    })
                    snack.show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true}

                R.id.drawer_phone->{setCurrentFragment(thirdFragment)
                    val dialog= AlertDialog.Builder(this)
                    dialog.setTitle("Alert Dialog Box")
                    dialog.setMessage("Do you want to exit ")
                    dialog.setCancelable(false)
                    dialog.setPositiveButton("Yes"){
                       dialog,which-> finish()
                    }
                    dialog.setNegativeButton("No"){
                        dialog,which->dialog.cancel()
                    }
                    dialog.create().show()

                    true}
                R.id.drawer_lock->{
                    finish()
                    true
                }

                else->false
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }


    private fun setCurrentFragment(fragment: Fragment){
        val transcation=fragmentManager.beginTransaction()
        transcation.replace(R.id.flFragment,fragment)
        transcation.commit()
    }

    }

   




//    private lateinit var btn: Button
//    private lateinit var editEmail:TextInputEditText
//    private lateinit var editPass:TextInputEditText
//        btn = findViewById(R.id.btn1)
//        editEmail=findViewById(R.id.edEmail)
//        editPass=findViewById(R.id.edPass)
//
//
//
//        btn.setOnClickListener {
//            val email= editEmail.text.toString()
//            val pass=editPass.text.toString()
//            if(isValidEmail(email) && isValidPass(pass))
//            {
//                val intent = Intent(this@MainActivity, MainActivity2::class.java)
//                startActivity(intent)
//                Toast.makeText(this@MainActivity, "Login Successfully!!!", Toast.LENGTH_SHORT).show()
//            }
//            else
//                Toast.makeText(this@MainActivity, "Enter Valid Details!!!", Toast.LENGTH_LONG).show()
//        }
//    }
//
//     private fun isValidEmail(email:String):Boolean{
//
//         return Patterns.EMAIL_ADDRESS.matcher(email).matches()}
//
//    private fun isValidPass(pass:String):Boolean{
//        return pass.length>7

//
//
//    fun pattern() {
//        println("enter a number for rows ")
//        val n: Int = Integer.valueOf(readLine())
//
//        for (i in 1..n) {
//            var k = 1
//            for (j in 1 until (2 * n)) {
//                if (j >= n + 1 - i && j <= n - 1 + i) {
//                    if (k == 1)
//                        print("*")
//                    else
//                        print(" ")
//                    k = 1 - k
//                } else
//                    print(" ")
//            }
//            println()
//        }
//    }
//
//
//    fun sort(array: ArrayList<Int>) {
//
//        for (i in 0 until array.size - 1) {
//            for (j in 0 until array.size - 1 - i) {
//                if (array[j] > array[j + 1])
//                    swap(array, j, j + 1)
//            }
//        }
//        for (ele in array)
//            println(ele)
//    }
//
//    fun swap(array: ArrayList<Int>, i: Int, j: Int) {
//        var k: Int = 0
//        k = array[i]
//        array[i] = array[j]
//        array[j] = k
//    }
//
//
//    sealed class Shape {
//        class circle(var radius: Float) : Shape()
//        class rectangle(var length: Int, var breadth: Int) : Shape()
//        class cube(var side: Int) : Shape()
//    }
//
//    fun shape(e: Shape) =
//        when (e) {
//            is Shape.circle -> println(" Circumference is ${3.14 * e.radius * e.radius}")
//            is Shape.rectangle -> println(" reactangle area is ${e.length * e.breadth} ")
//            is Shape.cube -> println(" volume of cube is ${e.side * e.side * e.side}")
//        }
//}
//
//class firstAssignmentClass(fir: Int) {
//    val second: Float = 5.6f
//    var fourth: Char = 'c'
//
//    lateinit var third: String
//
//    val five: String by lazy {
//        println("Welcome to lazy part")
//        "Hello lazy"
//    }
//
//    companion object {
//        var first: Int = 7
//    }
//
//    fun check() {
//        if (this::third.isInitialized)
//            println(" value printed is ${third}")
//        else {
//            third = "Hello"
//            println(third)
//        }
//    }
//}