package com.example.questapi.viewmodel

import com.example.questapi.modeldata.DetailSiswa

sealed interface StatusUiDetail{
    data class Succes(val satusiswa: DetailSiswa): StatusUiDetail
    object Error : StatusUiDetail
    object Loading : StatusUiDetail
}