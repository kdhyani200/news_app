package kd.dhyani.newsapp.presentation.onboarding.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.AppEntryUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUsecases: AppEntryUseCases
): ViewModel(){

    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }

            else -> {}
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUsecases.saveAppEntry()
        }
    }
}