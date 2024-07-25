import ext.androidOptions
import ext.getLibs



androidOptions {
    buildFeatures{
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = getLibs().versions.androidxComposeCompiler.get()
    }
}

