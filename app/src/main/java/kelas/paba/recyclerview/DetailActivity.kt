package kelas.paba.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import kelas.paba.recyclerview.databinding.ActivityDetailBinding

private lateinit var binding: ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        val karakter = intent.getStringExtra("karakter")
        val deskripsi = intent.getStringExtra("deskripsi")
        val foto = intent.getStringExtra("foto")

        binding.tvDetailNama.text = nama
        binding.chipDetailKarakter.text = karakter
        binding.tvDetailDeskripsi.text = deskripsi

        val circularProgressDrawable = CircularProgressDrawable(this).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }

        binding.ivDetailGambar.load(foto) {
            crossfade(true)
            placeholder(circularProgressDrawable)
            error(R.drawable.ic_launcher_background)
        }
    }
}