package com.example.tokoelektronik.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tokoelektronik.model.Periferal
import com.example.tokoelektronik.repositories.PeriferalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeriferalViewModel @Inject constructor(private val periferalRepository: PeriferalRepository) : ViewModel()
{
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<Periferal>> =
        MutableLiveData()
    val list: LiveData<List<Periferal>> get() = _list
    suspend fun loadItems() {
        _isLoading.postValue(true)
        periferalRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(
            nama: String,
            harga: String,
            deskripsi: String,
            jenis : String
        ){
        _isLoading.postValue(true)
        periferalRepository.insert(
            nama, harga, jenis, deskripsi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

    suspend fun loadItem(id: String, onSuccess: (Periferal?) ->
    Unit) {
        val item = periferalRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(
            id: String,
            nama: String,
            harga: String,
            deskripsi: String,
            jenis : String
        ){
        _isLoading.postValue(true)
        periferalRepository.update(
            id, nama, harga, deskripsi, jenis,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        periferalRepository.delete(id, onError = { message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _success.postValue(true)
        }, onSuccess = {
            _toast.postValue("Data berhasil dihapus")
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }

}