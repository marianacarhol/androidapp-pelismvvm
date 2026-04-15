package carrillo.mariana.peliculasapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import carrillo.mariana.peliculasapp.modelos.PeliculaRepositorio

class PeliculaViewModelFactory(private val repo: PeliculaRepositorio): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T{

        if(modelClass.isAssignableFrom(PeliculaViewModel::class.java)){
            return PeliculaViewModel(repo) as T
        }
        throw IllegalArgumentException("Desconocido")
    }
}