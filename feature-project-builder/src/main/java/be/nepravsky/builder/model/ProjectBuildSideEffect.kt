package be.nepravsky.builder.model

sealed class ProjectBuildSideEffect(){

    data object CLOSE: ProjectBuildSideEffect()
}
