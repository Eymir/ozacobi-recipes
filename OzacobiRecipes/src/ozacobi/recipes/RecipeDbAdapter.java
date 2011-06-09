package ozacobi.recipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class RecipeDbAdapter {

    public static final String KEY_NAME = "name";

    @SuppressWarnings("unused")
	private static final String TAG = "RecipeDbAdapter";
    
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_CREATE =
        "create table recipes (key integer primary key autoincrement, "
        + "name text not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "recipes";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;

    public static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			int x = 1;
			
		}

    }

    public RecipeDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }
    
    public RecipeDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    public boolean createRecipe(String name) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);

        mDb.insert(DATABASE_TABLE, null, initialValues);
        return true;
    }


    public Cursor fetchAllRecipes() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_NAME}, null, null, null, null, null);
    }

    public Cursor fetchRecipe(String name) throws SQLException {

        Cursor mCursor =
            mDb.query(true, DATABASE_TABLE, new String[] {KEY_NAME}, KEY_NAME + "=" + name, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

}
