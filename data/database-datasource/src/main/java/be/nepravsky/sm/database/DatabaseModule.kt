package be.nepravsky.sm.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import java.io.FileOutputStream
import java.io.InputStream

@Module
@ComponentScan
class DatabaseModule {


    @Single
    fun provideDatabase(sqlDriver: SqlDriver): Database = Database(sqlDriver)


    @Single
    fun provideSqlDriver(context: Context): SqlDriver {
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
            "reactionsv341.db"
        )
    }

    @Single
    fun provideReactionTableDao(database: Database): ReactionTableQueries =
        database.reactionTableQueries
}


