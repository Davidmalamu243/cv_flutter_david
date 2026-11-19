package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.DavidCvRepository
import com.example.model.CvFilter
import com.example.model.FamilyLayoutMode
import com.example.model.FamilyMember
import com.example.model.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    private val _selectedCvFilter = MutableStateFlow(CvFilter.ALL)
    val selectedCvFilter: StateFlow<CvFilter> = _selectedCvFilter.asStateFlow()

    private val _familyLayoutMode = MutableStateFlow(FamilyLayoutMode.LIST)
    val familyLayoutMode: StateFlow<FamilyLayoutMode> = _familyLayoutMode.asStateFlow()

    private val _selectedFamilyMember = MutableStateFlow<FamilyMember?>(null)
    val selectedFamilyMember: StateFlow<FamilyMember?> = _selectedFamilyMember.asStateFlow()

    val profile = DavidCvRepository.profile
    val experiences = DavidCvRepository.experiences
    val education = DavidCvRepository.education
    val technicalSkills = DavidCvRepository.technicalSkills
    val languages = DavidCvRepository.languages
    val interests = DavidCvRepository.interests
    val familyMembers = DavidCvRepository.familyMembers
    val contacts = DavidCvRepository.contacts

    fun onIdentifierChanged(value: String) {
        _loginState.update {
            it.copy(
                identifier = value,
                identifierError = null
            )
        }
    }

    fun onPasswordChanged(value: String) {
        _loginState.update {
            it.copy(
                password = value,
                passwordError = null
            )
        }
    }

    fun togglePasswordVisibility() {
        _loginState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun fillDemoCredentials() {
        _loginState.update {
            it.copy(
                identifier = "david.ciza",
                password = "isig2004",
                identifierError = null,
                passwordError = null
            )
        }
    }

    fun submitLogin(): Boolean {
        val current = _loginState.value
        val idTrimmed = current.identifier.trim()
        val idError = if (idTrimmed.isEmpty()) "Veuillez saisir un identifiant." else null
        val pwdError = if (current.password.length < 4) {
            "Le mot de passe doit contenir au moins 4 caractères."
        } else {
            null
        }

        if (idError != null || pwdError != null) {
            _loginState.update {
                it.copy(
                    identifierError = idError,
                    passwordError = pwdError
                )
            }
            return false
        }

        _loginState.update {
            it.copy(
                isAuthenticated = true,
                identifierError = null,
                passwordError = null
            )
        }
        return true
    }

    fun logout() {
        _loginState.update {
            LoginUiState(
                identifier = it.identifier,
                password = "",
                isAuthenticated = false
            )
        }
    }

    fun selectCvFilter(filter: CvFilter) {
        _selectedCvFilter.value = filter
    }

    fun setFamilyLayoutMode(mode: FamilyLayoutMode) {
        _familyLayoutMode.value = mode
    }

    fun selectFamilyMember(member: FamilyMember?) {
        _selectedFamilyMember.value = member
    }

    fun getShareSummary(): String = DavidCvRepository.buildShareSummary()
}
