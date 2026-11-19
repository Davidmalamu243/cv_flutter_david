import 'package:flutter/material.dart';
import 'screens/contact_screen.dart';
import 'screens/cv_screen.dart';
import 'screens/family_screen.dart';
import 'screens/login_screen.dart';
import 'theme/app_theme.dart';
import 'widgets/futuristic_widgets.dart';

void main() {
  runApp(const CvDavidFlutterApp());
}

class CvDavidFlutterApp extends StatefulWidget {
  const CvDavidFlutterApp({super.key});

  @override
  State<CvDavidFlutterApp> createState() => _CvDavidFlutterAppState();
}

class _CvDavidFlutterAppState extends State<CvDavidFlutterApp> {
  bool _isDark = false;
  bool _isAuthenticated = false;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CV David',
      debugShowCheckedModeBanner: false,
      theme: AppTheme.lightTheme(),
      darkTheme: AppTheme.darkTheme(),
      themeMode: _isDark ? ThemeMode.dark : ThemeMode.light,
      home: _isAuthenticated
          ? HomeShell(
              isDark: _isDark,
              onToggleTheme: () => setState(() => _isDark = !_isDark),
              onLogout: () => setState(() => _isAuthenticated = false),
            )
          : LoginScreen(
              onLoginSuccess: () => setState(() => _isAuthenticated = true),
            ),
    );
  }
}

class HomeShell extends StatefulWidget {
  const HomeShell({
    super.key,
    required this.isDark,
    required this.onToggleTheme,
    required this.onLogout,
  });

  final bool isDark;
  final VoidCallback onToggleTheme;
  final VoidCallback onLogout;

  @override
  State<HomeShell> createState() => _HomeShellState();
}

class _HomeShellState extends State<HomeShell> {
  int _selectedIndex = 0;

  static const _titles = ['Mon CV', 'Famille', 'Contact'];
  static const _pages = [
    CvScreen(),
    FamilyScreen(),
    ContactScreen(),
  ];

  @override
  Widget build(BuildContext context) {
    final scheme = Theme.of(context).colorScheme;
    return Scaffold(
      appBar: AppBar(
        title: Column(
          children: [
            Text(
              _titles[_selectedIndex],
              style: Theme.of(context).textTheme.titleMedium,
            ),
            Text(
              'Ciza Elmalamu David',
              style: TextStyle(fontSize: 11, color: scheme.onSurfaceVariant),
            ),
          ],
        ),
        actions: [
          IconButton(
            onPressed: widget.onToggleTheme,
            icon: Icon(widget.isDark ? Icons.light_mode : Icons.dark_mode),
            tooltip: 'Changer le thème',
          ),
          IconButton(
            onPressed: widget.onLogout,
            icon: Icon(Icons.logout, color: scheme.onSurfaceVariant),
            tooltip: 'Déconnexion',
          ),
        ],
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(1),
          child: Divider(height: 1, color: scheme.outline.withValues(alpha: 0.7)),
        ),
      ),
      drawer: Drawer(
        child: Column(
          children: [
            UserAccountsDrawerHeader(
              accountName: const Text('Ciza Elmalamu David'),
              accountEmail: const Text('L4 ISIG-Goma • Quartier Himbi'),
              currentAccountPicture: const CircleAvatar(
                backgroundImage: AssetImage('assets/images/img_david_profile.jpg'),
              ),
              decoration: BoxDecoration(color: scheme.primary),
            ),
            ListTile(
              leading: const Icon(Icons.badge),
              title: const Text('Mon CV'),
              selected: _selectedIndex == 0,
              onTap: () {
                setState(() => _selectedIndex = 0);
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: const Icon(Icons.family_restroom),
              title: const Text('Famille'),
              selected: _selectedIndex == 1,
              onTap: () {
                setState(() => _selectedIndex = 1);
                Navigator.pop(context);
              },
            ),
            ListTile(
              leading: const Icon(Icons.contact_phone),
              title: const Text('Contact'),
              selected: _selectedIndex == 2,
              onTap: () {
                setState(() => _selectedIndex = 2);
                Navigator.pop(context);
              },
            ),
            const Spacer(),
            const Divider(),
            ListTile(
              leading: Icon(Icons.logout, color: scheme.error),
              title: const Text('Déconnexion'),
              onTap: () {
                Navigator.pop(context);
                widget.onLogout();
              },
            ),
          ],
        ),
      ),
      body: CleanBackdrop(child: _pages[_selectedIndex]),
      bottomNavigationBar: NavigationBar(
        selectedIndex: _selectedIndex,
        onDestinationSelected: (index) => setState(() => _selectedIndex = index),
        destinations: const [
          NavigationDestination(
            icon: Icon(Icons.badge_outlined),
            selectedIcon: Icon(Icons.badge),
            label: 'Mon CV',
          ),
          NavigationDestination(
            icon: Icon(Icons.family_restroom_outlined),
            selectedIcon: Icon(Icons.family_restroom),
            label: 'Famille',
          ),
          NavigationDestination(
            icon: Icon(Icons.contact_phone_outlined),
            selectedIcon: Icon(Icons.contact_phone),
            label: 'Contact',
          ),
        ],
      ),
    );
  }
}
