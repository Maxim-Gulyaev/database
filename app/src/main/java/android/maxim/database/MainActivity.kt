package android.maxim.database

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.database.sqlite.SQLiteDatabase
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button.setOnClickListener { createDB()}
    }

    private fun createDB() {
        val db = baseContext.openOrCreateDatabase("app.db", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, UNIQUE(name))")
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31)")

        val query: Cursor = db.rawQuery("SELECT * FROM users", null)
        val textView: TextView = findViewById(R.id.textView)
        textView.setText("")
        while (query.moveToNext()) {
            val name: String = query.getString(0)
            val age: Int = query.getInt(1)
            textView.append("Name: $name. Age: $age. \n")
        }
        query.close()
        db.close()
    }
}