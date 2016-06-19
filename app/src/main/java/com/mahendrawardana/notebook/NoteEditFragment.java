package com.mahendrawardana.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private EditText title, message;
    private ImageButton noteCatButton;
    private Integer editNoteCategory;

    private AlertDialog categoryDialogObject;

    private  Note.Category savedButtonCategory;


    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate our fragment edit layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        // Grab widget references fro layout
        title   = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);

        // Populate widgets with note data
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));

        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);

        //savedButtonCategory = noteCat;
        noteCatButton.setImageResource(Note.categoryToDrawable(noteCat));

        buildCategoryDialog(noteCat);
        noteCatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                categoryDialogObject.show();
            }
        });

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

    private void buildCategoryDialog(Note.Category noteCat) {
        final String[] categories = new String[] {"Facebook","Twitter","Instagram","Google Plus"};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose Note Type Sharing");

        switch(noteCat) {
            case FACEBOOK: editNoteCategory = 0; break;
            case TWITTER: editNoteCategory = 1; break;
            case INSTAGRAM: editNoteCategory = 2; break;
            case PLUS: editNoteCategory = 3; break;
        }

        categoryBuilder.setSingleChoiceItems(categories, editNoteCategory, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss our dialog window
                categoryDialogObject.cancel();

                switch(which) {
                    case 0:
                        noteCatButton.setImageResource(R.drawable.facebook);
                        savedButtonCategory = Note.Category.FACEBOOK;
                        break;
                    case 1:
                        noteCatButton.setImageResource(R.drawable.twitter);
                        savedButtonCategory = Note.Category.TWITTER;
                        break;
                    case 2:
                        noteCatButton.setImageResource(R.drawable.instagram);
                        savedButtonCategory = Note.Category.INSTAGRAM;
                        break;
                    case 3:
                        noteCatButton.setImageResource(R.drawable.plus);
                        savedButtonCategory = Note.Category.PLUS;
                        break;
                }

            }
        });

        categoryDialogObject = categoryBuilder.create();
    }

}
