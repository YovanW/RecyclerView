package kelas.paba.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.app.AlertDialog
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import kelas.paba.recyclerview.databinding.ActivityMainBinding
import kelas.paba.recyclerview.ui.theme.RecyclerViewTheme
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.widget.EditText
import android.widget.LinearLayout

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

        binding.btnToggleLayout.setOnCheckedChangeListener { _, isChecked ->
            tampilkanData()
        }
    }

    private fun siapkanData(){
        _nama = resources.getStringArray(R.array.namaWayang)
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang)
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang)
        _gambar = resources.getStringArray(R.array.gambarWayang)
    }

    private fun tambahData(){
        _arWayang.clear()
        for (i in _nama.indices) {
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
        val adapterWayang = AdapterRecView(_arWayang)

        adapterWayang.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(data: dcWayang) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply{
                    putExtra("nama", data.nama)
                    putExtra("karakter", data.karakter)
                    putExtra("deskripsi", data.deskripsi)
                    putExtra("foto", data.foto)
                }
                startActivity(intent)
            }

            override fun delData(pos: Int) {
                if (pos !in _arWayang.indices) return
                val item = _arWayang[pos]
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Konfirmasi Hapus Data")
                    .setMessage("Apakah Anda yakin ingin menghapus data ${item.nama}?")
                    .setPositiveButton("Hapus") { _, _ ->
                        _arWayang.removeAt(pos)
                        adapterWayang.notifyItemRemoved(pos)
                        adapterWayang.notifyItemRangeChanged(pos, _arWayang.size - pos)
                        Toast.makeText(
                            this@MainActivity,
                            "Data ${item.nama} berhasil dihapus",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("Tidak", null)
                    .show()
            }
        })
        binding.rvWayang.layoutManager =
            if (binding.btnToggleLayout.isChecked) GridLayoutManager(this, 2)
            else LinearLayoutManager(this)
        binding.rvWayang.adapter = adapterWayang
    }

}