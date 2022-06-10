package com.e.codingpractice.dfs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DFSViewModel : ViewModel() {
    var n by mutableStateOf(0)
    var m by mutableStateOf(0)
    var sum by mutableStateOf(-1)
    var graph = Array(101) { Array(101) { 0 } }
    var visit = Array(101) { 0 }
    var links = mutableStateListOf<Links>()

    fun reset() {
        graph.fill(Array(101) { 0 })
        visit.fill(0)
        n = 0
        m = 0
        sum = -1
        links.clear()
    }

    fun add() {
        links.clear()
        links.addAll(List(m) { Links() })
    }

    fun runDfs() {
        for (i in 0 until m) {
            if (links[i].error.value) {
                graph.fill(Array(101) { 0 })
                return
            }
            graph[links[i].first.value][links[i].second.value] = 1
            graph[links[i].second.value][links[i].first.value] = 1
        }
        dfs(1)
        for (i in visit) {
            sum += i
        }
    }

    private fun dfs(x: Int) {
        visit[x] = 1
        for (i in 1..n) {
            if (visit[i] == 1 || graph[x][i] == 0) {
                continue
            }
            dfs(i)
        }
    }
}