package com.example.customlayoutflowrowmaxlines

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

// provide properly, from Vm or somewhere else
val items = listOf(
    "A More Simpler and Scalable Way to do Multiple view types in",
    "Price: High to Low",
    "Spa lounge / relaxation area",
    "Avg rating: 4+",
    "Free breakfast",
    "Free cancellation",
    "Wifi on premise",
    "Kids' pool",
    "Full body massage",
    "Hand massage ",
    "Head massage ",
    "Couples massage ",
    "Foot massage ",
    "Neck massage",
    "Back massage",
    "Spa / wellness packages",
    "Spa facilities",
    "Sun umbrellas",
    "Sun loungers or beach chairs",
    "Massage",
    "Spa and wellness centre"
)

@Composable
@PreviewLightDark
fun ChipComponentCustom(modifier: Modifier = Modifier, maxLines: Int = 5) {
    Column(modifier = Modifier.padding(16.dp)) {
        val measurePolicy = flowLayoutMeasurePolicy(maxLines)

        Layout(
            measurePolicy = measurePolicy,
            content =
            {
                items.forEach {
                    ChipItem(it)
                }
            },
            modifier = modifier
        )
    }
}

/**
 * Could be be optimised with SubCompose Layout, we dont want to compose every item if we're only showing a few?
 */
private fun flowLayoutMeasurePolicy(maxLines: Int) = MeasurePolicy { measurables, constraints ->
    layout(constraints.maxWidth, constraints.maxHeight) {
        var lines = 0
        var yPos = 0
        var xPos = 0
        var maxY = 0

        for (index in measurables.indices) {
            val rest = constraints.maxWidth - xPos
            val isEnoughSpace = rest >= (0.4 * constraints.maxWidth)

            val measurable = measurables[index]
            val placeable = if (isEnoughSpace) {
                measurable.measure(Constraints(maxWidth = rest))
            } else {
                measurable.measure(Constraints())
            }

            if (lines == maxLines) {
                break
            }

            if (xPos + placeable.width > constraints.maxWidth && !isEnoughSpace) {
                yPos += maxY
                xPos = 0
                maxY = 0
                lines += 1
            }

            if (lines < maxLines) {
                placeable.placeRelative(
                    x = xPos,
                    y = yPos
                )
            }

            xPos += placeable.width

            if (maxY < placeable.height) {
                maxY = placeable.height
            }

            if (index < measurables.size - 1) {
                /**
                 * Maybe we want to add a marker if there's remaining items
                 */
            }
        }
    }
}
