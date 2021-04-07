package com.example.bai4c7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.FiboAdapter;
import com.example.model.Fibonacci;

public class MainActivity extends AppCompatActivity {
    Button btnOk;
    EditText edtNumber;
    TextView txtResult;
    ListView lvFibonacci;
    int n = 0;
    FiboAdapter fiboAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = Integer.parseInt(edtNumber.getText().toString());
                ButtonTask task = new ButtonTask();
                task.execute(n);
            }
        });
    }

    private void addControls() {
        btnOk = findViewById(R.id.btnOk);
        edtNumber = findViewById(R.id.edtNumber);
        txtResult = findViewById(R.id.txtResult);
        lvFibonacci = findViewById(R.id.lvFibonacci);
        fiboAdapter = new FiboAdapter(MainActivity.this,R.layout.lv_fibo);
        lvFibonacci.setAdapter(fiboAdapter);
    }
    class ButtonTask extends AsyncTask<Integer,Integer,Integer>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            fiboAdapter.clear();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            txtResult.setText(integer+"");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int t1 = values[0];
            fiboAdapter.add(new Fibonacci(t1));
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int n = integers[0];
            int t1 = 0;
            int t2 = 1;
            int sumofall = 0;
            for(int i=0;i<n;i++)
            {
                int sum = t1+t2;
                t1 = t2;
                t2 = sum;
                sumofall+=t1;
                publishProgress(t1);
                SystemClock.sleep(150);
            }
            return sumofall;
        }
    }
}