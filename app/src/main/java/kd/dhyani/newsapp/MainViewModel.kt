package kd.dhyani.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.AppEntryUseCases
import kd.dhyani.newsapp.presentation.onboarding.nvgraph.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel(){

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                startDestination = Route.NewsNavigation.route
            }else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}