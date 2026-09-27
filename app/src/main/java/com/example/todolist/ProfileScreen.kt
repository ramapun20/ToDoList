package com.example.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String,
    userPhone: String,
    onNavigateDashboard: () -> Unit,
    onNavigateEditProfile: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(24.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onNavigateDashboard) {
                Text("Back", color = Color(0xFF00B894), fontWeight = FontWeight.Bold)
            }
            Text("Profile", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(40.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(70.dp).clip(CircleShape).background(Color(0xFFE0E0E0)), contentAlignment = Alignment.Center) {
                Text("👤", fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(userName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            ProfileFieldBox("👤 $userName")
            ProfileFieldBox("✉️ $userEmail")
            ProfileFieldBox("📞 $userPhone")
            ProfileFieldBox("🔒 **********")
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNavigateEditProfile,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B894)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Edit Profile", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ProfileFieldBox(text: String) {
    Box(
        modifier = Modifier.fillMaxWidth().height(48.dp).background(Color.White, shape = RoundedCornerShape(12.dp)).padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = text, fontSize = 14.sp, color = Color.DarkGray)
    }
}