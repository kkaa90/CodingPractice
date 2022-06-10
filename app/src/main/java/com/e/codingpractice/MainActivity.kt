package com.e.codingpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.e.codingpractice.dfs.DFSViewModel
import com.e.codingpractice.dfs.TestDFS
import com.e.codingpractice.ui.theme.CodingPracticeTheme
import com.e.codingpractice.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}

@Composable
fun MainView(routeAction: RouteAction) {
    Column(Modifier.fillMaxSize()) {
        TextButton(onClick = { routeAction.navTo(NAVROUTE.DFS) },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, shape = RoundedCornerShape(8.dp), color = Purple80),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = NAVROUTE.DFS.description, fontSize = 50.sp)
        }
    }
}

enum class NAVROUTE(val routeName: String, val description: String) {
    MAIN("main", "메인"),
    DFS("dfs", "깊이우선탐색")
}

class RouteAction(navHostController: NavHostController) {
    val navTo: (NAVROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }
    val goBack: () -> Unit = {
        navHostController.popBackStack()
    }
}

@Composable
fun NavigationGraph(starting: String = NAVROUTE.MAIN.routeName) {
    val navController = rememberNavController()
    val routeAction = remember(navController) { RouteAction(navController) }
    val dfsViewModel: DFSViewModel = viewModel()
    NavHost(navController, starting) {
        composable(NAVROUTE.MAIN.routeName) {
            MainView(routeAction = routeAction)
        }
        composable(NAVROUTE.DFS.routeName) {
            TestDFS(dfsViewModel, routeAction)
        }
    }
}