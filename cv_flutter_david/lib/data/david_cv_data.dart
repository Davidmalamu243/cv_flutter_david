import '../models/cv_models.dart';

class DavidCvData {
  static const profile = CvProfile(
    fullName: 'CIZA ELMALAMU DAVID',
    title: 'Électricien, Sécurité CCTV & Ingénieur Réseau • Promoteur & Fondateur',
    birthDate: '13/02/2004',
    location: 'Quartier Himbi, Goma (RDC)',
    bio:
        "Étudiant en 4e année de Licence à l'ISIG-Goma et diplômé en électricité générale, "
        "je combine l'ingénierie de terrain (chantiers électriques, sécurité CCTV et infrastructures réseau) "
        "avec l'entrepreneuriat éducatif comme promoteur du Complexe Scolaire MALKIA à Bukavu "
        "et le leadership culturel via DUNDA Production.",
    photoAsset: 'assets/images/img_david_profile.jpg',
    stats: [
      ProfileStat(value: 'L4 ISIG', label: 'Goma'),
      ProfileStat(value: 'CS MALKIA', label: 'Bukavu'),
      ProfileStat(value: 'DUNDA', label: 'Production'),
    ],
  );

  static const experiences = [
    ExperienceItem(
      role: 'Électricien, Sécurité CCTV & Ingénieur Réseau',
      organization: 'Prestataire Indépendant / Missions Terrain',
      location: 'Goma & Nord/Sud-Kivu',
      period: 'En activité',
      badge: 'Terrain & Infra',
      highlights: [
        "Interventions rapides sur chantiers en tant qu'électricien bâtiment et industriel.",
        'Installation de vidéosurveillance Hikvision ColorVu, câblage réseau structuré et routeurs.',
        'Diagnostic électrique, sécurisation des tableaux de distribution et maintenance réseau.',
      ],
      galleryAssets: [
        'assets/images/img_david_field.jpg',
        'assets/images/img_exp_cctv.jpg',
      ],
    ),
    ExperienceItem(
      role: 'Promoteur & Visionnaire',
      organization: 'Complexe Scolaire MALKIA',
      location: 'Bukavu, Sud-Kivu',
      period: 'Direction & Promotion',
      badge: 'Éducation',
      highlights: [
        'Pilotage stratégique et développement du Complexe Scolaire MALKIA à Bukavu.',
        "Modernisation des équipements techniques et promotion d'une éducation d'excellence.",
        'Coordination administrative et partenariats éducatifs.',
      ],
      galleryAssets: ['assets/images/img_exp_malkia.jpg'],
    ),
    ExperienceItem(
      role: 'Fondateur & Producteur Exécutif',
      organization: 'DUNDA Production',
      location: 'Goma / Bukavu',
      period: 'Structure Active',
      badge: 'Musique & Média',
      highlights: [
        "Création et gestion d'une structure dédiée à la production d'artistes musiciens locaux.",
        'Direction artistique, prise de son (interface audio & studio), stratégie digitale et scène.',
        "Accompagnement de jeunes talents urbains de l'Est de la RDC.",
      ],
      galleryAssets: ['assets/images/img_exp_dunda.jpg'],
    ),
  ];

  static const education = [
    EducationItem(
      degree: '4e Année de Licence (L4)',
      institution: "ISIG-Goma (Institut Supérieur d'Informatique et de Gestion)",
      location: 'Goma, RDC',
      status: 'En cours',
      details: "Systèmes d'information, réseaux informatiques, génie logiciel et gestion de projets.",
    ),
    EducationItem(
      degree: "Diplôme d'État en Électricité Générale",
      institution: 'Section Technique Électricité Générale',
      location: 'RDC',
      status: 'Diplômé',
      details: 'Installations électriques basse/moyenne tension, schémas unifilaires et sécurité.',
    ),
  ];

  static const skills = [
    SkillItem(name: 'Électricité Générale & Chantiers', category: 'Énergie', level: 0.94),
    SkillItem(name: 'Ingénierie Réseau & Vidéosurveillance CCTV', category: 'Réseaux & Sécurité', level: 0.92),
    SkillItem(name: 'Gestion de Projets & Entrepreneuriat', category: 'Leadership', level: 0.92),
    SkillItem(name: "Production Musicale & Management d'Artistes", category: 'Création', level: 0.89),
    SkillItem(name: "Développement Mobile & Systèmes d'Information", category: 'Tech ISIG', level: 0.85),
  ];

