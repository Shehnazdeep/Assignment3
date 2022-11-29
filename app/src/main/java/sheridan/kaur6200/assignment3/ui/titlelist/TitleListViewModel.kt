package sheridan.kaur6200.assignment3.ui.titlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import sheridan.kaur6200.assignment3.model.Title
import sheridan.kaur6200.assignment3.repositories.TitleDataRepository
import javax.inject.Inject


@HiltViewModel
class TitleListViewModel@Inject constructor(
    repository: TitleDataRepository
) : ViewModel() {

    val liveTitleList: LiveData<List<Title>> = repository.getAllTitlesFlow().asLiveData()
}