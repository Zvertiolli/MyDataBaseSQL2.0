package space.alehandrozed.mydatabasesql20.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(val context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(title: String, content: String) {
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }

    fun readDbData(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(
            MyDBNameClass.TABLE_NAME, null, null, null, null, null, null, null
        )
        with(cursor){
            while (this!!.moveToNext()){
                val  itemTitle = getString(getColumnIndexOrThrow(MyDBNameClass.COLUMN_NAME_TITLE))
                dataList.add(itemTitle.toString())
            }
        }
        cursor?.close()
        return dataList
    }
    fun closeBd(){
        myDbHelper.close()
    }
}