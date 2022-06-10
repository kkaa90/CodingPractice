package com.e.codingpractice.dfs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.e.codingpractice.RouteAction


@Composable
fun TestDFS(viewModel: DFSViewModel, routeAction: RouteAction) {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "결과 : ${viewModel.sum}",
                Modifier
                    .fillMaxWidth()
                    .border(2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .weight(1f),
                fontSize = 20.sp
            )
            TextButton(onClick = { viewModel.runDfs() }) {
                Text(text = "실행")
            }
            TextButton(onClick = { viewModel.reset() }) {
                Text(text = "초기화")
            }
        }
        OutlinedTextField(
            value = if(viewModel.n!=0) viewModel.n.toString() else "",
            onValueChange = {
                if (it.isEmpty()||!it.isDigitsOnly()) {
                    viewModel.n = 0
                } else {
                    viewModel.n = it.toInt()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("노드 갯수") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true
        )
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = if(viewModel.m!=0) viewModel.m.toString() else "",
                onValueChange = {
                    if (it.isEmpty()||!it.isDigitsOnly()) {
                        viewModel.m = 0
                    } else {
                        viewModel.m = it.toInt()
                    }
                },
                modifier = Modifier.weight(1f),
                label = { Text(text = "노드 연결의 갯수") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true
            )
            IconButton(onClick = { viewModel.add() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "생성")
            }
        }
        LazyColumn {
            items(viewModel.links) { l ->
                DFSItems(links = l, viewModel)
            }
        }
    }
}

@Composable
fun DFSItems(links: Links, viewModel: DFSViewModel) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = if(links.first.value!=0)links.first.value.toString() else "",
            onValueChange = {
                if (it.isEmpty()||!it.isDigitsOnly()) {
                    links.first.value = 0
                } else {
                    if(it.toInt()>viewModel.n){
                        links.first.value=0
                    }
                    else {
                        links.first.value = it.toInt()

                    }

                }
                links.error.value = links.first.value==0||links.first.value>viewModel.n||links.first.value==links.second.value
            },
            isError = links.error.value,
            modifier = Modifier.width(150.dp),
            label = { Text("첫번째 노드") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true
        )
        OutlinedTextField(
            value = if(links.second.value!=0)links.second.value.toString() else "",
            onValueChange = {
                if (it.isEmpty()||!it.isDigitsOnly()) {
                    links.second.value = 0
                } else {
                    if(it.toInt()>viewModel.n){
                        links.second.value=0
                    }
                    else {
                        links.second.value = it.toInt()

                    }

                }
                links.error.value = links.second.value==0||links.second.value>viewModel.n||links.first.value==links.second.value
            },
            isError = links.error.value,
            modifier = Modifier.width(150.dp),
            label = { Text("두번째 노드") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true
        )
    }
}
