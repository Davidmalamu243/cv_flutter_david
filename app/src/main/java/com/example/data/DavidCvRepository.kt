package com.example.data

import com.example.R
import com.example.model.ContactActionType
import com.example.model.ContactItem
import com.example.model.CvProfile
import com.example.model.EducationItem
import com.example.model.ExperienceItem
import com.example.model.FamilyMember
import com.example.model.InterestItem
import com.example.model.LanguageItem
import com.example.model.ProfileStat
import com.example.model.SkillItem

object DavidCvRepository {

    val profile = CvProfile(
        fullName = "CIZA ELMALAMU DAVID",
        title = "Électricien & Ingénieur Réseau • Promoteur & Fondateur",
        subtitle = "Étudiant L4 ISIG-Goma • CS MALKIA • DUNDA Production",
        birthDate = "13/02/2004",
        location = "Quartier Himbi, Goma (RDC)",
        bio = "Étudiant en 4e année de Licence à l'ISIG-Goma et diplômé en électricité générale, " +
            "je combine l'ingénierie de terrain (chantiers électriques et infrastructures réseau) avec " +
            "l'entrepreneuriat éducatif en tant que promoteur du Complexe Scolaire MALKIA à Bukavu et " +
            "le leadership culturel via DUNDA Production.",
        photoRes = R.drawable.img_david_profile,
        stats = listOf(
            ProfileStat(value = "L4 ISIG", label = "Goma"),
            ProfileStat(value = "CS MALKIA", label = "Bukavu"),
            ProfileStat(value = "DUNDA", label = "Production")
        )
    )

    val experiences = listOf(
        ExperienceItem(
            id = "exp_chantier",
            role = "Électricien, Sécurité CCTV & Ingénieur Réseau",
            organization = "Prestataire Indépendant / Missions Terrain",
            location = "Goma & Nord/Sud-Kivu",
            period = "En activité",
            badge = "Terrain & Infra",
            highlights = listOf(
                "Interventions rapides sur chantiers en tant qu'électricien bâtiment et industriel.",
                "Installation de vidéosurveillance Hikvision ColorVu, câblage réseau structuré et routeurs.",
                "Diagnostic électrique, sécurisation des tableaux de distribution et maintenance réseau."
            ),
            galleryPhotos = listOf(R.drawable.img_david_field, R.drawable.img_exp_cctv)
        ),
        ExperienceItem(
            id = "exp_malkia",
            role = "Promoteur & Visionnaire",
            organization = "Complexe Scolaire MALKIA",
            location = "Bukavu, Sud-Kivu",
            period = "Direction & Promotion",
            badge = "Éducation",
            highlights = listOf(
                "Pilotage stratégique et développement du Complexe Scolaire MALKIA à Bukavu.",
                "Modernisation des équipements techniques et promotion d'une éducation d'excellence.",
                "Coordination administrative et partenariats éducatifs."
            ),
            galleryPhotos = listOf(R.drawable.img_exp_malkia)
        ),
        ExperienceItem(
            id = "exp_dunda",
            role = "Fondateur & Producteur Exécutif",
            organization = "DUNDA Production",
            location = "Goma / Bukavu",
            period = "Structure Active",
            badge = "Musique & Média",
            highlights = listOf(
                "Création et gestion d'une structure dédiée à la production d'artistes musiciens locaux.",
                "Direction artistique, prise de son (interface audio & studio), stratégie digitale et scène.",
                "Accompagnement de jeunes talents urbains de l'Est de la RDC."
            ),
            galleryPhotos = listOf(R.drawable.img_exp_dunda)
        )
    )

    val education = listOf(
        EducationItem(
            id = "edu_isig",
            degree = "4e Année de Licence (L4)",
            institution = "ISIG-Goma (Institut Supérieur d'Informatique et de Gestion)",
            location = "Goma, RDC",
            status = "En cours",
            details = "Systèmes d'information, réseaux informatiques, génie logiciel et gestion de projets."
        ),
        EducationItem(
            id = "edu_elec",
            degree = "Diplôme d'État en Électricité Générale",
            institution = "Section Technique Électricité Générale",
            location = "RDC",
            status = "Diplômé",
            details = "Installations électriques basse/moyenne tension, schémas unifilaires, automatismes et sécurité."
        )
    )

    val technicalSkills = listOf(
        SkillItem("Électricité Générale & Chantiers", "Énergie", 0.94f),
        SkillItem("Ingénierie Réseau & Vidéosurveillance CCTV", "Réseaux & Sécurité", 0.92f),
        SkillItem("Gestion de Projets & Entrepreneuriat", "Leadership", 0.92f),
        SkillItem("Production Musicale & Management d'Artistes", "Création", 0.89f),
        SkillItem("Développement Mobile & Systèmes d'Information", "Tech ISIG", 0.85f)
    )

    val languages = listOf(
        LanguageItem("Français", "Courant / Académique", 0.96f),
        LanguageItem("Swahili", "Langue usuelle / Natif", 0.98f),
        LanguageItem("Lingala", "Courant", 0.85f),
        LanguageItem("Anglais", "Technique & Professionnel", 0.72f)
    )

