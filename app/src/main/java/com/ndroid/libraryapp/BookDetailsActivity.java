package com.ndroid.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView ivCover;
    TextView tvTitle, tvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivCover = findViewById(R.id.ivCover);
        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);

        //Book book = (Book) getIntent().getSerializableExtra("book");
        int book_id = getIntent().getIntExtra("book_id", 0);

        getBookDetails(book_id);
    }

    private void getBookDetails(int book_id) {

        Ion.with(this)
                .load("http://10.0.2.2:8888/BooksApi/get_book_by_id.php?id="+book_id)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e == null) {
                            Gson gson = new Gson();
                            Book book = gson.fromJson(result.toString(), Book.class);
                            tvTitle.setText(book.getTitle());
                            tvAuthor.setText(book.getAuthor());
                            Picasso.get().load(book.getImage()).into(ivCover);

                        } else {
                            Toast.makeText(BookDetailsActivity.this, "server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
