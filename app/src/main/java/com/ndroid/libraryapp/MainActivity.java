package com.ndroid.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvBooks;
    RecyclerBookAdapter recyclerBookAdapter;

    ArrayList<Book> bookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBooks = findViewById(R.id.rvBooks);

        bookArrayList = new ArrayList<>();

        recyclerBookAdapter = new RecyclerBookAdapter();
        recyclerBookAdapter.setBookArrayList(bookArrayList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvBooks.setLayoutManager(layoutManager);
        rvBooks.setAdapter(recyclerBookAdapter);

        loadBooksData();

        recyclerBookAdapter.setOnBookClickListener(new RecyclerBookAdapter.OnBookClickListener() {
            @Override
            public void onBookClick(int position) {
                Intent openBookDetailsActivity = new Intent(MainActivity.this, BookDetailsActivity.class);
                Book book = bookArrayList.get(position);
                openBookDetailsActivity.putExtra("book_id", book.getId());
                //openBookDetailsActivity.putExtra("book", book);
                startActivity(openBookDetailsActivity);
            }
        });



    }


    private void loadBooksData() {

        // pour test sur un emulateur AVD : localhost devient : 10.0.0.2
        // pour gynomotion : localhost devient : 10.0.0.3

        Ion.with(this)
                .load("http://10.0.0.2:8888/BooksApi/get_books.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(e != null){
                            Toast.makeText(MainActivity.this, "error loading data from server!", Toast.LENGTH_SHORT).show();
                        }  else {
                            Gson gson = new Gson();
                            Book[] books = gson.fromJson(result.toString(), Book[].class);
                            for (Book book : books) {
                                bookArrayList.add(book);
                            }
                            recyclerBookAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


}


