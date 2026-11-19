package com.example.model

import androidx.annotation.DrawableRes

enum class AppDestination(val route: String) {
    Login("login"),
    Cv("cv"),
    Family("family"),
    Contact("contact")
}

enum class CvFilter(val label: String) {
    ALL("Tout"),
    EXPERIENCE("Expériences"),
    EDUCATION("Formation"),
    SKILLS("Compétences"),
    INTERESTS("Passions")
}

enum class FamilyLayoutMode {
    LIST,
    GRID
}

enum class ContactActionType {
    PHONE,
    EMAIL,
    MAP,
    WEB
}

data class CvProfile(
    val fullName: String,
    val title: String,
    val subtitle: String,
    val birthDate: String,
    val location: String,
    val bio: String,
    @DrawableRes val photoRes: Int,
    val stats: List<ProfileStat>
)

data class ProfileStat(
    val value: String,
    val label: String
)

data class ExperienceItem(
    val id: String,
    val role: String,
    val organization: String,
    val location: String,
    val period: String,
    val badge: String,
    val highlights: List<String>,
    val galleryPhotos: List<Int> = emptyList()
)

data class EducationItem(
    val id: String,
    val degree: String,
    val institution: String,
    val location: String,
    val status: String,
    val details: String
)

data class SkillItem(
    val name: String,
    val category: String,
    val level: Float
)

data class LanguageItem(
    val name: String,
    val level: String,
    val proficiency: Float
)

data class InterestItem(
    val title: String,
    val description: String,
    val tag: String,
    @DrawableRes val photoRes: Int? = null
)

data class FamilyMember(
    val id: String,
    val fullName: String,
    val relationship: String,
    val profession: String,
    val city: String,
    val bio: String,
    @DrawableRes val photoRes: Int
)

data class ContactItem(
    val id: String,
    val label: String,
    val value: String,
    val actionHint: String,
    val actionUri: String,
    val copyValue: String,
    val type: ContactActionType
)

data class LoginUiState(
    val identifier: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val identifierError: String? = null,
    val passwordError: String? = null,
    val isAuthenticated: Boolean = false
)
