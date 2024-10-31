package com.example.happybirthday

import StudentAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // Thêm danh sách sinh viên mẫu ở đây
    private lateinit var studentAdapter: StudentAdapter
    private var students: List<Student> = listOf(
        Student("Nguyen Van A", "123456"),
        Student("Le Thi B", "234567"),
        Student("Pham Van C", "345678"),
        Student("Trịnh Hoàng Quân", "20204850"),
        Student("THg Quân", "20204850")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Bài 1. Danh sách đơn giản
//        val edtNumber = findViewById<EditText>(R.id.edtNumber)
//        val radioEven = findViewById<RadioButton>(R.id.radioEven)
//        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
//        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
//        val btnShow = findViewById<Button>(R.id.btnShow)
//        val tvError = findViewById<TextView>(R.id.tvError)
//        val listView = findViewById<ListView>(R.id.listView)
//
//        btnShow.setOnClickListener {
//            val input = edtNumber.text.toString()
//            tvError.visibility = TextView.GONE
//            val numbers = mutableListOf<Int>()
//
//            if (input.isEmpty()) {
//                tvError.text = "Vui lòng nhập một số nguyên dương."
//                tvError.visibility = TextView.VISIBLE
//                return@setOnClickListener
//            }
//
//            val n = input.toIntOrNull()
//            if (n == null || n <= 0) {
//                tvError.text = "Vui lòng nhập một số nguyên dương hợp lệ."
//                tvError.visibility = TextView.VISIBLE
//                return@setOnClickListener
//            }
//
//            when {
//                radioEven.isChecked -> {
//                    for (i in 0..n step 2) numbers.add(i)
//                }
//                radioOdd.isChecked -> {
//                    for (i in 1..n step 2) numbers.add(i)
//                }
//                radioSquare.isChecked -> {
//                    var i = 0
//                    while (i * i <= n) {
//                        numbers.add(i * i)
//                        i++
//                    }
//                }
//                else -> {
//                    tvError.text = "Vui lòng chọn một loại số."
//                    tvError.visibility = TextView.VISIBLE
//                    return@setOnClickListener
//                }
//            }
//
//            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
//            listView.adapter = adapter
//            }



//        Bài 2. Tìm kiếm trong danh sách
        studentAdapter = StudentAdapter(students)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Xử lý tìm kiếm
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString()
                if (keyword.length > 2) {
                    val filteredStudents = students.filter {
                        it.name.contains(keyword, ignoreCase = true) ||
                                it.studentId.contains(keyword, ignoreCase = true)
                    }
                    studentAdapter.updateList(filteredStudents)
                } else {
                    studentAdapter.updateList(students)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

    }
}