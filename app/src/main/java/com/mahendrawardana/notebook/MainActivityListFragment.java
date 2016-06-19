package com.mahendrawardana.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notes = new ArrayList<Note>();
        notes.add(new Note("3 Aplikasi Memenangkan Indonesia Ramadhan Challenge", "Dalam menyambut bulan Ramadhan yang penuh berkah, Dicoding dengan dukungan dari Intel Indonesia menyelenggarakan “Indonesia Ramadhan Challenge” dari 16 Mei – 5 Juni 2016.", Note.Category.FACEBOOK));
        notes.add(new Note("Mengenal Intel XDK, Perangkat Development untuk Membangun Aplikasi Cross-Platform", "Siapa yang tidak mengenal Intel, sebuah perusahaan raksasa produsen mikroprosesor yang menjadi otak bagi jutaan komputer, laptop dan gadget. Intel adalah raksasa di belakang layar, di balik banyak teknologi lainnya yang membutuhkan performa komputasi yang sangat tinggi.", Note.Category.FACEBOOK));
        notes.add(new Note("Pendaftaran SMK Inclusive Innovation Challenge 2016 Diperpanjang","Dalam rangka kerja sama di bidang pendidikan kejuruan, Kementerian Pendidikan dan Kebudayaan Republik Indonesia, Kementerian Perindustrian Republik Indonesia dan RIBH SEA dan SED-TVET yang diimplementasikan oleh GIZ", Note.Category.PLUS));
        notes.add(new Note("500K Download Club DU Ad Platform Challenge","Model bisnis dalam sebuah aplikasi atau game berbasis mobile adalah hal yang sangat penting dalam pengembangan sebuah produk berbasis mobile. Banyak sekali model bisnis yang dapat dipakai oleh developer.",Note.Category.TWITTER));
        notes.add(new Note("Para Pemenang Hackathon Big Data dan Machine Learning CodeFest KUDO","Sebanyak 79 tim dengan 205 peserta mendaftar sebagai calon peserta pada CodeFest yang diadakan oleh KUDO. Baru pada tahap awal saja panitia CodeFest sudah dihadapkan dengan seleksi yang ketat.",Note.Category.INSTAGRAM));
        notes.add(new Note("Final CodeFest KUDO Big Data and Machine Learning","Bersamaan dengan berlangsungnya hackathon Makassar yang dilaksanakan pada 28-29 mei 2016 yang lalu, berlangsung pula Hackathon CodeFest yang diadakan oleh Kudo bertemakan Big data dan machine learning",Note.Category.TWITTER));
        notes.add(new Note("Tak Hanya Sekedar Canggih Buatlah yang Bermanfaat, Belajar dari Aplikasi Alarm Adzan Sholat dan Kiblat","Halo Devs. Kalau punya ide mau membuat aplikasi, salah satu faktor yang perlu dipertimbingkan adalah aplikasi yang akan Devs buat itu dibutuhkan oleh banyak orang.",Note.Category.FACEBOOK));
        notes.add(new Note("Situs Jejaring Sosial Mirip Facebook Muncul dari Korea Utara","Mengingat popularitasnya, bukan hal yang baru jika muncul jejaring sosial yang mirip Facebook. Namun, lain ceritanya jika situs jejaring sosial tersebut muncul dari salah satu negara paling tertutup dan paling ketat aturan dalam menggunakan internet seperti Korea Utara",Note.Category.PLUS));
        notes.add(new Note("1M Download Club DU Ad Platform Challenge","Model bisnis dalam sebuah aplikasi atau game berbasis mobile adalah hal yang sangat penting dalam pengembangan sebuah produk berbasis mobile.",Note.Category.INSTAGRAM));
        notes.add(new Note("Hackathon dari Makassar untuk Indonesia","28-29 Mei 2016 yang lalu telah terselenggara kompetisi Hackathon Makassar yang resmi dibuka oleh EVP Telkom Regional VII, Bpk Mohammad Firdaus.",Note.Category.TWITTER));

        noteAdapter = new NoteAdapter(getActivity(), notes);

        setListAdapter(noteAdapter);

        registerForContextMenu(getListView());

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW, position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Give me the position of whatever note I Long pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        // Returns to us id of whatever menu item we selected
        switch(item.getItemId()) {
            // If we press edit
            case R.id.edit:
                // do something here

                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT, rowPosition);
                Log.d("Menu Clicks", "We pressed edit");
                return true;

        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(MainActivity.FragmentToLaunch ftl, int position) {

        // Grab the note information associted with whateever note item we clicked on
        Note note     = (Note) getListAdapter().getItem(position);

        // Create a note intent that launches our noteDetailActivity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);

        // Pass aloing the infromation of the note we clicked on to our noteDetailActivity
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getId());

        switch(ftl) {
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.EDIT);
                break;
        }

        startActivity(intent);
    }


}
