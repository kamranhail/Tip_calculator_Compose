package com.example.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.persistableBundleOf
import com.example.tipcalculator.Components.InputField
import com.example.tipcalculator.Widgets.RoundIconButton
import com.example.tipcalculator.ui.theme.TipcalculatorTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipcalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                ) {
                    Column(modifier = Modifier.padding(5.dp)) {
                    //  MainContenct()
                      //  UppderHeader()
                        MainContenctone()
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun UppderHeader(totalperperson : Double=14.0) {
    Card(shape = RoundedCornerShape(20.dp)
    , elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    , modifier = Modifier.padding(16.dp)

    ) {
Column(modifier = Modifier
    .fillMaxWidth()
    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
) {
    Text(text = "Total per Person")
    Spacer(modifier = Modifier.height(20.dp))

val num= "%.2f".format(totalperperson)
    Text(text = num
    , fontWeight = FontWeight.Bold,
    fontSize = 35.sp
    , style = MaterialTheme.typography.headlineLarge

        )

}
    }
    
}


@Composable
fun MainContenctone(){
    BillForm(){ sd->
        Log.d("AMT","maincontant ${ sd.toInt()}")
    }
}


@OptIn(ExperimentalComposeUiApi::class)
//@Preview(showBackground = true, backgroundColor = 0xFFFFEB3B)
@Composable
fun MainContenct() {
    val totalBillState = remember {
        mutableStateOf("")
    }
val isValid=remember(totalBillState.value){
totalBillState.value.trim().isNotEmpty()
}
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(

        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
        , shape = RoundedCornerShape(corner = CornerSize(8.dp)  )


     //   modifier = Modifier.padding(5.dp).fillMaxWidth()

       //
        //  .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
       // .clip(shape = CircleShape.copy(all = CornerSize(8.dp)))
      , border = BorderStroke(2.dp, color = Color.LightGray)
//, shape = RoundedCornerShape(corner = CornerSize(8.dp))
        ) {

Column () {

InputField(  valueState = totalBillState, labelId = "Enter Bill",
    enabaled = true, isSingleLine = true, onAction = KeyboardActions{
if(!isValid) return@KeyboardActions

        keyboardController?.hide()
    }
)

}
            }
    
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFEB3B)
@Composable
fun BillForm(modifier: Modifier=Modifier,
       onValChange :(String)->Unit={}
             )

  {
      val totalBillState = remember {
          mutableStateOf("")
      }
      val isValid=remember(totalBillState.value){
          totalBillState.value.trim().isNotEmpty()
      }
      var splitNum = remember {
          mutableStateOf(1)
      }

val sliderPostionState= remember {
    mutableStateOf(0f)
}
      val tipPercentage = (sliderPostionState.value*100).toInt()

      val tipAmmountstate= remember {
          mutableStateOf(0.0)
      }
      val totalPerPerson = remember {
          mutableStateOf(0.0)
      }



      val keyboardController = LocalSoftwareKeyboardController.current
      UppderHeader(totalPerPerson.value)
      Surface(

          modifier = Modifier
              .padding(5.dp)
              .fillMaxWidth()
          , shape = RoundedCornerShape(corner = CornerSize(8.dp)  )


          //   modifier = Modifier.padding(5.dp).fillMaxWidth()

          //
          //  .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
          // .clip(shape = CircleShape.copy(all = CornerSize(8.dp)))
          , border = BorderStroke(2.dp, color = Color.LightGray)
//, shape = RoundedCornerShape(corner = CornerSize(8.dp))
      ) {

          Column (modifier= Modifier
              .padding(6.dp)
              .fillMaxWidth(),
          verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start
              ) {

              InputField(valueState = totalBillState, labelId = "Enter Bill",
                  enabaled = true, isSingleLine = true, onAction = KeyboardActions {
                      if (!isValid) return@KeyboardActions
                      onValChange(totalBillState.value.trim())

                      keyboardController?.hide()
                  }
              )
                if(isValid) {
              Row(
                  modifier = Modifier
                      .padding(3.dp)
                      .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
              ) {
                  Text(
                      text = "Split",
                      modifier = Modifier.align(
                          alignment = Alignment.CenterVertically
                      )
                  )
                  Spacer(modifier = Modifier.width(120.dp))



                  Row(
                      modifier = Modifier.padding(3.dp),
                      horizontalArrangement = Arrangement.End
                  ) {

                      RoundIconButton(imageVector = Icons.Default.Remove, onClick = {
                          if (splitNum.value > 0 && splitNum.value <= 15) {
                              splitNum.value--
                              totalPerPerson.value= calculateTotalPerPerson(totalBillState.value.toDouble(),splitNum.value
                                  ,tipPercentage)
                          }
                      })

                      Text(
                          text = splitNum.value.toString(),
                          modifier = Modifier
                              .align(Alignment.CenterVertically)
                              .padding(start = 9.dp, end = 9.dp)
                      )
                      RoundIconButton(imageVector = Icons.Default.Add, onClick = {
                          if (splitNum.value >= 0 && splitNum.value <= 15) {
                              splitNum.value++
                              totalPerPerson.value= calculateTotalPerPerson(totalBillState.value.toDouble(),splitNum.value
                                  ,tipPercentage)
                          }
                      })

                  }
              }
                    //Tip Row
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .padding(vertical = 12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        androidx.compose.material.Text(
                            text = "Tip"
                            // , modifier = Modifier.align(alignment = Alignment.Start)
                        )

                        Spacer(modifier = Modifier.width(200.dp))

                        androidx.compose.material.Text(
                            text = "$ ${tipAmmountstate.value}"
                            //, modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        )

                    }


                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(5.dp, 10.dp)
                    ) {

                        Text(text = "$tipPercentage %")
                        Spacer(modifier = Modifier.height(14.dp))

                        Slider(value = sliderPostionState.value,
                            onValueChange = { newval ->
                                sliderPostionState.value = newval
                                //   Log.d("Tag", "BillForm $newval")
                                tipAmmountstate.value=calculateTotalTip(totalBillState.value.toDouble(),tipPercentage)
                            }, steps = 5, onValueChangeFinished = {
                                tipAmmountstate.value=calculateTotalTip(totalBillState.value.toDouble(),tipPercentage)
                                totalPerPerson.value= calculateTotalPerPerson(totalBillState.value.toDouble(),splitNum.value
                                    ,tipPercentage)
                            }
                        )

                    }

              }else{
                  Box(modifier = Modifier) {

                  }

              //Tip Row


          }
          }
      }

}

fun calculateTotalTip(totalBill: Double, tipPercent: Int): Double {
    Log.d("Tag", "B $totalBill  $tipPercent ")
    return if (totalBill > 1 && totalBill.toString().isNotEmpty()) (totalBill * tipPercent) / 100 else 0.0
}
fun calculateTotalPerPerson(totalBill: Double, splitBy: Int, tipPercent: Int): Double {
    val bill = calculateTotalTip(totalBill, tipPercent = tipPercent) + totalBill
    return (bill/splitBy)
}

