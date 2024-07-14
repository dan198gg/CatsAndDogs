package com.example.catsanddogs

import android.media.Image
import android.os.Bundle
import android.text.Layout.Alignment
import android.util.Log
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsanddogs.ui.theme.CatOrDog
import com.example.catsanddogs.ui.theme.CatsAndDogsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//
//        mutableListOf(CatOrDog(type = "Сибирский хаски", img = R.drawable.haski),
//            CatOrDog(type = "Японский бобтейл", img = R.drawable.japanesecat))
        enableEdgeToEdge()
        setContent {
            val mutableCat= MutableSharedFlow<Int>()
            val stateCat=mutableCat.asSharedFlow()
            var list= mutableListOf(CatOrDog(R.drawable.haski,"Хаски"), CatOrDog(R.drawable.akitainy,"акита-ину"),
                CatOrDog(R.drawable.japanesecat,"Японский кот")
            )
            val corScope= rememberCoroutineScope()
            LaunchedEffect(key1 = "") {
                corScope.launch {
                    repeat(list.size){
                        mutableCat.emit(it)
                    }
                }
            }
            cardScroll(stateCat,mutableCat,list)
            }
        }
    }



@Composable
fun cardScroll(flow:SharedFlow<Int>,mutableStateFlow: MutableSharedFlow<Int>,list: MutableList<CatOrDog>) {
    val catNum by flow.collectAsState(initial = 0)
    var cat = remember {
        mutableStateListOf<CatOrDog>()
    }
    val boxVisible = remember { mutableStateOf(true) }

    var corScope = rememberCoroutineScope()
    LaunchedEffect(key1 = "") {
        corScope.launch {
            flow.collect {
                cat.add(list[it])
                delay(1000)
            }

        }
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
    ) {
        items(catNum) {
            AnimatedVisibility(
                visible = boxVisible.value,
                Modifier.padding(40.dp),
                enter = fadeIn(),
                exit = fadeOut()
            ) {


                Card(Modifier.padding(30.dp)) {
                    androidx.compose.foundation.Image(
                        painter = painterResource(id = cat[it].img),
                        contentDescription = "0",
                        modifier = Modifier.padding(30.dp)
                    )
                    Text(
                        text = cat[it].type,
                        Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally),
                        fontSize = 25.sp
                    )
                }

            }
        }
    }
}



