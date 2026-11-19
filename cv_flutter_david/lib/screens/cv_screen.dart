import 'package:flutter/material.dart';
import '../data/david_cv_data.dart';
import '../models/cv_models.dart';
import '../widgets/futuristic_widgets.dart';

class CvScreen extends StatefulWidget {
  const CvScreen({super.key});

  @override
  State<CvScreen> createState() => _CvScreenState();
}

class _CvScreenState extends State<CvScreen> {
  CvFilter _selectedFilter = CvFilter.all;
  final Set<int> _collapsedExperiences = {};

  @override
  Widget build(BuildContext context) {
    final scheme = Theme.of(context).colorScheme;
    const profile = DavidCvData.profile;

    return ListView(
      padding: const EdgeInsets.fromLTRB(16, 14, 16, 24),
      children: [
        AnimatedProCard(
          animationIndex: 0,
          highlightBorder: true,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Container(
                    width: 82,
                    height: 82,
                    padding: const EdgeInsets.all(3),
                    decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      border: Border.all(
                        color: scheme.primary.withValues(alpha: 0.4),
                        width: 2,
                      ),
                    ),
                    child: ClipOval(
                      child: Image.asset(profile.photoAsset, fit: BoxFit.cover),
                    ),
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        ProStatusBadge(
                          text: 'Disponible • Projets & Terrain',
                          color: scheme.tertiary,
                        ),
                        const SizedBox(height: 6),
                        Text(
                          profile.fullName,
                          style: Theme.of(context).textTheme.titleLarge?.copyWith(
                                fontWeight: FontWeight.bold,
                              ),
                        ),
                        const SizedBox(height: 2),
                        Text(
                          profile.title,
                          style: TextStyle(
                            color: scheme.primary,
                            fontWeight: FontWeight.w500,
                            fontSize: 13,
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 14),
              Divider(color: scheme.outline.withValues(alpha: 0.6)),
              const SizedBox(height: 10),
              Wrap(
                spacing: 16,
                runSpacing: 6,
                children: [
                  Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Icon(Icons.cake, size: 16, color: scheme.primary),
                      const SizedBox(width: 6),
                      Text('Né le ${profile.birthDate}'),
                    ],
                  ),
                  Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Icon(Icons.location_on, size: 16, color: scheme.primary),
                      const SizedBox(width: 4),
                      Text(profile.location),
                    ],
                  ),
                ],
              ),
              const SizedBox(height: 14),
              Row(
                children: profile.stats.map((stat) {
                  return Expanded(
                    child: Container(
                      margin: const EdgeInsets.symmetric(horizontal: 4),
                      padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 6),
                      decoration: BoxDecoration(
                        color: scheme.surfaceContainerHighest,
                        borderRadius: BorderRadius.circular(12),
                      ),
                      child: Column(
                        children: [
                          Text(
                            stat.value,
                            style: TextStyle(
                              color: scheme.primary,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          Text(
                            stat.label,
                            style: Theme.of(context).textTheme.labelSmall,
                          ),
                        ],
                      ),
                    ),
                  );
                }).toList(),
              ),
            ],
          ),
        ),
        const SizedBox(height: 14),

        SingleChildScrollView(
          scrollDirection: Axis.horizontal,
          child: Row(
            children: CvFilter.values.map((filter) {
              return Padding(
                padding: const EdgeInsets.only(right: 8),
                child: FilterChip(
                  selected: _selectedFilter == filter,
                  label: Text(filter.label),
                  onSelected: (_) => setState(() => _selectedFilter = filter),
                ),
              );
            }).toList(),
          ),
        ),
        const SizedBox(height: 14),

        if (_selectedFilter == CvFilter.all) ...[
          const SectionHeader(title: 'Profil & Objectifs'),
          const SizedBox(height: 8),
          AnimatedProCard(
            animationIndex: 1,
            child: Text(profile.bio, style: Theme.of(context).textTheme.bodyLarge),
          ),
          const SizedBox(height: 16),
        ],

        if (_selectedFilter == CvFilter.all || _selectedFilter == CvFilter.experience) ...[
          const SectionHeader(title: 'Expériences Professionnelles'),
          const SizedBox(height: 8),
          ...DavidCvData.experiences.asMap().entries.map((entry) {
            final idx = entry.key;
            final exp = entry.value;
            final expanded = !_collapsedExperiences.contains(idx);
            return Padding(
              padding: const EdgeInsets.only(bottom: 12),
              child: AnimatedProCard(
                animationIndex: idx + 2,
                onTap: () {
                  setState(() {
                    if (expanded) {
                      _collapsedExperiences.add(idx);
                    } else {
                      _collapsedExperiences.remove(idx);
                    }
                  });
                },
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        ProStatusBadge(text: exp.badge, color: scheme.primary),
                        Row(
                          children: [
                            Text(exp.period, style: Theme.of(context).textTheme.labelSmall),
                            const SizedBox(width: 4),
                            Icon(
                              expanded ? Icons.expand_less : Icons.expand_more,
                              size: 18,
                              color: scheme.onSurfaceVariant,
                            ),
                          ],
                        ),
                      ],
                    ),
                    const SizedBox(height: 10),
                    Text(
                      exp.role,
                      style: Theme.of(context).textTheme.titleMedium?.copyWith(
                            fontWeight: FontWeight.bold,
                          ),
                    ),
                    Text(
                      '${exp.organization} • ${exp.location}',
                      style: TextStyle(color: scheme.primary, fontWeight: FontWeight.w500),
                    ),
                    if (expanded) ...[
                      if (exp.galleryAssets.isNotEmpty) ...[
                        const SizedBox(height: 12),
                        Row(
                          children: exp.galleryAssets.map((asset) {
                            return Expanded(
                              child: Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 4),
                                child: ClipRRect(
                                  borderRadius: BorderRadius.circular(12),
                                  child: SizedBox(
                                    height: 164,
                                    child: Image.asset(asset, fit: BoxFit.cover),
                                  ),
                                ),
                              ),
                            );
                          }).toList(),
                        ),
                      ],
                      const SizedBox(height: 12),
                      ...exp.highlights.map(
                        (h) => Padding(
                          padding: const EdgeInsets.only(bottom: 4),
                          child: Row(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Icon(Icons.check_circle, size: 15, color: scheme.tertiary),
                              const SizedBox(width: 8),
                              Expanded(child: Text(h)),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ],
                ),
              ),
            );
          }),
        ],

        if (_selectedFilter == CvFilter.all || _selectedFilter == CvFilter.education) ...[
          const SectionHeader(title: 'Formation Académique'),
          const SizedBox(height: 8),
          ...DavidCvData.education.asMap().entries.map(
            (entry) => Padding(
              padding: const EdgeInsets.only(bottom: 12),
              child: AnimatedProCard(
                animationIndex: entry.key + 2,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        ProStatusBadge(text: entry.value.status, color: scheme.tertiary),
                        Text(entry.value.location, style: Theme.of(context).textTheme.labelSmall),
                      ],
                    ),
                    const SizedBox(height: 8),
                    Text(
                      entry.value.degree,
                      style: Theme.of(context).textTheme.titleMedium?.copyWith(
                            fontWeight: FontWeight.bold,
                          ),
                    ),
                    Text(entry.value.institution, style: TextStyle(color: scheme.primary)),
                    const SizedBox(height: 6),
                    Text(entry.value.details),
                  ],
                ),
              ),
            ),
          ),
        ],

        if (_selectedFilter == CvFilter.all || _selectedFilter == CvFilter.skills) ...[
          const SectionHeader(title: 'Compétences Techniques'),
          const SizedBox(height: 8),
          AnimatedProCard(
            animationIndex: 2,
            child: Column(
              children: DavidCvData.skills.map((skill) {
                return Padding(
                  padding: const EdgeInsets.only(bottom: 12),
                  child: AnimatedSkillBar(
                    name: skill.name,
                    category: skill.category,
                    level: skill.level,
                  ),
                );
              }).toList(),
            ),
          ),
          const SizedBox(height: 14),
          SectionHeader(title: 'Langues', accentColor: scheme.tertiary),
          const SizedBox(height: 8),
          AnimatedProCard(
            animationIndex: 3,
            child: Column(
              children: DavidCvData.languages.map((lang) {
                return Padding(
                  padding: const EdgeInsets.symmetric(vertical: 5),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(lang.name, style: const TextStyle(fontWeight: FontWeight.w600)),
                      ProStatusBadge(text: lang.level, color: scheme.tertiary),
                    ],
                  ),
                );
              }).toList(),
            ),
          ),
          const SizedBox(height: 14),
        ],

        if (_selectedFilter == CvFilter.all || _selectedFilter == CvFilter.interests) ...[
          SectionHeader(title: "Centres d'intérêt", accentColor: scheme.secondary),
          const SizedBox(height: 8),
          ...DavidCvData.interests.asMap().entries.map(
            (entry) => Padding(
              padding: const EdgeInsets.only(bottom: 12),
              child: AnimatedProCard(
                animationIndex: entry.key + 2,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          entry.value.title,
                          style: Theme.of(context).textTheme.titleMedium?.copyWith(
                                fontWeight: FontWeight.bold,
                              ),
                        ),
                        ProStatusBadge(text: entry.value.tag, color: scheme.secondary),
                      ],
                    ),
                    const SizedBox(height: 6),
                    Text(entry.value.description),
                    if (entry.value.photoAsset != null) ...[
                      const SizedBox(height: 10),
                      ClipRRect(
                        borderRadius: BorderRadius.circular(12),
                        child: SizedBox(
                          height: 166,
                          width: double.infinity,
                          child: Image.asset(entry.value.photoAsset!, fit: BoxFit.cover),
                        ),
                      ),
                    ],
                  ],
                ),
              ),
            ),
          ),
        ],
      ],
    );
  }
}
