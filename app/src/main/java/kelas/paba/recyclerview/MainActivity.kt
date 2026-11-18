package kelas.paba.recyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import kelas.paba.recyclerview.databinding.ActivityMainBinding
import kelas.paba.recyclerview.ui.theme.RecyclerViewTheme

private lateinit var binding: ActivityMainBinding
private val _arWayang = ArrayList<dcWayang>()

private var _nama: Array<String> = arrayOf()
private var _karakter: Array<String> = arrayOf()
private var _deskripsi: Array<String> = arrayOf()
private var _gambar: Array<String> = arrayOf()



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        siapkanData()
        tambahData()
        tampilkanData()
    }

    private fun siapkanData(){
        _nama = resources.getStringArray(R.array.namaWayang)
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang)
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang)
        _gambar = resources.getStringArray(R.array.gambarWayang)
    }

    private fun tambahData(){
        _arWayang.clear()
        for (i in _nama.indices){
            val wayang = dcWayang(
                foto = _gambar[i],
                nama = _nama[i],
                karakter = _karakter[i],
                deskripsi = _deskripsi[i]
            )
            _arWayang.add(wayang)
        }
    }

    private fun tampilkanData(){
        binding.rvWayang.layoutManager = LinearLayoutManager(this)
        binding.rvWayang.adapter = AdapterRecView(_arWayang)
    }
}
