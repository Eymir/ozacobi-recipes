package ozacobi.recipes;

import ozacobi.recipes.RecipeDbAdapter.DatabaseHelper;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
//import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ozacobirecipes extends Activity {
    /** Called when the activity is first created. */
	private int recipeNum = 0;
	private static RecipeDbAdapter mDbHelper;
	private DataFiller df;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new RecipeDbAdapter(this);
        mDbHelper.open();
        df = new DataFiller();
        setContentView(R.layout.main);

    }
    
    public void createRecipe(View view) {
    	setContentView(R.layout.test);
        String recipeName = "Note " + recipeNum++;
        mDbHelper.createRecipe(recipeName);
        //df.fillData();
    }
    public static class DataFiller extends ListActivity{
    	
    	DataFiller(){}
    	
	    public void fillData() {
	        // Get all of the notes from the database and create the item list
	        Cursor c = mDbHelper.fetchAllRecipes();
	        startManagingCursor(c);
	
	        String[] from = new String[] { RecipeDbAdapter.KEY_NAME };
	        int[] to = new int[] { R.id.text1 };
	        
	        // Now create an array adapter and set it to display using our row
	        SimpleCursorAdapter recipes =
	            new SimpleCursorAdapter(this, R.layout.recipes_row, c, from, to);
	        setListAdapter(recipes);
	    }
    }
    public void viewMain(View view)
    {
    	setContentView(R.layout.main);
    }
    
    public void view(View view)
    {
    	setContentView(R.layout.test);
    }
    
    public void viewIngredients(View view)
    {
    	setContentView(R.layout.ingredients);
    }
    
    public void viewRecipes(View view)
    {
    	setContentView(R.layout.recipes);
    	df.fillData();
    }
    
    
}