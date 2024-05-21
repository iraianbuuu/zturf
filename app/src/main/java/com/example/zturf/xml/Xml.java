package com.example.zturf.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zturf.R;
public class Xml extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);
        ListView listView = (ListView) findViewById(R.id.listView1);

        List<Turf> turfList = null;
        try {
            Helper parser = new Helper();
            InputStream is=getAssets().open("turf.xml");
            turfList = parser.parse(is);

            ArrayAdapter<Turf> adapter =new ArrayAdapter<Turf>
                    (this,android.R.layout.simple_list_item_1, turfList);
            listView.setAdapter(adapter);

        } catch (IOException e) {e.printStackTrace();}

}
}
