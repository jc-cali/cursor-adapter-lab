package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Intent mDetailIntent;
    ListView mGroceryList;
    Cursor mCursor;
    AdapterView.OnItemClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db;
        db = openOrCreateDatabase("Grocery.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);

        db.setVersion(1);
        db.setLocale(Locale.getDefault());

        String names[] = {"Frosted Flakes", "Oscar Meyer", "Van De Camp", "Silk"};
        String descriptions[] = {"Sugar-frosted flake cereal","Mystery meat encased hot dogs","Pork and Beans","Soy milk"};
        String prices[] = {"2.99","3.99","1.99","3.99"};
//        Double price[] = {2.99,3.99,1.99,3.99};
        String types[] = {"Cereal","Meat","Canned Goods","Dairy"};

        /* CREATE TABLE IF NOT EXISTS grocery_list
            (_id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            description TEXT,
            price REAL,
            type TEXT);
       */

        String createTableSql = "CREATE TABLE IF NOT EXISTS grocery_list\n" +
                "            (_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "            name TEXT,\n" +
                "            description TEXT,\n" +
                "            price TEXT,\n" +
                "            type TEXT);";

        db.execSQL(createTableSql);

        // for each item in my [], let us INSERT the sql
//        for (int idx = 0; idx < names.length; idx++) {
//            /*
//            INSERT INTO grocery_list Values (null, "name", "description", 5.99, "type");
//             */
//            String temporaryQuery = "INSERT INTO grocery_list Values (null, '" + names[idx] + "', '" +
//                    descriptions[idx] + "','" + prices[idx] + "','" + types[idx] + "');";
//            db.execSQL(temporaryQuery);
//        }

        mCursor = db.query("grocery_list", null, null, null, null, null, null, null);
        mGroceryList = (ListView) findViewById(R.id.shopping_list_view);

        CursorAdapter mCursorAdapter = new CursorAdapter(MainActivity.this, mCursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,
                        parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txt = (TextView) view.findViewById(android.R.id.text1);
                String rowData = cursor.getString(cursor.getColumnIndex("name"));
                txt.setText(rowData);
            }
        };

        mClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDetailIntent = new Intent(MainActivity.this, GroceryDetailActivity.class);

                String name = mCursor.getString(mCursor.getColumnIndex("name"));
                String description = mCursor.getString(mCursor.getColumnIndex("description"));
                String price = mCursor.getString(mCursor.getColumnIndex("price"));
                String type = mCursor.getString(mCursor.getColumnIndex("type"));
                mDetailIntent.putExtra("NAME", name);
                mDetailIntent.putExtra("DESCRIPTION", description);
                mDetailIntent.putExtra("PRICE", price);
                mDetailIntent.putExtra("TYPE", type);
                startActivity(mDetailIntent);
            }
        };

        mGroceryList.setAdapter(mCursorAdapter);
        mGroceryList.setOnItemClickListener(mClickListener);



        //Ignore the two lines below, they are for setup
        //DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        //dbSetup.getReadableDatabase();

    }
}
