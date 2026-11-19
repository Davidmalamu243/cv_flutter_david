package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.FamilyLayoutMode
import com.example.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `app name matches CV David`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("CV David", appName)
    }

    @Test
    fun `login validation rejects empty fields and accepts valid credentials`() {
        val vm = MainViewModel()
        assertFalse(vm.submitLogin())
        assertNotNull(vm.loginState.value.identifierError)
        assertNotNull(vm.loginState.value.passwordError)

        vm.fillDemoCredentials()
        assertTrue(vm.submitLogin())
        assertTrue(vm.loginState.value.isAuthenticated)

        vm.logout()
        assertFalse(vm.loginState.value.isAuthenticated)
    }

    @Test
    fun `profile and family layout mode work correctly`() {
        val vm = MainViewModel()
        assertEquals("CIZA ELMALAMU DAVID", vm.profile.fullName)
        assertEquals("13/02/2004", vm.profile.birthDate)
        assertEquals(FamilyLayoutMode.LIST, vm.familyLayoutMode.value)

        vm.setFamilyLayoutMode(FamilyLayoutMode.GRID)
        assertEquals(FamilyLayoutMode.GRID, vm.familyLayoutMode.value)
    }
}
