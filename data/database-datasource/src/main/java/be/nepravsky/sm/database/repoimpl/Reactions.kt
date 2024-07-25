package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.Reaction
import be.nepravsky.sm.domain.model.Blueprint

fun Reaction.toBlueprint() = Blueprint(
    id = id,
    groupId = group_id,
    de = de,
    en = en,
    fr = fr,
    ja = ja,
    ru = ru,
    zh = zh,
    baseTime = base_time,
    runLimit = run_limit,
    materials = materials,
    products = products
)