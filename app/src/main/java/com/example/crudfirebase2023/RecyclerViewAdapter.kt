import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudfirebase2023.MyListData
import com.example.crudfirebase2023.R
import com.example.crudfirebase2023.UpdateData
import com.example.crudfirebase2023.data_mahasiswa


//Class Adapter ini Digunakan Untuk Mengatur Bagaimana Data akan Ditampilkan
class RecyclerViewAdapter( private val listMahasiswa: ArrayList<data_mahasiswa>, context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val context: Context

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NIM: TextView
        val Nama: TextView
        val Jurusan: TextView
        val jeniskelamin: TextView
        val alamat: TextView
        val ListItem: LinearLayout
        init {//Menginisialisasi View yang terpasang pada layout RecyclerView kita
            NIM = itemView.findViewById(R.id.nimx)
            Nama = itemView.findViewById(R.id.namax)
            Jurusan = itemView.findViewById(R.id.jurusanx)
            jeniskelamin = itemView.findViewById(R.id.jeniskelaminx)
            alamat = itemView.findViewById(R.id.alamatx)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        //Membuat View untuk Menyiapkan & Memasang Layout yang digunakan pada RecyclerView
        val V: View = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.view_design, parent, false)
        return ViewHolder(V)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Mengambil Nilai/Value pada RecyclerView berdasarkan Posisi Tertentu
        val NIM: String? = listMahasiswa.get(position).nim
        val Nama: String? = listMahasiswa.get(position).nama
        val Jurusan: String? = listMahasiswa.get(position).jurusan
        val jeniskelamin: String? = listMahasiswa.get(position).jeniskelamin
        val alamat: String? = listMahasiswa.get(position).alamat

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.NIM.text = "NIM: $NIM"
        holder.Nama.text = "Nama: $Nama"
        holder.Jurusan.text = "Jurusan: $Jurusan"
        holder.jeniskelamin.text = "jeniskelamin: $jeniskelamin"
        holder.alamat.text = "alamat: $alamat"
        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
//Kodingan untuk fungsi Edit dan Delete, yang dibahas pada Tutorial Berikutnya.
                return true
            }
        })
    }
    override fun getItemCount(): Int {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return listMahasiswa.size
    }
    //Membuat Konstruktor, untuk menerima input dari Database
    init {
        this.context = context
    }
}