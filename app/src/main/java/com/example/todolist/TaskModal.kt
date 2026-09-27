package com.example.todolist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun TaskModal(onClose: () -> Unit, onTaskCreated: (String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onClose) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Create New Task", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Task title") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = status,
                    onValueChange = { status = it },
                    label = { Text("Status (e.g., To do)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        if (title.isNotBlank()) {
                            onTaskCreated(title) // Send the task back
                            onClose()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B894)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Save Task", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}