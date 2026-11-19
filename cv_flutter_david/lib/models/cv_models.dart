enum CvFilter {
  all('Tout'),
  experience('Expériences'),
  education('Formation'),
  skills('Compétences'),
  interests('Passions');

  const CvFilter(this.label);
  final String label;
}

enum ContactActionType { phone, email, map, web }

class ProfileStat {
  const ProfileStat({required this.value, required this.label});
  final String value;
  final String label;
}

class CvProfile {
  const CvProfile({
    required this.fullName,
    required this.title,
    required this.birthDate,
    required this.location,
    required this.bio,
    required this.photoAsset,
    required this.stats,
  });

  final String fullName;
  final String title;
  final String birthDate;
  final String location;
  final String bio;
  final String photoAsset;
  final List<ProfileStat> stats;
}

class ExperienceItem {
  const ExperienceItem({
    required this.role,
    required this.organization,
    required this.location,
    required this.period,
    required this.badge,
    required this.highlights,
    this.galleryAssets = const [],
  });

  final String role;
  final String organization;
  final String location;
  final String period;
  final String badge;
  final List<String> highlights;
  final List<String> galleryAssets;
}

class EducationItem {
  const EducationItem({
    required this.degree,
    required this.institution,
    required this.location,
    required this.status,
    required this.details,
  });

  final String degree;
  final String institution;
  final String location;
  final String status;
  final String details;
}

class SkillItem {
  const SkillItem({
    required this.name,
    required this.category,
    required this.level,
  });

  final String name;
  final String category;
  final double level;
}

class LanguageItem {
  const LanguageItem({required this.name, required this.level});
  final String name;
  final String level;
}

class InterestItem {
  const InterestItem({
    required this.title,
    required this.description,
    required this.tag,
    this.photoAsset,
  });

  final String title;
  final String description;
  final String tag;
  final String? photoAsset;
}

class FamilyMember {
  const FamilyMember({
    required this.fullName,
    required this.relationship,
    required this.profession,
    required this.city,
    required this.bio,
    required this.photoAsset,
  });

  final String fullName;
  final String relationship;
  final String profession;
  final String city;
  final String bio;
  final String photoAsset;
}

class ContactItem {
  const ContactItem({
    required this.label,
    required this.value,
    required this.actionHint,
    required this.uri,
    required this.type,
  });

  final String label;
  final String value;
  final String actionHint;
  final String uri;
  final ContactActionType type;
}
