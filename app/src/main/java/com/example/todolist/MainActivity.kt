package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListApp()
        }
    }
}

@Composable
fun ToDoListApp() {
    var currentScreen by remember { mutableStateOf(Screen.WELCOME) }
    var isTaskModalOpen by remember { mutableStateOf(false) }

    // Separate lists for Pending and Completed tasks
    val pendingTaskList = remember { mutableStateListOf("Design user flow") }
    val completedTaskList = remember { mutableStateListOf<String>() }

    var userName by remember { mutableStateOf("Pun Rama") }
    var userEmail by remember { mutableStateOf("ramapun@gmail.com") }
    var userPhone by remember { mutableStateOf("010 6856 4968") }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF0F2F5))) {
        when (currentScreen) {
            Screen.WELCOME -> WelcomeScreen(
                onNavigateSignIn = { currentScreen = Screen.SIGN_IN },
                onNavigateSignUp = { currentScreen = Screen.SIGN_UP }
            )
            Screen.SIGN_IN -> SignInScreen(
                onNavigateDashboard = { currentScreen = Screen.DASHBOARD }
            )
            Screen.SIGN_UP -> SignUpScreen(
                onNavigateDashboard = { currentScreen = Screen.DASHBOARD }
            )
            Screen.DASHBOARD -> DashboardScreen(
                pendingTaskList = pendingTaskList,
                completedTaskList = completedTaskList,
                onTaskMovedToComplete = { task ->
                    val index = pendingTaskList.indexOf(task)
                    if (index != -1) {
                        pendingTaskList.removeAt(index)
                    }
                    if (!completedTaskList.contains(task)) {
                        completedTaskList.add(task)
                    }
                },
                onTaskMovedToPending = { task ->
                    val index = completedTaskList.indexOf(task)
                    if (index != -1) {
                        completedTaskList.removeAt(index)
                    }
                    if (!pendingTaskList.contains(task)) {
                        pendingTaskList.add(task)
                    }
                },
                onNavigateProfile = { currentScreen = Screen.PROFILE },
                onOpenModal = { isTaskModalOpen = true }
            )
            Screen.PROFILE -> ProfileScreen(
                userName = userName,
                userEmail = userEmail,
                userPhone = userPhone,
                onNavigateDashboard = { currentScreen = Screen.DASHBOARD },
                onNavigateEditProfile = { currentScreen = Screen.EDIT_PROFILE }
            )
            Screen.EDIT_PROFILE -> EditProfileScreen(
                currentName = userName,
                currentEmail = userEmail,
                currentPhone = userPhone,
                onSaveProfile = { newName, newEmail, newPhone ->
                    userName = newName
                    userEmail = newEmail
                    userPhone = newPhone
                },
                onBack = { currentScreen = Screen.PROFILE }
            )
        }

        if (isTaskModalOpen) {
            TaskModal(
                onClose = { isTaskModalOpen = false },
                onTaskCreated = { newTask ->
                    pendingTaskList.add(newTask)
                }
            )
        }
    }
}