package ppm.b.kelompok4.tokoelektronik.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ppm.b.kelompok4.tokoelektronik.model.Komputer
import ppm.b.kelompok4.tokoelektronik.repositories.KomputerRepository
import javax.inject.Inject

@HiltViewModel
class PengelolaanKomputerViewModel @Inject constructor(private val komputerRepository: KomputerRepository) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get()= _isLoading
    private val _success: MutableLiveData<Boolean> = MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _listData: MutableLiveData<List<Komputer>> = MutableLiveData()
    val listData: LiveData<List<Komputer>> get() = _listData

    suspend fun loadItems() {
        _isLoading.postValue(true)
        komputerRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _listData.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _listData.postValue(list)
        })
    }

    suspend fun loadItem(id: String, onSuccess: (Komputer?) -> Unit) {
        val item = komputerRepository.find(id)
        onSuccess(item)
    }

    suspend fun update(
        id: String,
        merk: String,
        jenis: String,
        harga: Int,
        dapat_diupgrade: Int,
        spesifikasi: String,
    ) {
        _isLoading.postValue(true)
        komputerRepository.update(id, merk, jenis, harga, dapat_diupgrade, spesifikasi,
            onError = {
                    item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _success.postValue(true)
                _isLoading.postValue(false)
            })
    }

    suspend fun insert(
        merk: String,
        jenis: String,
        harga: Int,
        dapat_diupgrade: Int,
        spesifikasi: String,
    ) {
        _isLoading.postValue(true)
        komputerRepository.insert(merk, jenis, harga, dapat_diupgrade, spesifikasi,
            onError = {
                    item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _success.postValue(true)
                _isLoading.postValue(false)
            })
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        komputerRepository.delete(id, onError = { message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _success.postValue(true)
        }, onSuccess = {
            _toast.postValue("Data Berhasil Dihapus")
            _isLoading.postValue(false)
            _success.postValue(true)
        })
    }
}