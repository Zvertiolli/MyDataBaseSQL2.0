package space.alehandrozed.mydatabasesql20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import space.alehandrozed.mydatabasesql20.db.MyDbManager

class MainActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        val list = myDbManager.readDbData()
        for (item in list) {
            tvTest.append(item)
            tvTest.append("\n")
        }
    }

    fun onClick(view: View) {
        tvTest.text = ""
        myDbManager.openDb()
        myDbManager.insertToDb(edTitle.text.toString(), edContext.text.toString())
        val list = myDbManager.readDbData()
        for (item in list) {
            tvTest.append(item)
            tvTest.append("\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeBd()
    }
}


