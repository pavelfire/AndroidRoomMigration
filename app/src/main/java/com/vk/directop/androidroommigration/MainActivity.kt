package com.vk.directop.androidroommigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.vk.directop.androidroommigration.ui.theme.AndroidRoomMigrationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "users.db"
        )
            .addMigrations(UserDatabase.migration3To4)
            .build()

        lifecycleScope.launch {
            db.dao.getSchools().forEach(::println)
        }

//        lifecycleScope.launch {
//            db.dao.getUsers().forEach(::println)
//        }

//        (10..12).forEach {
//            lifecycleScope.launch{
//                db.dao.insertUser(
//                    User(
//                        email = "test@test$it.com",
//                        username = "Test-$it"
//                    )
//                )
//            }
//        }

        (10..12).forEach {
            lifecycleScope.launch {
                db.dao.insertSchool(
                    School(
                        name = "Test-$it"
                    )
                )
            }
        }

    }
}

