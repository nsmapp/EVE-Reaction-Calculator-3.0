package be.nepravsky.sm.evereactioncalculator.core.mapper

import be.nepravsky.sm.domain.model.settings.Language
import be.nepravsky.sm.domain.model.settings.Settings
import be.nepravsky.sm.domain.model.settings.Systems
import be.nepravsky.sm.evereactioncalculator.core.model.LanguageModel
import be.nepravsky.sm.evereactioncalculator.core.model.SettingsState
import be.nepravsky.sm.evereactioncalculator.core.model.SystemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.koin.core.annotation.Factory

@Factory
class SettingsStateMapper {


    fun map(settings: Settings): SettingsState {

        val languages = mapLanguage(settings.languages)
        val systems = mapSystems(settings.systems)

        return SettingsState(
            id = settings.id,
            langId = settings.langId,
            langName = settings.langName,
            systemId = settings.systemId,
            systemName = settings.systemName,
            regionId = settings.regionId,
            isOfflineMode = settings.isOfflineMode,
            isIgnoreFuelBlock = settings.isIgnoreFuelBlock,
            isProgress = false,
            isShowLanguageDialog = false,
            isShowPriceLocationDialog = false,
            languages = languages,
            systems = systems,

            )
    }

    private fun mapLanguage(languages: List<Language>): ImmutableList<LanguageModel> =
        languages.map { lang ->
            LanguageModel(
                id = lang.id,
                name = lang.name
            )
        }.toPersistentList()

    private fun mapSystems(systems: List<Systems>): ImmutableList<SystemModel> =
        systems.map { system ->
            SystemModel(
                systemId = system.systemId,
                systemName = system.systemName,
                regionId = system.regionId,
                regionName = system.regionName
            )
        }.toPersistentList()
}
