package ppm.b.kelompok4.tokoelektronik.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import ppm.b.kelompok4.tokoelektronik.model.Periferal
import ppm.b.kelompok4.tokoelektronik.networks.PeriferalApi
import ppm.b.kelompok4.tokoelektronik.persistences.PeriferalDao
import javax.inject.Inject

class PeriferalRepository @Inject constructor(
    private val api: PeriferalApi,
    private val dao: PeriferalDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<Periferal>) -> Unit,
        onError: (List<Periferal>, String) -> Unit
    ) {
        val list: List<Periferal> = dao.getList()
        api.all()
// handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<Periferal> = dao.getList()
                        onSuccess(items)
                    }
                }
            }
// handle the case when the API request gets an error response.
// e.g. internal server error.
            .suspendOnError {
                onError(list, message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(list, message())
            }
    }
    suspend fun insert(
        nama: String,
        harga: Int,
        deskripsi: String,
        jenis: String,
        onSuccess: (Periferal) -> Unit,
        onError: (Periferal?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Periferal(id, nama, harga, deskripsi, jenis)
        dao.insertAll(item)
        api.insert(item)
// handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
// handle the case when the API request gets an error response.
// e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update(
        id: String,
        nama: String,
        harga: Int,
        deskripsi: String,
        jenis: String,
        onSuccess: (Periferal) -> Unit,
        onError: (Periferal?, String) -> Unit
    ) {
        val item = Periferal(id, nama, harga, deskripsi, jenis)
        dao.insertAll(item)
        api.update(id, item)
// handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
// handle the case when the API request gets an error response.
// e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(id: String, onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.delete(id)
        api.delete(id)
// handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
// handle the case when the API request gets an error response.
// e.g. internal server error.
            .suspendOnError {
                onError(message())
            }
// handle the case when the API request gets an exception response.
// e.g. network connection error.
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) : Periferal? {
        return dao.find(id)
    }
}