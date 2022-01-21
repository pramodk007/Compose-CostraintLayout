package com.example.compose_costraintlayout_v1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.compose_costraintlayout_v1.ui.theme.ComposeCostraintLayoutv1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayoutUsingConstraintSet()
    }
    //ConstraintLayoutExample()
    //ConstraintLayoutExampleTwo()

}

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        val (logo, topBar, companyName) = createRefs()

        TopAppBar(
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            backgroundColor = Color.Blue
        ) {
            Text(
                text = "Geeks for Geeks | Constraint Layout", color = Color.White
            )
        }

        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "background",
            modifier = Modifier
                .size(70.dp)
                .constrainAs(logo) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = "some text..",
            color = Color.Red,
            fontSize = 50.sp,
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(companyName) {
                start.linkTo(parent.start)
                top.linkTo(logo.bottom)
                end.linkTo(parent.end)
            }
        )
    }

}

@Composable
fun ConstraintLayoutExampleTwo() {
    ConstraintLayout {
        val (button1, button2, text1) = createRefs()
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(stringResource(id = R.string.button1, 1))
        }

        Text(
            stringResource(id = R.string.someArgs, 1),
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(button1.bottom, margin = 16.dp)
                centerAround(button1.end)
            }
        )
        val barrier = createEndBarrier(button1, text1)
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text(stringResource(id = R.string.buttonTwo, 2))
        }
    }
}

@Composable
fun ConstraintLayoutExampleThree() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val (textTopStart, textTopCenter, textTopEnd, textCenterStart, textCenter, textCenterEnd, textBottomStart, textBottom, textBottomEnd) = createRefs()

        //textTopStart
        Text(
            text = "textTopStart",
            modifier = Modifier.constrainAs(textTopStart) {
                start.linkTo(parent.start, 8.dp)
                top.linkTo(parent.top, 8.dp)
            }
        )

        //textTopCenter
        Text(
            text = "textTopCenter",
            modifier = Modifier.constrainAs(textTopCenter) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        //textTopEnd
        Text(
            text = "textTopCenter",
            modifier = Modifier.constrainAs(textTopEnd) {
                top.linkTo(parent.top, 8.dp)
                end.linkTo(parent.end, 8.dp)
            }
        )

        //textCenterStart
        Text(
            text = "textCenterStart",
            modifier = Modifier.constrainAs(textCenterStart) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(parent.start, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
            }
        )

        Text(
            text = "textCenter",
            modifier = Modifier.constrainAs(textCenter) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
        Text(text = "textCenterEnd", modifier = Modifier.constrainAs(textCenterEnd) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end, 8.dp)
        })

        Text(
            text = "textBottomStart",
            modifier = Modifier.constrainAs(textBottomStart) {
                start.linkTo(parent.start, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
            }
        )
        Text(
            text = "textBottomCenter",
            modifier = Modifier.constrainAs(textBottom) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, 8.dp)
            }
        )

        Text(
            text = "textBottomEnd",
            modifier = Modifier.constrainAs(textBottomEnd) {
                end.linkTo(parent.end, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
            }
        )
    }
}

@Preview
@Composable
fun ConstraintLayoutUsingConstraintSet() {
    val constraintSet = ConstraintSet {
        val buttonLogin = createRefFor("buttonLogin")
        val inputUserName = createRefFor("inputUserName")
        val inputPassword = createRefFor("inputPassword")

        constrain(inputUserName) {
            top.linkTo(parent.top, 32.dp)
            start.linkTo(parent.start, 16.dp)
            end.linkTo(parent.end, 16.dp)
        }

        constrain(inputPassword) {
            top.linkTo(inputUserName.bottom, 8.dp)
            start.linkTo(parent.start, 16.dp)
            end.linkTo(parent.end, 16.dp)
        }

        constrain(buttonLogin) {
            top.linkTo(inputPassword.bottom, 16.dp)
            start.linkTo(parent.start, 16.dp)
            end.linkTo(parent.end, 16.dp)
        }
    }
    ConstraintLayout(
        constraintSet = constraintSet
    ) {
        val context = LocalContext.current

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = ("UserName")) },
            modifier = Modifier.layoutId("inputUserName")
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.layoutId("inputPassword")
        )
        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Showing toast....",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.layoutId("buttonLogin")
        ) {
            Text(text = "Submit")
        }

    }
}

@Preview
@Composable
fun ConstraintLayoutGuideLine() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(12.dp)
    ) {
        val (input, text1, text2, text3) = createRefs()
        val guidelineFromTop = createGuidelineFromTop(0.7f)
        val guidelineFromStart = createGuidelineFromStart(0.2f)
        val guidelineFromEnd = createGuidelineFromEnd(0.2f)
        val guidelineFromBottom = createGuidelineFromBottom(0.2f)

        Text(
            text = "Attached to top guideLine ",
            modifier = Modifier.constrainAs(input) {
                top.linkTo(guidelineFromTop, 8.dp)
                start.linkTo(parent.start, 8.dp)
                end.linkTo(parent.end, 8.dp)
            }
        )
        Text(
            text = "Attached to starting guideLine",
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(guidelineFromStart, 8.dp)
            }
        )
        Text(
            text = "Attached to Ending guideLine",
            modifier = Modifier.constrainAs(text2) {
                end.linkTo(guidelineFromEnd, 8.dp)
            }
        )

        Text(
            text = "Attached to Bottom guideLine",
            modifier = Modifier.constrainAs(text3) {
                bottom.linkTo(guidelineFromBottom, 8.dp)
            }
        )
    }
}
@Preview
@Composable
fun ConstraintLayoutBarrier() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val (text1,text2,text3,text4,text5) = createRefs()

        val barrierStart = createStartBarrier(text1,text2)
        val barrierEnd = createEndBarrier(text4)
        Text(
            text = "hello there...",
            modifier = Modifier.constrainAs(text1){
                top.linkTo(parent.top,8.dp)
                start.linkTo(parent.start,8.dp)
            }
        )

        Text(text = "hello another one", modifier = Modifier.constrainAs(text2) {
            top.linkTo(text1.bottom,8.dp)
            start.linkTo(parent.start,8.dp)
        })

        Text(text = "attached to the starting barrier", modifier = Modifier.constrainAs(text3) {
            top.linkTo(text2.bottom,8.dp)
            start.linkTo(barrierStart,8.dp)
        })

        Text(text = "hello number three", modifier = Modifier.constrainAs(text4){
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        })
        Text(text = "attached to the ending barrier", modifier = Modifier.constrainAs(text5) {
            top.linkTo(text4.bottom,8.dp)
            end.linkTo(barrierEnd,8.dp)
        })

    }

}
@Preview
@Composable
fun ConstraintLayoutChains(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        val (button1,button2,button3) = createRefs()

        createHorizontalChain(button1,button2,button3, chainStyle = ChainStyle.SpreadInside)
        
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button1){
                centerHorizontallyTo(parent)
            }
        ) {
            Text(text = "ButtonOne")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button2){
                centerVerticallyTo(parent)
            }
        ) {
            Text(text = "ButtonTwo")
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button3){
                centerHorizontallyTo(parent)
            }
        ) {
            Text(text = "ButtonThree")
        }
    }
}

@Composable
fun ToastDemo() {
    val context = LocalContext.current
    Column(
        content = {
            Button(onClick = {
                Toast.makeText(
                    context,
                    "Showing toast....",
                    Toast.LENGTH_SHORT
                ).show()
            }, content = {
                Text(text = "Show Toast")
            })
        }, modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}