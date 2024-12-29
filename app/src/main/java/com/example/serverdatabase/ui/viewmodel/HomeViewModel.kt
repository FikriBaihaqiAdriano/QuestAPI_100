package com.example.serverdatabase.ui.viewmodel

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdatabase.model.Mahasiswa
import com.example.serverdatabase.repository.mahasiswaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState {
    data class Success(val mahasiswa: List<Mahasiswa>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeViewModel(private val mhs: mahasiswaRepository) : ViewModel() {
    var mhsUIState: HomeUiState by mutableStateOf(HomeUiState.Loading)
    private set


    init {
        getMhs()
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMhs() {
        viewModelScope.launch {
            mhsUIState = HomeUiState.Loading
            mhsUIState = try {
            HomeUiState.Success(mhs.getmahasiswa())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteMhs(nim: String) {
        viewModelScope.launch {
            try {
            mhs.deletemahasiswa(nim)
            getMhs() // Memperbarui daftar mahasiswa setelah delete
            } catch (e: IOException){
                HomeUiState.Error
            } catch (e:HttpException){
                HomeUiState.Error
            }
        }
    }
}
