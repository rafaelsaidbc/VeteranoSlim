package rafasaid.com.br.santacruzveterano;

import android.support.v7.app.AppCompatActivity;

public class FotosActivity extends AppCompatActivity {

    /*
    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_FOTOS_LENGTH_LIMIT = 1000;
    public static final String FOTOS_LENGTH_KEY = "fotos_length";
    public static final int RC_SIGN_IN = 1;
    private static final String TAG = "FotosActivity";
    private static final int RC_PHOTO_PICKER = 2;

    private ListView mFotosListView;
    private FotosAdapter mFotosAdapter;
    private ProgressBar mProgressBarFotos;
    private ImageButton mFotosPickerButton;
    private Button mSendFotosButton;
    private EditText mFotosEditText;


    private String mUsername;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabaseFotos;
    private DatabaseReference mMessagesDatabaseReferenceFotos;
    private ChildEventListener mChildEventListenerFotos;
    private FirebaseAuth mFirebaseAuthFotos;
    private FirebaseAuth.AuthStateListener mAuthStateListenerfotos;
    private FirebaseStorage mFirebaseStoragefotos;
    private StorageReference mChatPhotosStorageReferenceFotos;
    private FirebaseRemoteConfig mFirebaseRemoteConfigFotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);

        mUsername = ANONYMOUS;

        // Initialize Firebase components
        mFirebaseDatabaseFotos = FirebaseDatabase.getInstance();
        mFirebaseAuthFotos = FirebaseAuth.getInstance();
        mFirebaseStoragefotos = FirebaseStorage.getInstance();
        mFirebaseRemoteConfigFotos = FirebaseRemoteConfig.getInstance();

        mMessagesDatabaseReferenceFotos = mFirebaseDatabaseFotos.getReference().child("fotos");
        mChatPhotosStorageReferenceFotos = mFirebaseStoragefotos.getReference().child("fotos_activity");

        // Initialize references to views
        mProgressBarFotos = (ProgressBar) findViewById(R.id.progressBar_fotos_activity);
        mFotosListView = (ListView) findViewById(R.id.fotosListView);
        mFotosPickerButton = (ImageButton) findViewById(R.id.add_fotos_btn);
        mSendFotosButton = (Button) findViewById(R.id.send_fotos_btn);

        // Initialize message ListView and its adapter
        List<FotosMessage> fotosMessages = new ArrayList<>();
        mFotosAdapter = new FotosAdapter(this, R.layout.fotos_message, fotosMessages);
        //mFotosAdapter = new FotosAdapter(this, R.layout.fotos_message, fotosMessages);
        mFotosListView.setAdapter(mFotosAdapter);

        // Initialize progress bar
        mProgressBarFotos.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mFotosPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

        // Enable Send button when there's text to send
        mFotosEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendFotosButton.setEnabled(true);
                } else {
                    mSendFotosButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mFotosEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_FOTOS_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendFotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FotosMessage fotosMessage = new FotosMessage(mFotosEditText.getText().toString(), mUsername, null);
                mMessagesDatabaseReferenceFotos.push().setValue(fotosMessage);

                // Clear input box
                mFotosEditText.setText("");
            }
        });

        mAuthStateListenerfotos = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    // User is signed out
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

        // Create Remote Config Setting to enable developer mode.
        // Fetching configs from the server is normally limited to 5 requests per hour.
        // Enabling developer mode allows many more requests to be made per hour, so developers
        // can test different config values during development.
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfigFotos.setConfigSettings(configSettings);

        // Define default config values. Defaults are used when fetched config values are not
        // available. Eg: if an error occurred fetching values from the server.
        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put(FOTOS_LENGTH_KEY, DEFAULT_FOTOS_LENGTH_LIMIT);
        mFirebaseRemoteConfigFotos.setDefaults(defaultConfigMap);
        fetchConfig();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            // Get a reference to store file at chat_photos/<FILENAME>
            StorageReference photoRef = mChatPhotosStorageReferenceFotos.child(selectedImageUri.getLastPathSegment());

            // Upload file to Firebase Storage
            photoRef.putFile(selectedImageUri)
                    .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // When the image has successfully uploaded, we get its download URL
                            @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                            // Set the download URL to the message box, so that the user can send it to the database
                            FotosMessage fotosMessage = new FotosMessage(null, mUsername, downloadUrl.toString());
                            mMessagesDatabaseReferenceFotos.push().setValue(fotosMessage);
                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuthFotos.addAuthStateListener(mAuthStateListenerfotos);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListenerfotos != null) {
            mFirebaseAuthFotos.removeAuthStateListener(mAuthStateListenerfotos);
        }
        mFotosAdapter.clear();
        detachDatabaseReadListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fotos_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu_fotos:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onSignedInInitialize(String username) {
        mUsername = username;
        attachDatabaseReadListener();
    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
        mFotosAdapter.clear();
        detachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListenerFotos == null) {
            mChildEventListenerFotos = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FotosMessage fotosMessage = dataSnapshot.getValue(FotosMessage.class);
                    mFotosAdapter.add(fotosMessage);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mMessagesDatabaseReferenceFotos.addChildEventListener(mChildEventListenerFotos);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListenerFotos != null) {
            mMessagesDatabaseReferenceFotos.removeEventListener(mChildEventListenerFotos);
            mChildEventListenerFotos = null;
        }
    }

    // Fetch the config to determine the allowed length of messages.
    public void fetchConfig() {
        long cacheExpiration = 3600; // 1 hour in seconds
        // If developer mode is enabled reduce cacheExpiration to 0 so that each fetch goes to the
        // server. This should not be used in release builds.
        if (mFirebaseRemoteConfigFotos.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfigFotos.fetch(cacheExpiration)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Make the fetched config available
                        // via FirebaseRemoteConfig get<type> calls, e.g., getLong, getString.
                        mFirebaseRemoteConfigFotos.activateFetched();

                        // Update the EditText length limit with
                        // the newly retrieved values from Remote Config.
                        applyRetrievedLengthLimit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // An error occurred when fetching the config.
                        Log.w(TAG, "Error fetching config", e);

                        // Update the EditText length limit with
                        // the newly retrieved values from Remote Config.
                        applyRetrievedLengthLimit();
                    }
                });
    }


    private void applyRetrievedLengthLimit() {
        Long fotos_length = mFirebaseRemoteConfigFotos.getLong(FOTOS_LENGTH_KEY);
        mFotosEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(fotos_length.intValue())});
        Log.d(TAG, FOTOS_LENGTH_KEY + " = " + fotos_length);
    }
    */
}