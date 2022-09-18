package hotel.minimal.client.presentation.viewModels

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import hotel.minimal.client.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(protected open val app: Application): AndroidViewModel(app) {

    protected val context: Context
        get() = app.applicationContext

    protected val resources: Resources
        get() = app.resources

    private val defaultErrorMessage: String
        get() = resources.getString(R.string.connection_error)

    protected suspend fun showToast(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    protected suspend fun onError(message: String?) {
        withContext(Dispatchers.Main) {
            showToast("Error: ${message ?: defaultErrorMessage}")
        }
    }
}