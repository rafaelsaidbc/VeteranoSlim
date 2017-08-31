package rafasaid.com.br.santacruzveterano;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import rafasaid.com.br.santacruzveterano.data.CalendarioContract.CalendarioEntry;


/**
 * Allows user to create a new calendario or edit an existing one.
 */
public class AdicionarCalendario extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //Idenficador do calendario loader
    private static final int EXISTING_CALENDARIO_LOADER = 1;

    //Conteúdo URI de um calendario existente (nulo se for um novo calendario)
    private Uri mCurrentCalendarioUri;

    /**
     * EditText field to enter the calendario's name
     */
    private EditText mDataAddCalendarioEditText;

    /**
     * EditText field to enter the calendario's breed
     */
    private EditText mAdversarioAddCalendarioEditText;

    /**
     * EditText field to enter the calendario's weight
     */
    private EditText mLocalAddCalendarioEditText;

    /**
     * Boolean flag that keeps track of whether the calendario has been edited (true) or not (false)
     */
    private boolean mCalendarioHasChanged = false;

    /**
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the mCalendarioHasChanged boolean to true.
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mCalendarioHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_calendario);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new calendario or editing an existing one.
        Intent intentAddCalendario = getIntent();
        mCurrentCalendarioUri = intentAddCalendario.getData();

        // If the intent DOES NOT contain a calendario content URI, then we know that we are
        // creating a new calendario.
        if (mCurrentCalendarioUri == null) {
            // This is a new calendario, so change the app bar to say "Adicionar calendario"
            setTitle(getString(R.string.editor_activity_title_new_calendario));

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a calendario that hasn't been created yet.)
            invalidateOptionsMenu();

        } else {
            // Otherwise this is an existing calendario, so change app bar to say "Editar calendario"
            setTitle(getString(R.string.editor_activity_title_edit_calendario));

            //Inicializa um loader para ler os dados do calendario da database e mostra o valor
            //atual no editor
            getSupportLoaderManager().initLoader(EXISTING_CALENDARIO_LOADER, null, this);
        }

        // Find all relevant views that we will need to read user input from
        mDataAddCalendarioEditText = (EditText) findViewById(R.id.data_add_calendario);
        mAdversarioAddCalendarioEditText = (EditText) findViewById(R.id.adversario_add_calendario);
        mLocalAddCalendarioEditText = (EditText) findViewById(R.id.local_add_calendario);

        // Setup OnTouchListeners on all the input fields, so we can determine if the user
        // has touched or modified them. This will let us know if there are unsaved changes
        // or not, if the user tries to leave the editor without saving.
        mDataAddCalendarioEditText.setOnTouchListener(mTouchListener);
        mAdversarioAddCalendarioEditText.setOnTouchListener(mTouchListener);
        mLocalAddCalendarioEditText.setOnTouchListener(mTouchListener);

    }

    //Pega a entrada do editor e salva um calendario na database
    private void saveCalendario() {

        //Integer.parseInt() [transforma uma String em inteiro i. e. Integer.parseInt("1") = 1;

        //método .trim() elimina os espaços excedentes, garante que o usuário não deigitará
        //espaços desnecessários antes e depois da string
        String dataCalendarioString = mDataAddCalendarioEditText.getText().toString().trim();
        String adversarioCalendarioString = mAdversarioAddCalendarioEditText.getText().toString().trim();
        String localCalendarioString = mLocalAddCalendarioEditText.getText().toString().trim();

        //cria um objeto ContentValues onde as colunas são as chaves, e os atributos (data, adversario
        //local) são os valores
        ContentValues valuesCalendario = new ContentValues();
        valuesCalendario.put(CalendarioEntry.COLUMN_CALENDARIO_DATA, dataCalendarioString);
        valuesCalendario.put(CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO, adversarioCalendarioString);
        valuesCalendario.put(CalendarioEntry.COLUMN_CALENDARIO_LOCAL, localCalendarioString);

        //determine if this is a new or existing calendario by checking if mCurrentCalendarioUri is null or not
        if (mCurrentCalendarioUri == null) {
            //This is a NEW calendario, so insert a calendario into the provider, returning the content
            //URI for the new calendario.
            Uri newUri = getContentResolver().insert(CalendarioEntry.CONTENT_URI_CALENDARIO, valuesCalendario);

            //show a toast message depending on whether or not the insertion was successful
            if (newUri == null) {

                //if the new content URI is null, the there was an error with insertion
                Toast.makeText(this, getString(R.string.editor_insert_calendario_failed),
                        Toast.LENGTH_LONG).show();
            } else {

                //otherwise, the insertion was successful and we can display a toast
                Toast.makeText(this, getString(R.string.editor_insert_calendario_successful),
                        Toast.LENGTH_LONG).show();

            }
        } else {
            // Otherwise this is an EXISTING calendario, so update the calendario with content URI: mCurrentCalendarioUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentCalendarioUri will already identify the correct row in the database that
            // we want to modify.
            int rowsAffected = getContentResolver().update(mCurrentCalendarioUri, valuesCalendario, null, null);

            //show a toast message depending on wheter or not the update was successful
            if (rowsAffected == 0) {

                //if no rows were affected, the there was an error with the update
                Toast.makeText(this, getString(R.string.editor_insert_calendario_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                //otherwise, the update was successful and we can display a toast
                Toast.makeText(this, getString(R.string.editor_insert_calendario_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_adicionar_calendarionar_calendario.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_adicionar_calendario, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new calendario, hide the "Delete" menu item.
        if (mCurrentCalendarioUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete_calendario);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save_calendario: //executa duas ações: [insertCalendario] insere os dados na banco
                //de dados calendario, [finish] encerra a inserção de dados e
                //retorna para a lista de calendario
                // insere o calendario no banco de dados (database)
                saveCalendario();
                //retorna para a lista de calendario
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete_calendario:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the calendario hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mCalendarioHasChanged) {
                    NavUtils.navigateUpFromSameTask(AdicionarCalendario.this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(AdicionarCalendario.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        // If the calendario hasn't changed, continue with handling back button press
        if (!mCalendarioHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        // Since the editor shows all calendario attributes, define a projection that contains
        // all columns from the calendario table
        String[] projection = {
                CalendarioEntry._ID,
                CalendarioEntry.COLUMN_CALENDARIO_DATA,
                CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO,
                CalendarioEntry.COLUMN_CALENDARIO_LOCAL};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentCalendarioUri,   // Query the content URI for the current calendario
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of calendario attributes that we're interested in
            int dataCalendarioColumnIndex = cursor.getColumnIndex(CalendarioEntry.COLUMN_CALENDARIO_DATA);
            int adversarioColumnIndex = cursor.getColumnIndex(CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO);
            int localColumnIndex = cursor.getColumnIndex(CalendarioEntry.COLUMN_CALENDARIO_LOCAL);

            // Extract out the value from the Cursor for the given column index
            String dataCalendario = cursor.getString(dataCalendarioColumnIndex);
            String adversario = cursor.getString(adversarioColumnIndex);
            String local = cursor.getString(localColumnIndex);

            // Update the views on the screen with the values from the database
            mDataAddCalendarioEditText.setText(dataCalendario);
            mAdversarioAddCalendarioEditText.setText(adversario);
            mLocalAddCalendarioEditText.setText(local);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        // If the loader is invalidated, clear out all the data from the input fields.
        mDataAddCalendarioEditText.setText("");
        mAdversarioAddCalendarioEditText.setText("");
        mLocalAddCalendarioEditText.setText("");
    }

    /**
     * Show a dialog that warns the user there are unsaved changes that will be lost
     * if they continue leaving the editor.
     *
     * @param discardButtonClickListener is the click listener for what to do when
     *                                   the user confirms they want to discard their changes
     */
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg_calendario);
        builder.setPositiveButton(R.string.discard_calendario, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing_calendario, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the calendario.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Prompt the user to confirm that they want to delete this calendario.
     */
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete__calendario_dialog_msg);
        builder.setPositiveButton(R.string.delete_calendario, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the calendario.
                deleteCalendario();
            }
        });
        builder.setNegativeButton(R.string.cancel_calendario, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the calendario.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the calendario in the database.
     */
    private void deleteCalendario() {
        // Only perform the delete if this is an existing calendario.
        if (mCurrentCalendarioUri != null) {
            // Call the ContentResolver to delete the calendario at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentCalendarioUri
            // content URI already identifies the calendario that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentCalendarioUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_calendario_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_calendario_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }
}
