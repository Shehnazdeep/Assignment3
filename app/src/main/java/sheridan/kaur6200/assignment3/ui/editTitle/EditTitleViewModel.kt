package sheridan.kaur6200.assignment3.ui.editTitle

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sheridan.kaur6200.assignment3.model.Title
import sheridan.kaur6200.assignment3.repositories.TitleDataRepository
import java.util.*
import javax.inject.Inject


@HiltViewModel
class EditTitleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TitleDataRepository,
) : ViewModel() {

    private val titleId: String? = savedStateHandle["titleId"]

    private val _liveUiState = MutableLiveData(getInitUiSate())
    val liveUiState: LiveData<EditTitleUiState> = _liveUiState


    init{
        if(titleId is String){
            viewModelScope.launch(Dispatchers.IO) {
                val title = repository.getTitleById(titleId)
                val uiState = _liveUiState.value!!
                val newUiState = uiState.copy(
                    isTitleLoaded = false,
                    dueDate = title.dueDate,
                    initialTitle = title
                )
                _liveUiState.postValue(newUiState)
            }
        }
    }

    private fun getInitUiSate(): EditTitleUiState{
        val title = Title()
        return EditTitleUiState(
            isTitleLoaded = false,
            initialTitle = title,
                    dueDate = title.dueDate
        )
    }

    fun setTitleLoaded(){
        val uiState = _liveUiState.value!!
        _liveUiState.value = uiState.copy(isTitleLoaded = true)
    }
    fun setDate(dueDate: Date) {
        val uiState = _liveUiState.value!!
        _liveUiState.value = uiState.copy(dueDate = dueDate)
    }
    val dueDate: Date
        get() = liveUiState.value!!.dueDate
}