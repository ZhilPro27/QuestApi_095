package com.example.questapi.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi.modeldata.DataSiswa
import com.example.questapi.repositori.RepositoryDataSiswa
import com.example.questapi.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

sealed interface StatusUiDetail{
    data class Succes(val satusiswa: DataSiswa): StatusUiDetail
    object Error : StatusUiDetail
    object Loading : StatusUiDetail
}
class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
): ViewModel() {
    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])
    var statusUiDetail: StatusUiDetail by mutableStateOf(StatusUiDetail.Loading)
        private set
    init {
        getSatuSiswa()
    }

    fun getSatuSiswa(){
        viewModelScope.launch {
            statusUiDetail = StatusUiDetail.Loading
            statusUiDetail = try {
                StatusUiDetail.Succes(satusiswa = repositoryDataSiswa.getSatuSiswa(idSiswa))
            } catch (e: IOException){
                StatusUiDetail.Error
            } catch (e: HttpException){
                StatusUiDetail.Error
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun hapusSatuSiswa() {
        val resp: Response<Void> = repositoryDataSiswa.hapusSatuSiswa(idSiswa)

        if (resp.isSuccessful){
            println("Suskses Hapus Data : ${resp.message()}")
        } else {
            println("Gagal Hapus Data : ${resp.errorBody()}")
        }
    }
}