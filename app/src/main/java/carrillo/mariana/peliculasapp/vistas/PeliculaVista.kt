package carrillo.mariana.peliculasapp.vistas

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import carrillo.mariana.peliculasapp.modelos.Pelicula
import carrillo.mariana.peliculasapp.viewmodels.PeliculaViewModel
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import coil3.compose.AsyncImage
import org.jetbrains.annotations.Async

@Composable
fun PeliculaScreen(viewModel: PeliculaViewModel){
    val peliculas = viewModel.peliculas.value
    var mostrarDialogo by remember {mutableStateOf(false)  }
    val context = LocalContext.current

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    mostrarDialogo = true
                }
            ) {
                Icon (
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        }

    ){ padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            items(peliculas){ pelicula ->
                PeliculaCard(
                    pelicula = pelicula,
                    onEliminar = { viewModel.eliminaPelicula(pelicula) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
    if(mostrarDialogo){
        AgregarPeliculaDialog(
            onDismiss = { mostrarDialogo = false },
            onConfirm = { titulo, categoria, duracion, sinopsis, fotoUri ->
                viewModel.agregaPelicula(titulo, categoria, duracion, sinopsis, fotoUri)
                mostrarDialogo = false
            }
        )
    }

}

@Composable
fun PeliculaCard(pelicula: Pelicula, onEliminar: () -> Unit){
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp)
            ){
                if(pelicula.fotoUri != null){
                    AsyncImage(
                        model = pelicula.fotoUri,
                        contentDescription = "Avatar",
                        modifier = Modifier.size(48.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(pelicula.foto),
                        contentDescription = "Avatar",
                        modifier = Modifier.size(48.dp)
                    )
                }

                Text(text = pelicula.titulo)
                Text(text = pelicula.categoria)
                Text(text = pelicula.duracion)
                Text(text = pelicula.sinopsis)
            }
            IconButton(
                onClick = onEliminar,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Red
                )
            }
        }
    }
}

@Composable
fun AgregarPeliculaDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, String, String?) -> Unit
){
    var foto by remember { mutableStateOf<Uri?>(null) }
    var titulo by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract =    ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        foto  = uri

    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {Text("Nueva Película")},
        text = {
            Column {

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .clickable {
                            launcher.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ){
                    if(foto != null){
                        AsyncImage(
                            model = foto,
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }else{
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Elegir Foto",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text("Toca para elegir foto", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it},
                    label = { Text("Titulo") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = categoria,
                    onValueChange = { categoria = it },
                    label = { Text("Categoria") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = duracion,
                    onValueChange = { duracion = it },
                    label = { Text("Duracion") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = sinopsis,
                    onValueChange = { sinopsis = it },
                    label = { Text("Sinopsis") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (titulo.isNotBlank()) {
                        onConfirm (titulo, categoria, duracion, sinopsis, foto?.toString())
                    }
                }
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }


    )
}