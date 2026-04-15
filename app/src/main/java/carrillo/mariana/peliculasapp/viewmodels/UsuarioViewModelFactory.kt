package carrillo.mariana.peliculasapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import carrillo.mariana.peliculasapp.modelos.Repositorio

class UsuarioViewModelFactory(private val repo: Repositorio): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T{

        if(modelClass.isAssignableFrom(UsuarioViewModel::class.java)){
            return UsuarioViewModel(repo) as T
        }
        throw IllegalArgumentException("Desconocido")
    }
}