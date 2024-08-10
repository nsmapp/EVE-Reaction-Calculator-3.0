package be.nepravsky.sm.domain

import be.nepravsky.sm.domain.utils.DispatcherProvider
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan
class DomainModule{


    @Single
    fun provideCoroutineDispatcher() = DispatcherProvider()
}