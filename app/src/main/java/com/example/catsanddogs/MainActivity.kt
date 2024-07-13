package com.example.catsanddogs

import android.media.Image
import android.os.Bundle
import android.text.Layout.Alignment
import android.util.Log
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsanddogs.ui.theme.CatOrDog
import com.example.catsanddogs.ui.theme.CatsAndDogsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val mutableCat= MutableStateFlow<MutableList<CatOrDog>>(
            mutableListOf(CatOrDog(type = "Сибирский хаски", img = R.drawable.haski),
            CatOrDog(type = "Японский бобтейл", img = R.drawable.japanesecat)))
        val stateCat=mutableCat.asStateFlow()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            cardScroll(stateCat,mutableCat)




}
}
        }



@Composable
fun cardScroll(flow:StateFlow<MutableList<CatOrDog>>,mutableStateFlow: MutableStateFlow<MutableList<CatOrDog>>){
    var cat=flow.collectAsState()
    var corScope = rememberCoroutineScope()
    LaunchedEffect(key1 = "") {
        corScope.launch {
            flow.collect()
        }
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
) {
        items(flow.value.size){
            Card(Modifier.padding(30.dp)) {
                androidx.compose.foundation.Image(painter = painterResource(id = cat.value[it].img), contentDescription = "0", modifier = Modifier.padding(30.dp))
                Text(text =cat.value[it].type ,Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally), fontSize = 25.sp)
            }
        }
    }
}



