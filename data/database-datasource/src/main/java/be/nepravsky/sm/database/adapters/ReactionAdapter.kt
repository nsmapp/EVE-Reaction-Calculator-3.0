package be.nepravsky.sm.database.adapters

import app.cash.sqldelight.ColumnAdapter
import be.nepravsky.sm.database.models.ReactionItemEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val reactionItemAdapter = object: ColumnAdapter<List<ReactionItemEntity>, String>{


    override fun decode(databaseValue: String): List<ReactionItemEntity>  = Json.decodeFromString(databaseValue)

    override fun encode(value: List<ReactionItemEntity>): String = Json.encodeToString(value)

}