  static const languages = [
    LanguageItem(name: 'Français', level: 'Courant / Académique'),
    LanguageItem(name: 'Swahili', level: 'Langue usuelle / Natif'),
    LanguageItem(name: 'Lingala', level: 'Courant'),
    LanguageItem(name: 'Anglais', level: 'Technique & Professionnel'),
  ];

  static const interests = [
    InterestItem(
      title: 'Technologie',
      description: 'Architectures réseau, systèmes CCTV, innovations mobiles et énergie.',
      tag: 'Innovation',
    ),
    InterestItem(
      title: 'Musique',
      description: 'Production audio, scène, prise de voix et direction via DUNDA Production.',
      tag: 'Production',
      photoAsset: 'assets/images/img_interest_music.jpg',
    ),
    InterestItem(
      title: 'Sport',
      description: "Endurance, esprit d'équipe, discipline physique et dépassement de soi.",
      tag: 'Discipline',
    ),
  ];

  static const familyMembers = [
    FamilyMember(
      fullName: 'Malamu Elson',
      relationship: 'Père • En mémoire († 2021)',
      profession: 'Grand Humanitaire & Entrepreneur',
      city: 'Bukavu / Goma (RDC)',
      bio: "Disparu en 2021, il était un très grand humanitaire et un entrepreneur accompli. Son dévouement envers les autres et son esprit d'entreprise continuent d'inspirer mon parcours.",
      photoAsset: 'assets/images/img_family_pere.jpg',
    ),
    FamilyMember(
      fullName: 'Isabelle Ntaliboba',
      relationship: 'Mère',
      profession: 'Maman & Pilier de la famille',
      city: 'Goma / Bukavu',
      bio: 'Mère attentionnée et pilier de la famille Malamu, présente pour accompagner chaque réussite académique et professionnelle.',
      photoAsset: 'assets/images/img_family_isabelle.jpg',
    ),
    FamilyMember(
      fullName: 'Danette Malamu',
      relationship: 'Grande sœur',
      profession: 'Diplômée en Sciences Juridiques (ULPGL-Goma)',
      city: 'Goma, RDC',
      bio: "Grande sœur diplômée en filière de Science Juridique à l'ULPGL-Goma, modèle de persévérance et de réussite universitaire.",
      photoAsset: 'assets/images/img_family_danette.jpg',
    ),
    FamilyMember(
      fullName: 'Daniella Malamu',
      relationship: 'Grande sœur',
      profession: 'Grande sœur & Soutien familial',
      city: 'Goma / Bukavu',
      bio: 'Grande sœur bienveillante, toujours présente aux moments clés de la famille entre Goma et Bukavu.',
      photoAsset: 'assets/images/img_family_daniella.jpg',
    ),
  ];

  static const contacts = [
    ContactItem(
      label: 'Téléphone & WhatsApp',
      value: '+243 997 658 995',
      actionHint: 'Appuyer pour appeler',
      uri: 'tel:+243997658995',
      type: ContactActionType.phone,
    ),
    ContactItem(
      label: 'Adresse E-mail',
      value: 'davselmalamu0@gmail.com',
      actionHint: 'Appuyer pour envoyer un e-mail',
      uri: 'mailto:davselmalamu0@gmail.com?subject=Contact%20-%20Ciza%20Elmalamu%20David',
      type: ContactActionType.email,
    ),
    ContactItem(
      label: 'Résidence Principale',
      value: 'Quartier Himbi, Goma, Nord-Kivu, RDC',
      actionHint: 'Ouvrir sur la carte',
      uri: 'https://maps.google.com/?q=Quartier+Himbi+Goma+RDC',
      type: ContactActionType.map,
    ),
    ContactItem(
      label: 'GitHub',
      value: 'github.com/Davidmalamu243',
      actionHint: 'Voir les dépôts & projets',
      uri: 'https://github.com/Davidmalamu243',
      type: ContactActionType.web,
    ),
    ContactItem(
      label: 'LinkedIn',
      value: 'linkedin.com/in/david-el-malamu-4884512b0',
      actionHint: 'Consulter le profil professionnel',
      uri: 'https://www.linkedin.com/in/david-el-malamu-4884512b0',
      type: ContactActionType.web,
    ),
  ];
}
