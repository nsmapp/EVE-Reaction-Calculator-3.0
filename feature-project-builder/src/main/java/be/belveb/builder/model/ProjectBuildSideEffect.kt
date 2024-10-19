package be.belveb.builder.model

sealed class ProjectBuildSideEffect(){

    data object CLOSE: ProjectBuildSideEffect()
}
