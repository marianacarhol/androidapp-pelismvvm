package carrillo.mariana.peliculasapp.modelos

import carrillo.mariana.peliculasapp.R

class PeliculaRepositorio {

    private val peliculas = mutableListOf(
        Pelicula(1, "Akira", "Science Fiction", "2h 4min", "Un proyecto militar secreto pone en peligro a Neo-Tokio cuando convierte a un miembro de una pandilla de motociclistas en un psicópata devastador que solo puede ser detenido por dos adolescentes y un grupo de psíquicos.", R.drawable.perfil),
        Pelicula(2, "Barry Lyndon", "Drama", "3h 5min", "Un irlandés sin escrúpulos enamora a una viuda adinerada y adopta la posición aristocrática que ocupaba su marido en la Inglaterra del siglo 18.", R.drawable.bootstrap_person_circle),
        Pelicula(3, "The Witch", "Horror", "1h 32min", "Ambientada en la década de 1630 en Nueva Inglaterra, una familia está desgarrada por las fuerzas de la brujería, la magia negra y la posesión.", R.drawable.perfil),
        Pelicula(4, "Sinners", "Thriller", "2h 17min", "Tratando de descubrir sus problemáticas vidas detrás, los hermanos gemelos regresan a su ciudad natal para comenzar de nuevo, solo para descubrir que un mal aún mayor los espera para darles la bienvenida nuevamente.", R.drawable.bootstrap_person_circle),
        Pelicula(5, "Five Nights at Freddy's", "Horror", "1h 50min", "Un guardia de seguridad con problemas comienza a trabajar en Freddy Fazbear's Pizza. Mientras pasa su primera noche en el trabajo, se da cuenta de que el turno de noche en Freddy's no será tan fácil de superar.", R.drawable.perfil)
    )

    fun getPeliculas(): List<Pelicula>{
        return peliculas.toList()
    }

    fun agregarPeliculas(pelicula: Pelicula) {
        peliculas.add(pelicula)
    }
}