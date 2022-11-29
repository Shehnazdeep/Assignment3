package sheridan.kaur6200.assignment3

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sheridan.kaur6200.assignment3.model.Title
import sheridan.kaur6200.assignment3.repositories.TitleDataRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: TitleDataRepository
) : ViewModel(){

    companion object{
        private const val TAG = "MainViewModel"
    }

    init {
        Log.d(TAG, "init: the MainViewModel is created")
    }

    fun deleteTitleById(id: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTitleById(id)
        }

    fun saveTitle(title: Title) =
        viewModelScope.launch(Dispatchers.IO) {
            if (title.id == null) {
                repository.insertTitle(title)
            } else {
                repository.updateTitle(title)
            }
        }

    fun deleteAllTitles() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTitles()
        }

}