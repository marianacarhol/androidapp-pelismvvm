package carrillo.mariana.peliculasapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import carrillo.mariana.peliculasapp.R
import carrillo.mariana.peliculasapp.modelos.Pelicula
import carrillo.mariana.peliculasapp.modelos.PeliculaRepositorio
import carrillo.mariana.peliculasapp.modelos.Repositorio
import carrillo.mariana.peliculasapp.modelos.Usuario

class PeliculaViewModel(val repo: PeliculaRepositorio): ViewModel() {

    private val _peliculas = mutableStateOf<List<Pelicula>>(emptyList())
    val peliculas: State<List<Pelicula>> = _peliculas

    init{
        getPeliculas()
    }

    private fun getPeliculas(){
        _peliculas.value = repo.getPeliculas()
    }

    fun agregaPelicula(titulo: String, categoria: String, duracion: String, sinopsis: String, fotoUri: String?){
        val nuevoId = peliculas.value.size + 1
        val peli = Pelicula(nuevoId, titulo, categoria, duracion, sinopsis, R.drawable.perfil, fotoUri)
        repo.agregarPeliculas(peli)

        _peliculas.value = repo.getPeliculas()
    }

    fun eliminaPelicula(pelicula: Pelicula){
        val lista = _peliculas.value.toMutableList()
        lista.remove(pelicula)
        _peliculas.value = lista
    }
}