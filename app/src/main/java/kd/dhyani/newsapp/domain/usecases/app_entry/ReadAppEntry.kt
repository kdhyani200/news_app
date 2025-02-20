package kd.dhyani.newsapp.domain.manager.usecases.app_entry

import kd.dhyani.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke() : Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}