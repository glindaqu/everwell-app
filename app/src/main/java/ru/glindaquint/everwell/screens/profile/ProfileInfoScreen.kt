package ru.glindaquint.everwell.screens.profile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.network.dto.users.UpdateProfileRequest
import ru.glindaquint.everwell.sharedComponents.EverwellActionButton
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.LabeledTextField
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.sharedComponents.UserImage
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.utils.convertDateToLocalDate
import ru.glindaquint.everwell.utils.convertLocalDate2Date
import ru.glindaquint.everwell.utils.pxToDp
import ru.glindaquint.everwell.viewModels.impl.ProfileViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun ProfileInfoScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState = viewModel.uiState.collectAsState()

    var imageUri = remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri?.let { imageUri.value = it }
            },
        )

    val firstname = remember { mutableStateOf(TextFieldValue(uiState.value.firstname)) }
    val lastname = remember { mutableStateOf(TextFieldValue(uiState.value.lastname)) }
    val patronymic = remember { mutableStateOf(TextFieldValue(uiState.value.patronymic)) }
    val weight = remember { mutableStateOf(TextFieldValue(uiState.value.weight)) }
    val height = remember { mutableStateOf(TextFieldValue(uiState.value.height)) }
    val birthDate = remember { mutableStateOf(TextFieldValue()) }
    val selectedBirthDate =
        remember { mutableStateOf(convertDateToLocalDate(uiState.value.birthDate ?: Date())) }
    val sex = remember { mutableStateOf(SexPickerValue.getByString(uiState.value.sex)) }
    val badHabits =
        remember {
            mutableStateListOf<String>().apply {
                addAll(uiState.value.badHabits)
            }
        }
    val sicks = remember { mutableStateOf(TextFieldValue(uiState.value.diseases)) }

    val sheetState =
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false,
        )
    val shouldShowBottomSheet = remember { mutableStateOf(false) }
    val textFieldHeight = remember { mutableStateOf(0.dp) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(selectedBirthDate.value) {
        birthDate.value =
            TextFieldValue(
                DateTimeFormatter.ofPattern("dd.MM.yyyy").format(selectedBirthDate.value),
            )
    }

    EverwellScaffold(
        containerColor = MainBackground,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 30.dp),
        contentSpacing = Arrangement.spacedBy(8.dp),
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = "Profile",
                colors =
                    MainTopBarColors(
                        backgroundColor = MainPrimary,
                        foregroundColor = MainSecondary,
                        behindContainerColor = MainBackground,
                    ),
                onIconClick = {
                    navHostController.navigateUp()
                },
            )
        },
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            UserImage(
                image = imageUri.value ?: uiState.value.image,
                modifier = Modifier.clip(CircleShape).size(85.dp),
            )
            Box(
                modifier =
                    Modifier.size(85.dp).background(
                        color = Color.Black.copy(0.4f),
                        shape = CircleShape,
                    ),
            )
            IconButton(onClick = { launcher.launch("image/*") }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit profile image",
                    tint = Color.White,
                )
            }
        }
        LabeledTextField(state = lastname, labelText = "Lastname")
        LabeledTextField(state = firstname, labelText = "Firstname")
        LabeledTextField(state = patronymic, labelText = "Patronymic (optional)")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            LabeledTextField(
                state = height,
                labelText = "Height",
                modifier =
                    Modifier.fillMaxWidth(0.47f).onGloballyPositioned {
                        textFieldHeight.value = it.size.height.pxToDp()
                    },
            )
            LabeledTextField(
                state = weight,
                labelText = "Weight",
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            LabeledTextField(
                state = birthDate,
                labelText = "Birth date",
                modifier = Modifier.fillMaxWidth(0.47f),
                enabled = false,
                trailingIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            sheetState.show()
                            shouldShowBottomSheet.value = true
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.today),
                            contentDescription = "Select birth date",
                            tint = MainPrimary,
                        )
                    }
                },
            )
            SexPicker(
                title = "Sex",
                value = sex.value,
                onValueChanged = {
                    sex.value = it
                },
                modifier = Modifier.height(textFieldHeight.value),
            )
        }
        LabeledTextField(state = sicks, labelText = "Diseases (optional)")
        BadHabitsPicker(title = "Bad habits", state = badHabits)
        EverwellActionButton(text = "Save", action = {
            viewModel.updateProfile(
                request =
                    UpdateProfileRequest(
                        lastname = lastname.value.text,
                        firstname = firstname.value.text,
                        patronymic = patronymic.value.text,
                        badHabits = badHabits.toList(),
                        birthDate = convertLocalDate2Date(selectedBirthDate.value),
                        diseases = sicks.value.text,
                        sex = sex.value.name,
                        weight = weight.value.text.toIntOrNull(),
                        height = height.value.text.toIntOrNull(),
                    ),
                onSuccess = {
                    navHostController.navigateUp()
                },
            )
            imageUri.value?.let {
                viewModel.updateImage(it, context)
            }
        })

        if (shouldShowBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = {
                    coroutineScope.launch {
                        sheetState.hide()
                        shouldShowBottomSheet.value = false
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                WheelDatePicker(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    selectorProperties =
                        WheelPickerDefaults.selectorProperties(
                            color = MainPrimary.copy(0.1f),
                            border = null,
                            shape = RoundedCornerShape(12.dp),
                        ),
                    textColor = MainPrimary,
                    maxDate = LocalDate.now(),
                    onSnappedDate = {
                        selectedBirthDate.value = it
                    },
                    startDate = selectedBirthDate.value,
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun BadHabitsPicker(
    title: String,
    state: SnapshotStateList<String>,
) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = title,
            color = Color.Black,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontSize = 14.sp,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            listOf(
                "Курение",
                "Алкоголизм",
                "Парение",
                "Лекарственная зависимость",
                "Недоедание",
                "Переедание",
            ).forEach { habit ->
                BadHabitsItem(
                    value = habit,
                    selected = state.contains(habit),
                    onClick = {
                        if (state.contains(habit)) {
                            state.remove(habit)
                        } else {
                            state.add(habit)
                        }
                    },
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun BadHabitsItem(
    value: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .background(
                    color = if (selected) MainPrimary else MainSecondary,
                    shape = RoundedCornerShape(12.dp),
                ).clip(RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp)
                .clickable {
                    onClick()
                },
        contentAlignment = Alignment.Center,
    ) {
        Text(text = value, color = if (selected) MainSecondary else Color.Black)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SexPicker(
    title: String,
    modifier: Modifier = Modifier,
    value: SexPickerValue,
    onValueChanged: (SexPickerValue) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(
            text = title,
            color = Color.Black,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontSize = 14.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            SexPickerValue.entries.toTypedArray().forEach {
                SexPickerItem(
                    modifier =
                        Modifier
                            .weight(
                                if (it == SexPickerValue.OTHER) {
                                    0.5f
                                } else {
                                    0.25f
                                },
                            ).then(modifier),
                    value = it,
                    selected = value == it,
                    shape =
                        when (it) {
                            SexPickerValue.MAN ->
                                RoundedCornerShape(
                                    topStart = 12.dp,
                                    bottomStart = 12.dp,
                                )

                            SexPickerValue.WOMAN -> RectangleShape

                            SexPickerValue.OTHER ->
                                RoundedCornerShape(
                                    topEnd = 12.dp,
                                    bottomEnd = 12.dp,
                                )
                        },
                    onClick = onValueChanged,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SexPickerItem(
    modifier: Modifier = Modifier,
    value: SexPickerValue,
    selected: Boolean,
    shape: Shape,
    onClick: (SexPickerValue) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .then(modifier)
                .clickable { onClick(value) }
                .background(
                    color =
                        if (selected) {
                            MainPrimary
                        } else {
                            MainSecondary
                        },
                    shape = shape,
                ).clip(shape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text =
                if (value == SexPickerValue.OTHER) {
                    value.name.toLowerCase(Locale.current).capitalize(Locale.current)
                } else {
                    value.name[0].toString()
                },
            color =
                if (selected) {
                    MainSecondary
                } else {
                    Color.Black
                },
        )
    }
}

enum class SexPickerValue {
    MAN,
    WOMAN,
    OTHER, ;

    companion object {
        fun getByString(value: String?): SexPickerValue =
            when (value) {
                "MAN" -> MAN
                "WOMAN" -> WOMAN
                else -> OTHER
            }
    }
}
