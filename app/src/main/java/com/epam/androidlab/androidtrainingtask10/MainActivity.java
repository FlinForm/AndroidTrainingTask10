package com.epam.androidlab.androidtrainingtask10;

import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
    private final String[] PROJECTION = {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
    private final String SELECTION =
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?";
    private SimpleCursorAdapter cursorAdapter;
    private final static String[] FROM_COLUMNS = {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
    private final static int[] TO_IDS = {android.R.id.text1};
    private ListView contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = (ListView) findViewById(R.id.list);
        cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                null,
                FROM_COLUMNS, TO_IDS,
                0);
        contactList.setAdapter(cursorAdapter);

    getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                null,
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        cursorAdapter.swapCursor(null);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        cursorAdapter.swapCursor(null);
    }
}