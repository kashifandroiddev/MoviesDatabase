package com.common.utils

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowInsetsControllerCompat


object Utils {

    @Composable
    fun AlertDialog(title: String?,message: String?, onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = title?:"") },
            text = { Text(text = message?:"") },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }

//     object NoRippleTheme : RippleTheme {
//        @Composable
//        override fun defaultColor() = Color.Companion.Transparent
//
//        @Composable
//        override fun rippleAlpha() = RippleAlpha(0F, 0F, 0F, 0F)
//    }

    //    UtilsCompose.UpdateStatusBar(darkIcons = true) use of this function
//    @Composable
//    fun UpdateStatusBar(color: Color? = WhiteFF, darkIcons: Boolean) {
//        val context = LocalContext.current
//        val window = (context as Activity).window
//        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
//
//        SideEffect {
////        window.statusBarColor = color.toArgb() // Set status bar background color
//            insetsController.isAppearanceLightStatusBars = !darkIcons // Change icon color
//        }
//    }


    @OptIn(com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi::class)
    @Composable
    fun LoadImageCircleOrCorner(
        model: Any?,
        crossFade: Boolean = false,
        contentScale: ContentScale,
        modifier: Modifier
    ) {
        com.bumptech.glide.integration.compose.GlideImage(
            model = model,
            contentScale = contentScale,
            contentDescription = null,
            modifier = modifier
        )
    }
    @OptIn(com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi::class)
    @Composable
    fun LoadSimpleImage(
        model: Any?,
        crossFade: Boolean = false,
        contentScale: ContentScale,
        modifier: Modifier
    ) {
        com.bumptech.glide.integration.compose.GlideImage(
            model = model,
            contentScale = contentScale,
            contentDescription = null,
            modifier = modifier
        )
    }

    @Composable
    fun IconResource(
        modifier: Modifier,
        painterResource: Int
    ) {
        Icon(
            painterResource(painterResource),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = "",
            modifier = modifier,
        )
    }


    @Composable
    fun IconVector(
        modifier: Modifier,
        vectorIcon: ImageVector, tint: Color
    ) {
        Icon(
            imageVector = vectorIcon,
            tint = tint,
            contentDescription = null,
            modifier = modifier
        )
    }


//    fun toggleTheme(value: Boolean = false): Boolean {
//        return value
//    }


}


//old method
//    fun createAnnotatedString(data: List<LinkTextData?>?): AnnotatedString {
//        return buildAnnotatedString {
//            data?.forEach { dataModel ->
//                if (dataModel?.tag != null && dataModel.annotation != null) {
//                    pushStringAnnotation(
//                        tag = dataModel.tag,
//                        annotation = dataModel.annotation,
//                    )
//                    dataModel.styleCustom?.let {
//                        withStyle(
//                            style = it
//                        ) {
//                            append(dataModel.text)
//                        }
//                    }
//                    pop()
//                } else {
//                    dataModel?.styleCustom?.let {
//                        withStyle(
//                            style = it
//                        ) {
//                            append(dataModel.text)
//                        }
//                    }
//                }
//            }
//        }
//    }

//ClickableText(
//text = annotatedString,
//style = MaterialTheme.typography.bodyMedium,
//onClick = { offset ->
//    getLinkTxtData()?.forEach { annotatedStringData ->
//        if (annotatedStringData?.tag != null && annotatedStringData.annotation != null) {
//            annotatedString.getStringAnnotations(
//                tag = annotatedStringData.tag ?: "",
//                start = offset,
//                end = offset,
//            ).firstOrNull()?.let {
//                Log.d("Link text", "${it.tag} ${it.item}")
//                uriHandler.openUri(it.item)
//                return@ClickableText
//            }
//        }
//    }
//},
//)

//@Composable
//fun LoadImageRoundCornerWithBorder(
//    model: Any?,
//    crossFade: Boolean = false,
//    contentScale: ContentScale,
//    width: Dp,
//    height: Dp,
//    borderApply: Boolean = true,
//    roundedCornerShape: RoundedCornerShape,
//) {
//    AsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(model)
//            .crossfade(crossFade)
//            .build(),
//        placeholder = painterResource(R.drawable.eye_show),
//        contentScale = contentScale,
//        contentDescription = null,
//        modifier = Modifier
//            .width(width)
//            .height(height)
//            .padding(0.dp)
//            .clip(roundedCornerShape)
//            .then(
//                if (borderApply) Modifier.border(
//                    Dimens.BorderSizeCircle,
//                    MaterialTheme.colorScheme.primary,
//                    roundedCornerShape
//                ) else Modifier
//            )
//    )
//}
//@Composable
//fun LoadImageRoundCornerWithBorder(
//    model: Any?,
//    crossFade: Boolean = false,
//    contentScale: ContentScale,
//    width: Dp,
//    height: Dp,
//    borderApply: Boolean = true,
//    roundedCornerShape: RoundedCornerShape,
//) {
//    AsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(model)
//            .crossfade(crossFade)
//            .build(),
//        placeholder = painterResource(R.drawable.eye_show),
//        contentScale = contentScale,
//        contentDescription = null,
//        modifier = Modifier
//            .width(width)
//            .height(height)
//            .padding(0.dp)
//            .clip(roundedCornerShape)
//            .then(
//                if (borderApply) Modifier.border(
//                    Dimens.BorderSizeCircle,
//                    MaterialTheme.colorScheme.primary,
//                    roundedCornerShape
//                ) else Modifier
//            )
//    )
//}

//@Composable
//fun LoadImageCircleWithBorder(
//    model: Any?,
//    crossFade: Boolean = false,
//    contentScale: ContentScale,
//    width: Dp,
//    height: Dp,
//    borderApply: Boolean = true
//) {
//    AsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(model)
//            .crossfade(crossFade)
//            .build(),
//        placeholder = painterResource(R.drawable.eye_show),
//        contentScale = contentScale,
//        contentDescription = null,
//        modifier = Modifier
//            .width(width)
//            .height(height)
//            .padding(0.dp)
//            .clip(CircleShape)
//            .then(
//                if (borderApply) Modifier.border(
//                    Dimens.BorderSizeCircle,
//                    MaterialTheme.colorScheme.primary,
//                    CircleShape
//                ) else Modifier
//            )
//    )
//}