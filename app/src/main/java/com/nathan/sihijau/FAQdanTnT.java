package com.nathan.sihijau;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

public class FAQdanTnT extends AppCompatActivity {
    private SwipeDeck cardStack;
    private ArrayList<CourseModal> courseModalArrayList;

    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    static ArrayList<String> arrayList = new ArrayList<>();
    MainAdapter adapter;

    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_tnt);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.btmenu);
        recyclerView = findViewById(R.id.recycler_view);

        arrayList.clear();

        arrayList.add("Dashboard");
        arrayList.add("Account");
        arrayList.add("Konten Page");
        arrayList.add("Contact List");
        arrayList.add("About");
        arrayList.add("Maps");
        arrayList.add("Tips");
        arrayList.add("FAQ");
        arrayList.add("Keluar");

        adapter = new MainAdapter(this,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        courseModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // on below line we are adding data to our array list.
        courseModalArrayList.add(new CourseModal("Mengurangi Sampah Belanja " + "\n \nBawa Belanjaan Memakai Tas", "", "Saat berbelanja dan lupa bawa tas belanja sendiri, salah satu pilihannya kita bisa memasukan barang belanjaan ke dalam tas yang kita pakai.", "Triknya, tata yang rapi biar muat lebih banyak dan mudah buat membawanya. Nah ketika tahu dengan kita bawa kantong belanja, saat belanja dapat menyesesuaikan juga dengan kapasitas tas yang kita bawa.", R.drawable.mengurangi_sampah_plastik));
        courseModalArrayList.add(new CourseModal("Mengolah Sampah Organik \n \nPengolahan Sampah Rumah Tangga", "", "Mengubah sampah organik di rumah menjadi pupuk kompos", "Kamu bisa menyediakan tempat sampah ukuran kecil untuk pembuangan sementara. Namun, kamu juga harus menyiapkan composter sebagai wadah sampah organik tersebut agar mengendap lebih lama.", R.drawable.sampahorganik_compos));
        courseModalArrayList.add(new CourseModal("Daur Ulang \n \nMendaur Sampah Anorganik", "", "Mendaur ulang sampah-sampah yang tidak dapat diolah", "Dengan melakukan 3R, kamu bisa turut membantu mengurangi tingkat penimbunan sampah dari rumah. Kamu dapat memulainya dari mengurangi sampah (reduce), menggunakan kembali (reuse) dan mendaur ulang (recycle)",R.drawable.sampahdaurulang));
        courseModalArrayList.add(new CourseModal("Mengurangi Sampah Plastik \n \nMenggunakan Barang yang Eco-Friendly", "", "Memakai sampah yang tidak merusak bagi lingkungan dan tidak mengganggu lingkungan", "Sudah banyak kok, toko-toko yang juga menjual tas belanja ramah lingkungan dengan motif yang lucu dan menarik.", R.drawable.eco_friendly));
        courseModalArrayList.add(new CourseModal("Memilah Sampah \n \nMemilah Sampah Organik dan Anorganik", "", "Banyak sampah yang perlu di pilah agar TPA tidak penuh", "Mulailah belajar memilah sampah organik dan anorganik, lalu belajar mengolah sampah yang telah dipilah tersebut. Sejumlah jenis sampah bisa didaur ulang maupun dijadikan bahan pupuk dan kompos, kok", R.drawable.memilah_sampah));
        courseModalArrayList.add(new CourseModal("Membuang Sampah Pada Tempatnya \n \nMengurangi Sampah Berserakan", "", "Banyak sampah yang masih berserakan di jalanan maupun di lingkunan", "Mulailah belajar membuang sampah pada tempat yang telah disediakan oleh pemerintah ataupun tidak membuang sampah dengan sembarangan", R.drawable.stop));


        // on below line we are creating a variable for our adapter class and passing array list to it.
        final DeckAdapter adapter = new DeckAdapter(courseModalArrayList, this);

        // on below line we are setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(FAQdanTnT.this, "No more Tips and Tricks present", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
    }
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}