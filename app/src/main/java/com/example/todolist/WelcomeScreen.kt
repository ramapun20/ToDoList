package com.example.todolist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(onNavigateSignIn: () -> Unit, onNavigateSignUp: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Box(modifier = Modifier.fillMaxWidth().height(45.dp).background(Color(0xFF00B894)))

        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Welcome", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Image(
                painter = painterResource(id = R.drawable.welcomeillustration),
                contentDescription = "Welcome Illustration",
                modifier = Modifier.size(220.dp).padding(vertical = 10.dp)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Welcome to ToDoList", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Your personal task organizer", fontSize = 13.sp, color = Color.Gray)
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onNavigateSignUp,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B894)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Get Started", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onNavigateSignIn,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B894)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "I already have an account", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}