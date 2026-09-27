package com.example.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
    pendingTaskList: List<String>,
    completedTaskList: List<String>,
    onTaskMovedToComplete: (String) -> Unit,
    onTaskMovedToPending: (String) -> Unit,
    onNavigateProfile: () -> Unit,
    onOpenModal: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) } // 0 = Home, 1 = Notifications, 2 = Settings
    var filterStatus by remember { mutableStateOf("PENDING") } // "PENDING" or "COMPLETED"

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            // Top Bar
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = "ToDoList", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFE0E0E0)).clickable { onNavigateProfile() }, contentAlignment = Alignment.Center) {
                    Text("👤", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Home Tab View
            if (selectedTab == 0) {
                // Completed & Pending Toggle Buttons
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { filterStatus = "COMPLETED" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (filterStatus == "COMPLETED") Color(0xFFD4EDDA) else Color(0xFFF0F2F5)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Completed", color = if (filterStatus == "COMPLETED") Color(0xFF155724) else Color.Gray)
                    }
                    Button(
                        onClick = { filterStatus = "PENDING" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (filterStatus == "PENDING") Color(0xFFD4EDDA) else Color(0xFFF0F2F5)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Pending", color = if (filterStatus == "PENDING") Color(0xFF155724) else Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Text(
                        text = if (filterStatus == "COMPLETED") "Completed Tasks" else "Pending Tasks",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    val currentList = if (filterStatus == "COMPLETED") completedTaskList else pendingTaskList

                    if (currentList.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No ${filterStatus.lowercase()} tasks", color = Color.Gray, fontSize = 14.sp)
                        }
                    } else {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(currentList) { task ->
                                Card(
                                    modifier = Modifier.fillMaxWidth().clickable {
                                        if (filterStatus == "PENDING") {
                                            onTaskMovedToComplete(task)
                                        } else {
                                            onTaskMovedToPending(task)
                                        }
                                    },
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = task,
                                            fontSize = 14.sp,
                                            textDecoration = if (filterStatus == "COMPLETED") TextDecoration.LineThrough else TextDecoration.None,
                                            color = if (filterStatus == "COMPLETED") Color.Gray else Color.Black
                                        )
                                        Text(
                                            text = if (filterStatus == "COMPLETED") "✅" else "⭕",
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (selectedTab == 1) {
                // Notifications Tab View
                Column(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🔔", fontSize = 40.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("No new notifications", fontSize = 16.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                }
            } else {
                // Settings Tab View
                Column(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F2F5))) {
                        Text("Dark Mode (Coming Soon)", modifier = Modifier.padding(16.dp), fontSize = 14.sp)
                    }
                    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F2F5))) {
                        Text("App Version 1.0.0", modifier = Modifier.padding(16.dp), fontSize = 14.sp)
                    }
                }
            }
        }

        // Floating Action Button for adding tasks (Only visible on Home tab)
        if (selectedTab == 0) {
            FloatingActionButton(
                onClick = onOpenModal,
                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 90.dp, end = 24.dp),
                containerColor = Color(0xFF00B894),
                contentColor = Color.White
            ) {
                Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Bottom Navigation Bar
        Surface(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(70.dp),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { selectedTab = 0 }) {
                    Text("🏠", fontSize = 22.sp, color = if (selectedTab == 0) Color(0xFF00B894) else Color.Gray)
                }
                IconButton(onClick = { selectedTab = 1 }) {
                    Text("🔔", fontSize = 22.sp)
                }
                IconButton(onClick = { selectedTab = 2 }) {
                    Text("⚙️", fontSize = 22.sp)
                }
            }
        }
    }
}