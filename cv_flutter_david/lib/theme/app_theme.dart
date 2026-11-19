import 'package:flutter/material.dart';

class AppColors {
  // Clean Corporate Light Palette (Default)
  static const lightCanvasBg = Color(0xFFF8FAFC);
  static const lightSurface = Color(0xFFFFFFFF);
  static const lightSurfaceElevated = Color(0xFFF1F5F9);
  static const lightOutline = Color(0xFFE2E8F0);
  static const navyPrimary = Color(0xFF1E40AF);
  static const bluePrimarySoft = Color(0xFFDBEAFE);
  static const amberAccent = Color(0xFFB45309);
  static const emeraldVerified = Color(0xFF059669);
  static const lightTextPrimary = Color(0xFF0F172A);
  static const lightTextSecondary = Color(0xFF475569);

  // Executive Slate Dark Palette
  static const darkCanvasBg = Color(0xFF0F172A);
  static const darkSurface = Color(0xFF1E293B);
  static const darkSurfaceElevated = Color(0xFF273549);
  static const darkOutline = Color(0xFF334155);
  static const skyPrimaryDark = Color(0xFF60A5FA);
  static const amberDark = Color(0xFFFBBF24);
  static const emeraldDark = Color(0xFF34D399);
  static const darkTextPrimary = Color(0xFFF8FAFC);
  static const darkTextSecondary = Color(0xFFCBD5E1);
}

class AppTheme {
  static TextTheme _buildTextTheme(TextTheme base, Color primaryText, Color secondaryText) {
    return base.copyWith(
      displayLarge: base.displayLarge?.copyWith(
        fontFamily: 'Poppins',
        fontWeight: FontWeight.bold,
        color: primaryText,
      ),
      headlineMedium: base.headlineMedium?.copyWith(
        fontFamily: 'Poppins',
        fontWeight: FontWeight.w600,
        color: primaryText,
      ),
      headlineSmall: base.headlineSmall?.copyWith(
        fontFamily: 'Poppins',
        fontWeight: FontWeight.w600,
        color: primaryText,
      ),
      titleLarge: base.titleLarge?.copyWith(
        fontFamily: 'Poppins',
        fontWeight: FontWeight.w600,
        color: primaryText,
      ),
      titleMedium: base.titleMedium?.copyWith(
        fontFamily: 'Poppins',
        fontWeight: FontWeight.w600,
        color: primaryText,
      ),
      bodyLarge: base.bodyLarge?.copyWith(
        fontFamily: 'Inter',
        height: 1.5,
        color: primaryText,
      ),
      bodyMedium: base.bodyMedium?.copyWith(
        fontFamily: 'Inter',
        height: 1.45,
        color: secondaryText,
      ),
      bodySmall: base.bodySmall?.copyWith(
        fontFamily: 'Inter',
        color: secondaryText,
      ),
      labelLarge: base.labelLarge?.copyWith(
        fontFamily: 'Inter',
        fontWeight: FontWeight.w600,
      ),
      labelSmall: base.labelSmall?.copyWith(
        fontFamily: 'Inter',
        fontWeight: FontWeight.w500,
        color: secondaryText,
      ),
    );
  }

  static ThemeData lightTheme() {
    const scheme = ColorScheme.light(
      primary: AppColors.navyPrimary,
      onPrimary: Colors.white,
      primaryContainer: AppColors.bluePrimarySoft,
      secondary: AppColors.amberAccent,
      tertiary: AppColors.emeraldVerified,
      surface: AppColors.lightSurface,
      onSurface: AppColors.lightTextPrimary,
      surfaceContainerHighest: AppColors.lightSurfaceElevated,
      onSurfaceVariant: AppColors.lightTextSecondary,
      outline: AppColors.lightOutline,
    );

    return ThemeData(
      useMaterial3: true,
      fontFamily: 'Inter',
      colorScheme: scheme,
      scaffoldBackgroundColor: AppColors.lightCanvasBg,
      textTheme: _buildTextTheme(
        ThemeData.light().textTheme,
        AppColors.lightTextPrimary,
        AppColors.lightTextSecondary,
      ),
      appBarTheme: const AppBarTheme(
        backgroundColor: AppColors.lightSurface,
        foregroundColor: AppColors.lightTextPrimary,
        centerTitle: true,
        elevation: 0,
        scrolledUnderElevation: 1,
      ),
      navigationBarTheme: const NavigationBarThemeData(
        backgroundColor: AppColors.lightSurface,
        indicatorColor: AppColors.bluePrimarySoft,
      ),
    );
  }

  static ThemeData darkTheme() {
    const scheme = ColorScheme.dark(
      primary: AppColors.skyPrimaryDark,
      onPrimary: AppColors.darkCanvasBg,
      secondary: AppColors.amberDark,
      onSecondary: AppColors.darkCanvasBg,
      tertiary: AppColors.emeraldDark,
      onTertiary: AppColors.darkCanvasBg,
      surface: AppColors.darkSurface,
      onSurface: AppColors.darkTextPrimary,
      surfaceContainerHighest: AppColors.darkSurfaceElevated,
      onSurfaceVariant: AppColors.darkTextSecondary,
      outline: AppColors.darkOutline,
    );

    return ThemeData(
      useMaterial3: true,
      fontFamily: 'Inter',
      colorScheme: scheme,
      scaffoldBackgroundColor: AppColors.darkCanvasBg,
      textTheme: _buildTextTheme(
        ThemeData.dark().textTheme,
        AppColors.darkTextPrimary,
        AppColors.darkTextSecondary,
      ),
      appBarTheme: const AppBarTheme(
        backgroundColor: AppColors.darkSurface,
        foregroundColor: AppColors.darkTextPrimary,
        centerTitle: true,
        elevation: 0,
      ),
      navigationBarTheme: NavigationBarThemeData(
        backgroundColor: AppColors.darkSurface,
        indicatorColor: AppColors.skyPrimaryDark.withValues(alpha: 0.2),
      ),
    );
  }
}
