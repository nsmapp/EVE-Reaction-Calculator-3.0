package be.nepravsky.sm.evereactioncalculator.mapper

import be.nepravsky.sm.domain.model.settings.Language
import be.nepravsky.sm.domain.model.settings.Settings
import be.nepravsky.sm.evereactioncalculator.model.LanguageModel
import be.nepravsky.sm.evereactioncalculator.model.SettingsState
import org.koin.core.annotation.Factory

@Factory
class SettingsStateMapper {

    private fun mapLanguage(languages: List<Language>): List<LanguageModel>  =
        languages.map { lang ->
            LanguageModel(
                id = lang.id,
                name = lang.name
            )
        }

    fun map(settings: Settings): SettingsState {

        val languages = mapLanguage(settings.languages)

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
            languages = languages,
        )
    }
}
