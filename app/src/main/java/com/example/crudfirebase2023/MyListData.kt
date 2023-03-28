import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudfirebase2023.data_mahasiswa
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MyListData : AppCompatActivity() {
    //Deklarasi Variable untuk RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var layoutManager: RecyclerView.LayoutManager
    //Deklarasi Variable Database Reference & ArrayList dengan Parameter Class Model kita.
    private lateinit var database: DatabaseReference
    private var dataMahasiswa = ArrayList<data_mahasiswa>()
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list_data)
        recyclerView = findViewById(R.id.datalist)
        supportActionBar?.title = "Data Mahasiswa"
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        MyRecyclerView()
        GetData()
    }

    //Baris kode untuk mengambil data dari Database & menampilkan kedalam Adapter
    private fun GetData() {
        Toast.makeText(applicationContext, "Mohon Tunggu Sebentar...",
            Toast.LENGTH_LONG).show()
        val getUserID: String = auth?.currentUser?.uid.toString()
        database.child("Admin").child(getUserID).child("Mahasiswa")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (snapshot in dataSnapshot.children) {
                            //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                            val mahasiswa =
                                snapshot.getValue(data_mahasiswa::class.java)
                            //Mengambil Primary Key, digunakan untuk proses Update/Delete
                            mahasiswa?.key = snapshot.key
                            mahasiswa?.let { dataMahasiswa.add(it) }
                        }
                        //Inisialisasi Adapter dan data Mahasiswa dalam bentuk Array
                        adapter = RecyclerViewAdapter(dataMahasiswa, this@MyListData)
                        //Memasang Adapter pada RecyclerView
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                        Toast.makeText(applicationContext,"Data Berhasil Dimuat",
                            Toast.LENGTH_LONG).show()
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // Kode ini akan dijalankan ketika ada error, simpan ke LogCat
                    Toast.makeText(applicationContext, "Data Gagal Dimuat",
                        Toast.LENGTH_LONG).show()
                    Log.e("MyListActivity", "${databaseError.details} ${databaseError.message}")
                }
            })
    }

    //Methode yang berisi kumpulan baris kode untuk mengatur RecyclerView
    private fun MyRecyclerView() {
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        //Membuat Underline pada Setiap Item Didalam List
        val itemDecoration = DividerItemDecoration(applicationContext,
            DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(
            ContextCompat.getDrawable(applicationContext,
                R.drawable.line)!!)
        recyclerView.addItemDecoration(itemDecoration)
    }
}
