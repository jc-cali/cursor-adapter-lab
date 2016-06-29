package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.R;

public class GroceryDetailActivity extends AppCompatActivity {
    TextView mTxtName;
    TextView mTxtDescription;
    TextView mTxtPrice;
    TextView mTxtType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_detail);

        mTxtName = (TextView) findViewById(R.id.txt_name);
        mTxtDescription = (TextView) findViewById(R.id.txt_description);
        mTxtPrice = (TextView) findViewById(R.id.txt_price);
        mTxtType = (TextView) findViewById(R.id.txt_type);

        Intent listIntent = getIntent();
        String name = listIntent.getStringExtra("NAME");
        String description = listIntent.getStringExtra("DESCRIPTION");
        String price = listIntent.getStringExtra("PRICE");
        String type = listIntent.getStringExtra("TYPE");

        mTxtName.setText(name);
        mTxtDescription.setText(description);
        mTxtPrice.setText(price);
        mTxtType.setText(type);

    }
}
