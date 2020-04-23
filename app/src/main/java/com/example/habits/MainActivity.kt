package com.example.habits

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habits.Strings.HABIT_CREATE
import com.example.habits.Strings.HABIT_EDIT_POSITION
import com.example.habits.Strings.HABIT_CREATE_ACTIVITY_REQUEST_CODE
import com.example.habits.Strings.HABIT_EDIT_ACTIVITY_REQUEST_CODE

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private val habits = ArrayList<Habit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val savedHabits = savedInstanceState?.getParcelableArrayList<Habit>(HABITS_STATE)
        if (savedHabits != null)
            habits.addAll(savedHabits)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(habits)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            startActivityForResult(Intent(this, HabitActivity::class.java), HABIT_CREATE_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(HABITS_STATE, habits)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val habit = data?.extras?.getParcelable<Habit>(HABIT_CREATE)
        val position = data?.extras?.getInt(HABIT_EDIT_POSITION)
        when(requestCode){
            HABIT_CREATE_ACTIVITY_REQUEST_CODE -> handleHabitCreation(habit)
            HABIT_EDIT_ACTIVITY_REQUEST_CODE -> handleHabitEditing(habit, position)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleHabitCreation(habit: Habit?){
        if (habit != null) {
            habits.add(habit)
            adapter.notifyDataSetChanged()
        }
    }

    private fun handleHabitEditing(habit: Habit?, position: Int?){
        if (habit != null) {
            habits[position as Int] = habit
            adapter.notifyItemChanged(position)
        }
    }

    companion object{
        private const val HABITS_STATE = "HABITS_STATE"
    }}
