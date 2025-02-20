package kd.dhyani.newsapp.domain.manager.usecases.app_entry

import kd.dhyani.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}