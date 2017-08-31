
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

import rafasaid.com.br.santacruzveterano.data.ResultadoContract.ResultadoEntry;


/**
 * Allows user to create a new resultado or edit an existing one.
 */
public class AdicionarResultado extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //Idenficador do resultado loader
    private static final int EXISTING_RESULTADO_LOADER = 0;

    //Conteúdo URI de um resultado existente (nulo se for um novo resultado)
    private Uri mCurrentResultadoUri;

    /**
     * EditText field to enter the resultado's name
     */
    private EditText mDataAddResultadoEditText;

    /**
     * EditText field to enter the resultado's breed
     */
    private EditText mTimesAddResultadoEditText;

    /**
     * EditText field to enter the resultado's weight
     */
    private EditText mGolsAddResultadoEditText;

    /**
     * Boolean flag that keeps track of whether the resultado has been edited (true) or not (false)
     */
    private boolean mResultadoHasChanged = false;

    /**
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the mResultadoHasChanged boolean to true.
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mResultadoHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_resultado);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new resultado or editing an existing one.
        Intent intentAddResultado = getIntent();
        mCurrentResultadoUri = intentAddResultado.getData();

        // If the intent DOES NOT contain a resultado content URI, then we know that we are
        // creating a new resultado.
        if (mCurrentResultadoUri == null) {
            // This is a new resultado, so change the app bar to say "Adicionar resultado"
            setTitle(getString(R.string.editor_activity_title_new_resultado));

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a resultado that hasn't been created yet.)
            invalidateOptionsMenu();

        } else {
            // Otherwise this is an existing resultado, so change app bar to say "Editar resultado"
            setTitle(getString(R.string.editor_activity_title_edit_resultado));

            //Inicializa um loader para ler os dados do resultado da database e mostra o valor
            //atual no editor
            getSupportLoaderManager().initLoader(EXISTING_RESULTADO_LOADER, null, this);
        }

        // Find all relevant views that we will need to read user input from
        mDataAddResultadoEditText = (EditText) findViewById(R.id.data_add_resultado);
        mTimesAddResultadoEditText = (EditText) findViewById(R.id.times_add_resultado);
        mGolsAddResultadoEditText = (EditText) findViewById(R.id.gols_add_resultado);

        // Setup OnTouchListeners on all the input fields, so we can determine if the user
        // has touched or modified them. This will let us know if there are unsaved changes
        // or not, if the user tries to leave the editor without saving.
        mDataAddResultadoEditText.setOnTouchListener(mTouchListener);
        mTimesAddResultadoEditText.setOnTouchListener(mTouchListener);
        mGolsAddResultadoEditText.setOnTouchListener(mTouchListener);

    }

    //Pega a entrada do editor e salva um resultado na database
    private void saveResultado() {

        //Integer.parseInt() [transforma uma String em inteiro i. e. Integer.parseInt("1") = 1;

        //método .trim() elimina os espaços excedentes, garante que o usuário não deigitará
        //espaços desnecessários antes e depois da string
        String dataResultadoString = mDataAddResultadoEditText.getText().toString().trim();
        String timesResultadoString = mTimesAddResultadoEditText.getText().toString().trim();
        String golsResultadoString = mGolsAddResultadoEditText.getText().toString().trim();

        //cria um objeto ContentValues onde as colunas são as chaves, e os atributos (data, times
        //gols) são os valores
        ContentValues values = new ContentValues();
        values.put(ResultadoEntry.COLUMN_RESULTADO_DATA, dataResultadoString);
        values.put(ResultadoEntry.COLUMN_RESULTADO_TIMES, timesResultadoString);
        values.put(ResultadoEntry.COLUMN_RESULTADO_GOLS, golsResultadoString);

        //determine if this is a new or existing resultado by checking if mCurrentResultadoUri is null or not
        if (mCurrentResultadoUri == null) {
            //This is a NEW resultado, so insert a resultado into the provider, returning the content
            //URI for the new resultado.
            Uri newUri = getContentResolver().insert(ResultadoEntry.CONTENT_URI, values);

            //show a toast message depending on whether or not the insertion was successful
            if (newUri == null) {

                //if the new content URI is null, the there was an error with insertion
                Toast.makeText(this, getString(R.string.editor_insert_resultado_failed),
                        Toast.LENGTH_LONG).show();
            } else {

                //otherwise, the insertion was successful and we can display a toast
                Toast.makeText(this, getString(R.string.editor_insert_resultado_successful),
                        Toast.LENGTH_LONG).show();

            }
        } else {
            // Otherwise this is an EXISTING resultado, so update the resultado with content URI: mCurrentResultadoUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentResultadoUri will already identify the correct row in the database that
            // we want to modify.
            int rowsAffected = getContentResolver().update(mCurrentResultadoUri, values, null, null);

            //show a toast message depending on wheter or not the update was successful
            if (rowsAffected == 0) {

                //if no rows were affected, the there was an error with the update
                Toast.makeText(this, getString(R.string.editor_insert_resultado_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                //otherwise, the update was successful and we can display a toast
                Toast.makeText(this, getString(R.string.editor_insert_resultado_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_adicionar_resultadonar_resultado.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_adicionar_resultado, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new resultado, hide the "Delete" menu item.
        if (mCurrentResultadoUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save: //executa duas ações: [insertResultado] insere os dados na banco
                //de dados resultado, [finish] encerra a inserção de dados e
                //retorna para a lista de resultados
                // insere o resultado no banco de dados (database)
                saveResultado();
                //retorna para a lista de resultados
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the resultado hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mResultadoHasChanged) {
                    NavUtils.navigateUpFromSameTask(AdicionarResultado.this);
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
                                NavUtils.navigateUpFromSameTask(AdicionarResultado.this);
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
        // If the resultado hasn't changed, continue with handling back button press
        if (!mResultadoHasChanged) {
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

        // Since the editor shows all resultado attributes, define a projection that contains
        // all columns from the resultado table
        String[] projection = {
                ResultadoEntry._ID,
                ResultadoEntry.COLUMN_RESULTADO_DATA,
                ResultadoEntry.COLUMN_RESULTADO_TIMES,
                ResultadoEntry.COLUMN_RESULTADO_GOLS};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentResultadoUri,   // Query the content URI for the current resultado
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
            // Find the columns of resultado attributes that we're interested in
            int dataColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_RESULTADO_DATA);
            int timesColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_RESULTADO_TIMES);
            int golsColumnIndex = cursor.getColumnIndex(ResultadoEntry.COLUMN_RESULTADO_GOLS);

            // Extract out the value from the Cursor for the given column index
            String data = cursor.getString(dataColumnIndex);
            String times = cursor.getString(timesColumnIndex);
            String gols = cursor.getString(golsColumnIndex);

            // Update the views on the screen with the values from the database
            mDataAddResultadoEditText.setText(data);
            mTimesAddResultadoEditText.setText(times);
            mGolsAddResultadoEditText.setText(gols);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        // If the loader is invalidated, clear out all the data from the input fields.
        mDataAddResultadoEditText.setText("");
        mTimesAddResultadoEditText.setText("");
        mGolsAddResultadoEditText.setText("");
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
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the resultado.
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
     * Prompt the user to confirm that they want to delete this resultado.
     */
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the resultado.
                deleteResultado();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the resultado.
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
     * Perform the deletion of the resultado in the database.
     */
    private void deleteResultado() {
        // Only perform the delete if this is an existing resultado.
        if (mCurrentResultadoUri != null) {
            // Call the ContentResolver to delete the resultado at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentResultadoUri
            // content URI already identifies the resultado that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentResultadoUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_resultado_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_resultado_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }
}
