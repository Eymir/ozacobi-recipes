package ozacobi.recipes;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RecipeList extends ListActivity 
{
	
    public void showList(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	
    	String[] recipes = getResources().getStringArray(R.array.recipes_array);
        setListAdapter((ListAdapter) new ArrayAdapter<String>(this, R.layout.list_item, recipes));
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
            {
	            // When clicked, show a toast with the TextView text
	            Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_SHORT).show();
            	//TextView textview = new TextView(context);
                //textview.setText(((TextView) view).getText());
                //setContentView(textview);
	            //setContentView(R.layout.main);
            }
        });
    }
	
	
	
}
