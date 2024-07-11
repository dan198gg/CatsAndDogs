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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            cardScroll()




}
}
        }



@Composable
fun cardScroll(){

    var flow1 = getCatsOrDogs()
    var corScope = rememberCoroutineScope()
    var dog1 by rememberSaveable {
        mutableStateOf<MutableList<CatOrDog>>(mutableListOf())
    }
    var i=0
    LaunchedEffect(key1 = dog1) {
        corScope.launch {
            flow1.collect {

                dog1.add(it)
                Log.i("CAT",it.type.toString())
                Log.i("CAT2",dog1[i].type.toString())
                i+=1
            }
        }
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
) {
        items(dog1.size){
            Card(Modifier.padding(30.dp)) {
                Log.i("CARD",dog1.size.toString())
                androidx.compose.foundation.Image(painter = painterResource(id = dog1[it].img), contentDescription = "0", modifier = Modifier.padding(30.dp))
                Text(text =dog1[it].type ,Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally), fontSize = 25.sp)
            }
        }
    }
}



fun getCatsOrDogs() : Flow<CatOrDog> = flow {
    var dog= mutableListOf(CatOrDog(type = "Акита-ину", img = R.drawable.akitainy),
        CatOrDog(type = "Сибирский хаски", img = R.drawable.haski),
        CatOrDog(type = "Японский бобтейл", img = R.drawable.japanesecat)
    )
    repeat(dog.size){
        emit(dog[it])
    }
}
