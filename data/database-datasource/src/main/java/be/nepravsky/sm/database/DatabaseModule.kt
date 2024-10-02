package be.nepravsky.sm.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import be.nepravsky.sm.database.adapters.reactionItemAdapter
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import java.io.FileOutputStream
import java.io.InputStream

@Module
@ComponentScan
class DatabaseModule {


    @Single
    fun provideDatabase(sqlDriver: SqlDriver): Database = Database(
        driver = sqlDriver,
        reactionAdapter = Reaction.Adapter(reactionItemAdapter, reactionItemAdapter),
    )


    @Single
    fun provideSqlDriver(context: Context): SqlDriver {
        val database = context.getDatabasePath("reactionsv382.db")

        if (!database.exists()) {
            val inputStream = context.assets.open("reactionsv38.db")
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
            "reactionsv382.db"
        )
    }

    @Single
    fun provideReactionDao(database: Database): ReactionTableQueries =
        database.reactionTableQueries

    @Single
    fun provideTypeGroupDao(database: Database): TypeGroupTableQueries =
        database.typeGroupTableQueries

    @Single
    fun provideTypePriceDao(database: Database): TypePriceTableQueries =
        database.typePriceTableQueries

    @Single
    fun provideTypeRepoDao(database: Database): TypeTableQueries =
        database.typeTableQueries

    @Single
    fun provideSettingDao(database: Database): SettingsTableQueries =
        database.settingsTableQueries
}


