package ppm.b.kelompok4.tokoelektronik.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ppm.b.kelompok4.tokoelektronik.model.Periferal
import ppm.b.kelompok4.tokoelektronik.repositories.PeriferalRepository
import javax.inject.Inject
@HiltViewModel
class PengelolaanPeriferalViewModel @Inject constructor(private val PeriferalRepository: PeriferalRepository) : ViewModel()
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
    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        PeriferalRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(nama: String,
                       harga: Int,
                       deskripsi: String,
                       jenis: String){
        _isLoading.postValue(true)
        PeriferalRepository.insert(nama, harga, deskripsi,jenis,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

    suspend fun loadItem(id: String, onSuccess: (Periferal?) ->
    Unit) {
        val item = PeriferalRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       nama: String,
                       harga: Int,
                       deskripsi: String,
                       jenis: String){
        _isLoading.postValue(true)
        PeriferalRepository.update(id, nama, harga, deskripsi, jenis,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        PeriferalRepository.delete(id, onError = { message ->
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

