package sheridan.kaur6200.assignment3.repositories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TitleRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTitleDataRepository(
        repository: TitleRepositoryRoom
    ): TitleDataRepository
}
