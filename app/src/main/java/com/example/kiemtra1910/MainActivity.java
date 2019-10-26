package com.example.kiemtra1910;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnTTsach;
    GridView gridThongtin;
    DBHelper dbHelper;
    Dialog dialog;
    EditText id_Book, title, id_author;
    Button bt_save, bt_select, bt_exit, bt_update, bt_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTTsach = (Button) findViewById(R.id.btnThongtinsach);
        btnTTsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenuDialog();
            }
        });
        
    }

    private void showMenuDialog() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_book);
        dialog.setTitle("Thông tin sách");

         id_Book= (EditText)dialog.findViewById(R.id.edtNhapmasach);
        title = (EditText)dialog.findViewById(R.id.edtNhaptieude);
        id_author = (EditText)dialog.findViewById(R.id.edtTentacgia);
        gridThongtin = (GridView)dialog.findViewById(R.id.grvList);
        dbHelper = new DBHelper(this);
        bt_delete=(Button)dialog.findViewById(R.id.btnDelete);
        bt_save = (Button)dialog.findViewById(R.id.btnSave);
        bt_update=(Button)dialog.findViewById(R.id.btnUpdate);



        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập tiêu đề sách", Toast.LENGTH_SHORT).show();
                }
                else if (id_author.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập id tác giả", Toast.LENGTH_SHORT).show();
                }
                else{
                    Book book = new Book();
                    book.setBook_id(Integer.parseInt(id_Book.getText().toString()));
                    book.setTitle(title.getText().toString());

                    book.setAuthor_id(Integer.parseInt(id_author.getText().toString()));

                        if (dbHelper.insertBook(book))
                            Toast.makeText(getApplicationContext(), "Đã lưu thành công", Toast.LENGTH_SHORT).show();

                        else
                            Toast.makeText(getApplicationContext(), "Không lưu thành công", Toast.LENGTH_SHORT).show();
                }
            }


        });

        bt_select = (Button)dialog.findViewById(R.id.btnSelect);
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> books = new ArrayList<>();
                books = dbHelper.getAllBook();
                for (Book b : books) {
                    list.add(b.getBook_id() + "");
                    list.add(b.getTitle() + "");
                    list.add(b.getAuthor_id() + "");
                }
                Adapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gridThongtin.setAdapter((ListAdapter) adapter);
                if (gridThongtin.getCount()==0){
                    Toast.makeText(getApplicationContext(),"Danh sách rỗng",Toast.LENGTH_SHORT).show();
                }

            }
        });


        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean st=dbHelper.deleteBook(Integer.parseInt(id_Book.getText().toString()));

                if (st==true)
                    Toast.makeText(getApplicationContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(getApplicationContext(),"Xóa thất bại",Toast.LENGTH_SHORT).show();
                }


                ArrayList<String> list = new ArrayList<>();
                ArrayList<Book> books = new ArrayList<>();
                books = dbHelper.getAllBook();
                for (Book b : books) {
                    list.add(b.getBook_id() + "");
                    list.add(b.getTitle() + "");
                    list.add(b.getAuthor_id() + "");
                }
                Adapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gridThongtin.setAdapter((ListAdapter) adapter);
                id_Book.setText("");
                title.setText("");
                id_author.setText("");
                bt_delete.setEnabled(false);
            }
        });


        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (title.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập tiêu đề sách", Toast.LENGTH_SHORT).show();
                }
                else if (id_author.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập id tác giả", Toast.LENGTH_SHORT).show();
                }
                else{
                    Book book = dbHelper.getBook(Integer.parseInt(id_Book.getText().toString()));
                    book.setBook_id(Integer.parseInt(id_Book.getText().toString()));
                    book.setTitle(title.getText().toString());
                    book.setAuthor_id(Integer.parseInt(id_author.getText().toString()));
                    if (dbHelper.updateBook(book))
                        Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                    else
                        Toast.makeText(getApplicationContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });




        dialog.show();


    }


}

