import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:url_launcher/url_launcher.dart';
import '../data/david_cv_data.dart';
import '../models/cv_models.dart';
import '../widgets/futuristic_widgets.dart';

class ContactScreen extends StatelessWidget {
  const ContactScreen({super.key});

  Future<void> _openContact(BuildContext context, ContactItem item) async {
    final uri = Uri.parse(item.uri);
    final launched = await launchUrl(uri, mode: LaunchMode.externalApplication);
    if (!launched && context.mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Aucune application compatible trouvée.')),
      );
    }
  }

  Future<void> _copyValue(BuildContext context, String value) async {
    await Clipboard.setData(ClipboardData(text: value));
    if (context.mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Copié dans le presse-papiers : $value')),
      );
    }
  }

  IconData _iconFor(ContactActionType type) {
    switch (type) {
      case ContactActionType.phone:
        return Icons.call;
      case ContactActionType.email:
        return Icons.email;
      case ContactActionType.map:
        return Icons.location_on;
      case ContactActionType.web:
        return Icons.public;
    }
  }

  @override
  Widget build(BuildContext context) {
    final scheme = Theme.of(context).colorScheme;
    return ListView(
      padding: const EdgeInsets.fromLTRB(16, 14, 16, 24),
      children: [
        const SectionHeader(title: 'Coordonnées'),
        const SizedBox(height: 6),
        Text(
          'Disponible pour des missions en électricité, réseaux, sécurité CCTV, partenariats éducatifs et production musicale.',
          style: TextStyle(color: scheme.onSurfaceVariant),
        ),
        const SizedBox(height: 14),
        ...DavidCvData.contacts.asMap().entries.map((entry) {
          final idx = entry.key;
          final item = entry.value;
          return Padding(
            padding: const EdgeInsets.only(bottom: 12),
            child: AnimatedProCard(
              animationIndex: idx,
              onTap: () => _openContact(context, item),
              child: Row(
                children: [
                  Container(
                    width: 46,
                    height: 46,
                    decoration: BoxDecoration(
                      color: scheme.primary.withValues(alpha: 0.12),
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: Icon(_iconFor(item.type), color: scheme.primary),
                  ),
                  const SizedBox(width: 14),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          item.label,
                          style: TextStyle(
                            color: scheme.primary,
                            fontSize: 12,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                        const SizedBox(height: 2),
                        Text(
                          item.value,
                          style: const TextStyle(fontWeight: FontWeight.w600, fontSize: 15),
                        ),
                        Text(
                          item.actionHint,
                          style: Theme.of(context).textTheme.labelSmall,
                        ),
                      ],
                    ),
                  ),
                  IconButton(
                    onPressed: () => _copyValue(context, item.value),
                    icon: const Icon(Icons.copy),
                    tooltip: 'Copier',
                  ),
                ],
              ),
            ),
          );
        }),
        const SizedBox(height: 6),
        SectionHeader(title: 'Structures & Projets', accentColor: scheme.secondary),
        const SizedBox(height: 8),
        AnimatedProCard(
          animationIndex: DavidCvData.contacts.length,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              ProStatusBadge(text: 'Bukavu • Éducation', color: scheme.secondary),
              const SizedBox(height: 6),
              const Text(
                'Complexe Scolaire MALKIA',
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
              ),
              const Text('Promoteur — Contact via +243 997 658 995'),
              const SizedBox(height: 14),
              Divider(color: scheme.outline.withValues(alpha: 0.6)),
              const SizedBox(height: 14),
              ProStatusBadge(text: 'Goma • Production musicale', color: scheme.primary),
              const SizedBox(height: 6),
              const Text(
                'DUNDA Production',
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
              ),
              const Text('Fondateur — Booking & collaboration : davselmalamu0@gmail.com'),
            ],
          ),
        ),
      ],
    );
  }
}
