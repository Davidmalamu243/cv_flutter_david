import 'package:flutter/material.dart';
import '../data/david_cv_data.dart';
import '../models/cv_models.dart';
import '../widgets/futuristic_widgets.dart';

class FamilyScreen extends StatefulWidget {
  const FamilyScreen({super.key});

  @override
  State<FamilyScreen> createState() => _FamilyScreenState();
}

class _FamilyScreenState extends State<FamilyScreen> {
  bool _isGrid = false;

  void _showMemberDetail(FamilyMember member) {
    final scheme = Theme.of(context).colorScheme;
    showModalBottomSheet<void>(
      context: context,
      showDragHandle: true,
      builder: (context) => Padding(
        padding: const EdgeInsets.fromLTRB(24, 8, 24, 32),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            CircleAvatar(
              radius: 48,
              backgroundImage: AssetImage(member.photoAsset),
            ),
            const SizedBox(height: 14),
            ProStatusBadge(text: member.relationship, color: scheme.primary),
            const SizedBox(height: 8),
            Text(
              member.fullName,
              style: Theme.of(context).textTheme.headlineSmall?.copyWith(fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 4),
            Text(
              '${member.profession} • ${member.city}',
              style: TextStyle(color: scheme.primary),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 12),
            Text(member.bio, textAlign: TextAlign.center),
          ],
        ),
      ),
    );
  }

  Widget _buildGroupPhotoCard() {
    return AnimatedProCard(
      animationIndex: 0,
      highlightBorder: true,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const ProStatusBadge(text: 'Photo de famille'),
          const SizedBox(height: 10),
          ClipRRect(
            borderRadius: BorderRadius.circular(12),
            child: SizedBox(
              height: 250,
              width: double.infinity,
              child: Image.asset(
                'assets/images/img_family_group.jpg',
                fit: BoxFit.cover,
              ),
            ),
          ),
          const SizedBox(height: 10),
          const Text(
            'Isabelle Ntaliboba avec mes grandes sœurs Danette et Daniella Malamu',
            style: TextStyle(fontWeight: FontWeight.w500),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final scheme = Theme.of(context).colorScheme;
    const members = DavidCvData.familyMembers;

    return Padding(
      padding: const EdgeInsets.fromLTRB(16, 14, 16, 0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          const SectionHeader(title: 'Ma Famille'),
          const SizedBox(height: 6),
          Text(
            'Les membres de ma famille et les piliers qui accompagnent mon parcours entre Goma et Bukavu.',
            style: TextStyle(color: scheme.onSurfaceVariant),
          ),
          const SizedBox(height: 12),
          SegmentedButton<bool>(
            segments: const [
              ButtonSegment(value: false, icon: Icon(Icons.view_list), label: Text('Liste')),
              ButtonSegment(value: true, icon: Icon(Icons.grid_view), label: Text('Grille')),
            ],
            selected: {_isGrid},
            onSelectionChanged: (values) => setState(() => _isGrid = values.first),
          ),
          const SizedBox(height: 12),
          Expanded(
            child: AnimatedSwitcher(
              duration: const Duration(milliseconds: 280),
              child: _isGrid
                  ? CustomScrollView(
                      key: const ValueKey('family_grid'),
                      slivers: [
                        SliverToBoxAdapter(
                          child: Padding(
                            padding: const EdgeInsets.only(bottom: 12),
                            child: _buildGroupPhotoCard(),
                          ),
                        ),
                        SliverPadding(
                          padding: const EdgeInsets.only(bottom: 20),
                          sliver: SliverGrid(
                            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                              crossAxisCount: 2,
                              crossAxisSpacing: 12,
                              mainAxisSpacing: 12,
                              childAspectRatio: 0.78,
                            ),
                            delegate: SliverChildBuilderDelegate(
                              (context, index) {
                                final m = members[index];
                                return AnimatedProCard(
                                  animationIndex: index + 1,
                                  onTap: () => _showMemberDetail(m),
                                  child: Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      CircleAvatar(
                                        radius: 36,
                                        backgroundImage: AssetImage(m.photoAsset),
                                      ),
                                      const SizedBox(height: 10),
                                      ProStatusBadge(
                                        text: m.relationship,
                                        color: scheme.primary,
                                      ),
                                      const SizedBox(height: 6),
                                      Text(
                                        m.fullName,
                                        textAlign: TextAlign.center,
                                        maxLines: 2,
                                        overflow: TextOverflow.ellipsis,
                                        style: const TextStyle(fontWeight: FontWeight.w600),
                                      ),
                                      const SizedBox(height: 4),
                                      Text(
                                        m.profession,
                                        textAlign: TextAlign.center,
                                        maxLines: 2,
                                        overflow: TextOverflow.ellipsis,
                                        style: Theme.of(context).textTheme.bodySmall,
                                      ),
                                    ],
                                  ),
                                );
                              },
                              childCount: members.length,
                            ),
                          ),
                        ),
                      ],
                    )
                  : ListView.separated(
                      key: const ValueKey('family_list'),
                      padding: const EdgeInsets.only(bottom: 20),
                      itemCount: members.length + 1,
                      separatorBuilder: (_, __) => const SizedBox(height: 12),
                      itemBuilder: (context, index) {
                        if (index == 0) return _buildGroupPhotoCard();
                        final m = members[index - 1];
                        return AnimatedProCard(
                          animationIndex: index,
                          onTap: () => _showMemberDetail(m),
                          child: Row(
                            children: [
                              CircleAvatar(
                                radius: 34,
                                backgroundImage: AssetImage(m.photoAsset),
                              ),
                              const SizedBox(width: 14),
                              Expanded(
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    ProStatusBadge(
                                      text: m.relationship,
                                      color: scheme.primary,
                                    ),
                                    const SizedBox(height: 6),
                                    Text(
                                      m.fullName,
                                      style: Theme.of(context).textTheme.titleMedium?.copyWith(
                                            fontWeight: FontWeight.w600,
                                          ),
                                    ),
                                    Text(m.profession),
                                    Text(
                                      m.city,
                                      style: Theme.of(context).textTheme.labelSmall,
                                    ),
                                  ],
                                ),
                              ),
                            ],
                          ),
                        );
                      },
                    ),
            ),
          ),
        ],
      ),
    );
  }
}
