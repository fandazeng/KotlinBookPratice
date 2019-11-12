package zeng.fanda.com.kotlinpratice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var view: TextView

    var testParam: Int? = 10

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = findViewById(R.id.tv_pratice)
        view.setOnClickListener { startNewActivity<MainActivity>() }
        printId(view)
    }

    fun printId(view: View?) {
        showMessage(view?.id.toString())
    }

    fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : Activity> Context.startNewActivity() {
        startActivity(Intent(this, T::class.java))
    }

}
