package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.DataResponse
import ru.glindaquint.everwell.network.dto.users.User
import ru.glindaquint.everwell.network.services.FamilyGroupService
import ru.glindaquint.everwell.uiStates.FamilyGroupUiState
import javax.inject.Inject

@HiltViewModel
class FamilyGroupViewModel
    @Inject
    constructor(
        val familyGroupService: FamilyGroupService,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(FamilyGroupUiState())
        val uiState = _uiState.asStateFlow()

        fun join(familyGroupId: Long) {
            viewModelScope.launch(Dispatchers.IO) {
                familyGroupService.join(familyGroupId).execute()
            }
        }

        fun leave() {
            viewModelScope.launch(Dispatchers.IO) {
                familyGroupService.leave().execute()
            }
        }

        fun invite() {
            viewModelScope.launch(Dispatchers.IO) {
                familyGroupService.getInviteLink().enqueue(
                    object : Callback<DataResponse<String>> {
                        override fun onResponse(
                            call: Call<DataResponse<String>?>,
                            response: Response<DataResponse<String>?>,
                        ) {
                            if (response.isSuccessful) {
                                _uiState.value = _uiState.value.copy(inviteLink = response.body()?.data)
                            }
                        }

                        override fun onFailure(
                            call: Call<DataResponse<String>?>,
                            t: Throwable,
                        ) {
                        }
                    },
                )
            }
        }

        fun members() {
            viewModelScope.launch(Dispatchers.IO) {
                familyGroupService.getMembers().enqueue(
                    object : Callback<DataResponse<Set<User>>> {
                        override fun onResponse(
                            call: Call<DataResponse<Set<User>>>,
                            response: Response<DataResponse<Set<User>>>,
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.data?.let {
                                    _uiState.value = _uiState.value.copy(members = it)
                                }
                            }
                        }

                        override fun onFailure(
                            call: Call<DataResponse<Set<User>>>,
                            t: Throwable,
                        ) {
                        }
                    },
                )
            }
        }
    }