    val interests = listOf(
        InterestItem(
            title = "Technologie",
            description = "Architectures réseau, systèmes CCTV, innovations mobiles et énergie.",
            tag = "Innovation"
        ),
        InterestItem(
            title = "Musique",
            description = "Production audio, scène, prise de voix et direction via DUNDA Production.",
            tag = "Production",
            photoRes = R.drawable.img_interest_music
        ),
        InterestItem(
            title = "Sport",
            description = "Endurance, esprit d'équipe, discipline physique et dépassement de soi.",
            tag = "Discipline"
        )
    )

    val familyMembers = listOf(
        FamilyMember(
            id = "fam_pere",
            fullName = "Malamu Elson",
            relationship = "Père • En mémoire († 2021)",
            profession = "Grand Humanitaire & Entrepreneur",
            city = "Bukavu / Goma (RDC)",
            bio = "Disparu en 2021, il était un très grand humanitaire et un entrepreneur accompli. Son dévouement envers les autres et son esprit d'entreprise continuent d'inspirer mon parcours.",
            photoRes = R.drawable.img_family_pere
        ),
        FamilyMember(
            id = "fam_isabelle",
            fullName = "Isabelle Ntaliboba",
            relationship = "Mère",
            profession = "Maman & Pilier de la famille",
            city = "Goma / Bukavu",
            bio = "Mère attentionnée et pilier de la famille Malamu, présente pour accompagner chaque réussite académique et professionnelle.",
            photoRes = R.drawable.img_family_isabelle
        ),
        FamilyMember(
            id = "fam_danette",
            fullName = "Danette Malamu",
            relationship = "Grande sœur",
            profession = "Diplômée en Sciences Juridiques (ULPGL-Goma)",
            city = "Goma, RDC",
            bio = "Grande sœur diplômée en filière de Science Juridique à l'ULPGL-Goma, modèle de persévérance et de réussite universitaire.",
            photoRes = R.drawable.img_family_danette
        ),
        FamilyMember(
            id = "fam_daniella",
            fullName = "Daniella Malamu",
            relationship = "Grande sœur",
            profession = "Grande sœur & Soutien familial",
            city = "Goma / Bukavu",
            bio = "Grande sœur bienveillante, toujours présente aux moments clés de la famille entre Goma et Bukavu.",
            photoRes = R.drawable.img_family_daniella
        )
    )

    val contacts = listOf(
        ContactItem(
            id = "contact_phone",
            label = "Téléphone & WhatsApp",
            value = "+243 997 658 995",
            actionHint = "Appuyer pour appeler",
            actionUri = "tel:+243997658995",
            copyValue = "+243 997 658 995",
            type = ContactActionType.PHONE
        ),
        ContactItem(
            id = "contact_email",
            label = "Adresse E-mail",
            value = "davselmalamu0@gmail.com",
            actionHint = "Appuyer pour envoyer un e-mail",
            actionUri = "mailto:davselmalamu0@gmail.com?subject=Contact%20-%20Ciza%20Elmalamu%20David",
            copyValue = "davselmalamu0@gmail.com",
            type = ContactActionType.EMAIL
        ),
        ContactItem(
            id = "contact_address",
            label = "Résidence Principale",
            value = "Quartier Himbi, Goma, Nord-Kivu, RDC",
            actionHint = "Ouvrir sur la carte",
            actionUri = "geo:-1.6771,29.2285?q=Quartier+Himbi,+Goma,+RDC",
            copyValue = "Quartier Himbi, Goma, RDC",
            type = ContactActionType.MAP
        ),
        ContactItem(
            id = "contact_github",
            label = "GitHub",
            value = "github.com/Davidmalamu243",
            actionHint = "Voir les dépôts & projets",
            actionUri = "https://github.com/Davidmalamu243",
            copyValue = "https://github.com/Davidmalamu243",
            type = ContactActionType.WEB
        ),
        ContactItem(
            id = "contact_linkedin",
            label = "LinkedIn",
            value = "linkedin.com/in/david-el-malamu-4884512b0",
            actionHint = "Consulter le profil professionnel",
            actionUri = "https://www.linkedin.com/in/david-el-malamu-4884512b0",
            copyValue = "https://www.linkedin.com/in/david-el-malamu-4884512b0",
            type = ContactActionType.WEB
        )
    )

    fun buildShareSummary(): String = buildString {
        appendLine("CV — ${profile.fullName}")
        appendLine(profile.title)
        appendLine("Né le ${profile.birthDate} • ${profile.location}")
        appendLine()
        appendLine("Formation : 4e Année de Licence à l'ISIG-Goma | Diplômé en Électricité Générale")
        appendLine("Réalisations : Promoteur du Complexe Scolaire MALKIA (Bukavu) | Fondateur de DUNDA Production | Électricien & Ingénieur Réseau sur chantiers")
        appendLine()
        appendLine("Téléphone : +243 997 658 995")
        appendLine("E-mail : davselmalamu0@gmail.com")
        appendLine("GitHub : https://github.com/Davidmalamu243")
        appendLine("LinkedIn : https://www.linkedin.com/in/david-el-malamu-4884512b0")
    }
}
