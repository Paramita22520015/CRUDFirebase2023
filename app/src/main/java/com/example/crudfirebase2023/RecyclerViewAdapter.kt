import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudfirebase2023.R
import com.example.crudfirebase2023.data_mahasiswa

class RecyclerViewAdapter(private val listMahasiswa: ArrayList<data_mahasiswa>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val context: Context = context

    // ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NIM: TextView
        val Nama: TextView
        val Jurusan: TextView
        val ListItem: LinearLayout

        init {
            NIM = itemView.findViewById(R.id.nimx)
            Nama = itemView.findViewById(R.id.namax)
            Jurusan = itemView.findViewById(R.id.jurusanx)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_design, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nim: String? = listMahasiswa[position].nim
        val nama: String? = listMahasiswa[position].nama
        val jurusan: String? = listMahasiswa[position].jurusan

        holder.NIM.text = "NIM: $nim"
        holder.Nama.text = "Nama: $nama"
        holder.Jurusan.text = "Jurusan: $jurusan"

        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                return true
            }
        })
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }
}
