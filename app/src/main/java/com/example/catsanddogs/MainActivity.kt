package com.example.catsanddogs

import android.media.Image
import android.os.Bundle
import android.text.Layout.Alignment
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsanddogs.ui.theme.CatOrDog
import com.example.catsanddogs.ui.theme.CatsAndDogsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var dog= mutableListOf(CatOrDog(type = "Акита-ину", img = R.drawable.akitainy),
                CatOrDog(type = "Сибирский хаски", img = R.drawable.haski)
            )
            cardScroll(list = dog)
        }
    }
}


@Composable
fun cardScroll(list: MutableList<CatOrDog>){



    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
        repeat(list.size) {
            Card(Modifier.padding(30.dp)) {

                androidx.compose.foundation.Image(painter = painterResource(id = list[it].img), contentDescription = "0", modifier = Modifier.padding(30.dp))
                Text(text =list[it].type ,Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally), fontSize = 25.sp)
            }
        }
    }
}

