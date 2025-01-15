package com.example.playwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playwithcompose.ui.theme.PlayWithComposeTheme
import java.text.NumberFormat

class TipTime : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayWithComposeTheme  {
                TipTimeLayout()
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipInput by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    var roundUp by remember { mutableStateOf(false) }
    val tip = calculateTip(amount, tipPercent,roundUp)
    val price = NumberFormat.getCurrencyInstance().format(tip)
    val foodAmount = tip + amount

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = amountInput,
            onValueChange = {amountInput = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            label = {Text(stringResource(R.string.bill_amount))},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next)
        )
        EditNumberField(
            value = tipInput,
            onValueChange = {tipInput = it},
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            label =  {Text(stringResource(R.string.how_was_the_service))},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done)
        )
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Text(
            text = stringResource(R.string.tip_amount, price),
            style = TextStyle(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 16.sp)
        )

        Text(
            text = "Total Amount: $foodAmount",
            style = TextStyle(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 16.sp),
        )
        Spacer(modifier = Modifier.height(100.dp))

            DonutGraph(
                totalAmount = amountInput.toDoubleOrNull(),
                tipAmount = tip,
                modifier = Modifier.size(200.dp)
            )




    }
}


@Composable
private fun EditNumberField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier,label: (@Composable () -> Unit),keyboardOptions: KeyboardOptions) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        singleLine = true,
        keyboardOptions = keyboardOptions
    )
}
@Composable
fun Legend(items: List<ColorLegendItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(item.color) // Display the color box
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = item.label,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End),
        verticalAlignment =  Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.round_up_tip), modifier = Modifier.padding(8.dp))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
        )
    }

}
@Composable
fun DonutGraph(
    totalAmount: Double?,
    tipAmount: Double?,
    modifier: Modifier = Modifier
) {
    // Colors for the segments
    val tipColor = Color(0xFFFFA726) // Orange
    val foodColor = Color(0xFF66BB6A) // Green
    val backgroundColor = Color(0xFFEEEEEE) // Light gray


    val totalAmountValue = totalAmount?.mod(360.0)
    val tipAmountValue = tipAmount?.mod(30.0)
    Box(
        modifier = modifier
            .size(100.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {


            val startAngle = 0f
            var sweepAngle = 0f
            if (totalAmountValue != null) sweepAngle = ((totalAmountValue / 100) * 360).toFloat()

            val strokeWidth = 16.dp

            // Background circle
            drawCircle(
                color = backgroundColor,
                radius = size.minDimension / 2,
                center = center
            )

            // Food Segment segment

            drawArc(
                color = foodColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )


            // Tip segment
            if (tipAmountValue != null) {
                drawArc(
                    color = tipColor,
                    startAngle = startAngle + sweepAngle,
                    sweepAngle = ((tipAmountValue / 100) * 360).toFloat(),
                    useCenter = false,

                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }





    }
    Spacer(modifier = Modifier.width(100.dp))

    Legend(
        items = listOf(
            ColorLegendItem(Color(0xFFFFA726), "Tip Amount"),
            ColorLegendItem(Color(0xFF66BB6A), "Bill Amount")
        )
    )
}

private fun calculateTip(amount: Double, tipPercent: Double,roundUp: Boolean): Double {
    var tip = tipPercent / 100 * amount
    if (roundUp) tip = kotlin.math.ceil(tip)
    return tip
}
data class ColorLegendItem(val color: Color, val label: String)
@Preview(showBackground = true)
@Composable
fun TipTimePreview() {
   PlayWithComposeTheme  {
        TipTimeLayout()
    }
}