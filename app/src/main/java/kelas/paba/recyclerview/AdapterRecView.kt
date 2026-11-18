package kelas.paba.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import kelas.paba.recyclerview.databinding.ItemRecycleBinding

class AdapterRecView (private val listWayang: ArrayList<dcWayang>): RecyclerView.Adapter<AdapterRecView.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val wayang = listWayang[position]
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context).apply{
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
        holder.binding.namaWayang.text = wayang.nama
        holder.binding.karakterWayang.text = wayang.karakter
        holder.binding.deskripsiWayang.text = wayang.deskripsi
        holder.binding.gambarWayang.load(wayang.foto){
            crossfade(true)
            placeholder(circularProgressDrawable)
            error(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    class ListViewHolder(val binding: ItemRecycleBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
