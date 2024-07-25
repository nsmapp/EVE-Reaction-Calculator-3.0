package be.nepravsky.sm.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import java.io.FileOutputStream
import java.io.InputStream


fun getDriver(context: Context): SqlDriver {
    val database = context.getDatabasePath("reactionsv341.db")

    if (!database.exists()) {
        val inputStream = context.assets.open("reactionsv34.db")
        val outputStream = FileOutputStream(database.absolutePath)

        inputStream.use { input: InputStream ->
            outputStream.use { output: FileOutputStream ->
                input.copyTo(output)
            }
        }
    }

    return AndroidSqliteDriver(
        Database.Schema,
        context,
        "reactionsv341.db")
}