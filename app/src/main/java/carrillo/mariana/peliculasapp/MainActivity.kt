package carrillo.mariana.peliculasapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import carrillo.mariana.peliculasapp.modelos.PeliculaRepositorio
import carrillo.mariana.peliculasapp.modelos.Repositorio
import carrillo.mariana.peliculasapp.ui.theme.PeliculasAppTheme
import carrillo.mariana.peliculasapp.viewmodels.PeliculaViewModel
import carrillo.mariana.peliculasapp.viewmodels.PeliculaViewModelFactory
import carrillo.mariana.peliculasapp.viewmodels.UsuarioViewModel
import carrillo.mariana.peliculasapp.viewmodels.UsuarioViewModelFactory
import carrillo.mariana.peliculasapp.vistas.PeliculaScreen
import carrillo.mariana.peliculasapp.vistas.UsuarioScreen

class MainActivity : ComponentActivity() {
    private val TAG = "PELICULAS"

    private val viewModel: PeliculaViewModel by viewModels {
        PeliculaViewModelFactory(PeliculaRepositorio())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d(TAG, "Create")

        Toast.makeText(this, "Create", Toast.LENGTH_SHORT).show()
        setContent {
//            PeliculasAppTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
            PeliculaScreen(viewModel = viewModel)
        }
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Start")
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Resume")
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "Destroy", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Destroy")
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Stop")
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Pause")
    }

    override fun onRestart() {
        super.onRestart()

        Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Restart")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PeliculasAppTheme {
        Greeting("Android")
    }
}