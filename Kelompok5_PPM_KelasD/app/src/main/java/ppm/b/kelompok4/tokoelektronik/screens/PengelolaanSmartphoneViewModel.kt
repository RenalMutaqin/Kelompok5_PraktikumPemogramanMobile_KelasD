package ppm.b.kelompok4.tokoelektronik.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ppm.b.kelompok4.tokoelektronik.model.Smartphone
import ppm.b.kelompok4.tokoelektronik.repositories.SmartphoneRepository
import javax.inject.Inject
@HiltViewModel
class PengelolaanSmartphoneViewModel @Inject constructor(private val SmartphoneRepository: SmartphoneRepository) : ViewModel()
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
    private val _list: MutableLiveData<List<Smartphone>> =
        MutableLiveData()
    val list: LiveData<List<Smartphone>> get() = _list
    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        SmartphoneRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(model: String,
                       warna: String,
                       storage: Int,
                       tanggal_rilis: String,
                       sistem_operasi: String){
        _isLoading.postValue(true)
        SmartphoneRepository.insert(model, warna, storage, tanggal_rilis,sistem_operasi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

    suspend fun loadItem(id: String, onSuccess: (Smartphone?) ->
    Unit) {
        val item = SmartphoneRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       model: String,
                       warna: String,
                       storage: Int,
                       tanggal_rilis: String,
                       sistem_operasi: String){
        _isLoading.postValue(true)
        SmartphoneRepository.update(id, model, warna, storage, tanggal_rilis,sistem_operasi,
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
        SmartphoneRepository.delete(id, onError = { message ->
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

