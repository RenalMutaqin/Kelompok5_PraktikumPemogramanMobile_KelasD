package com.example.tokoelektronik.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tokoelektronik.model.Komputer
import com.example.tokoelektronik.repositories.KomputerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KomputerViewModel @Inject constructor(private val komputerRepository: KomputerRepository) : ViewModel()
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
    private val _list: MutableLiveData<List<Komputer>> =
        MutableLiveData()
    val list: LiveData<List<Komputer>> get() = _list
    suspend fun loadItems() {
        _isLoading.postValue(true)
        komputerRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(
        merk: String,
        jenis: String,
        harga: String,
        dapat_diupgrade: String,
        spesifikasi: String
        ){
        _isLoading.postValue(true)
        komputerRepository.insert(
            merk, jenis, harga, dapat_diupgrade, spesifikasi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            }
        )
    }

    suspend fun loadItem(id: String, onSuccess: (Komputer?) ->
    Unit) {
        val item = komputerRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(
        id: String,
        merk: String,
        jenis: String,
        harga:  String,
        dapat_diupgrade: String,
        spesifikasi: String
        ){
        _isLoading.postValue(true)
        komputerRepository.update(
            id, merk, jenis, harga, dapat_diupgrade, spesifikasi,
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
        komputerRepository.delete(id, onError = { message ->
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