package com.example.mybasicapplication

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

private const val LAUNCH_TIMEOUT = 6500L
private const val BASIC_SAMPLE_PACKAGE = "com.example.mybasicapplication"

@RunWith(AndroidJUnit4::class)

class ExampleInstrumentedTest {
    private lateinit var device: UiDevice
    @Before
    fun setup()
    {
        val packageName = "com.example.mybasicapplication"
        val appName = "My Basic Application"

        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        val appIcon = device.findObject(By.text(appName))
        appIcon.click()

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )

    }


    @Test
    fun startAppFromHomeScreen() {

        val packageName = "com.example.mybasicapplication"
        val appName = "My Basic Application"

        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )



        val appIcon = device.findObject(By.text(appName))
        assertThat("App icon not found", appIcon, notNullValue())
        appIcon.click()

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )


    }

    @Test
    fun startActivityExplicitlyButton() {
        val startButton = device.findObject(By.text("Explicit Button"))
        assertThat("Button not found", startButton, notNullValue())
        startButton.click()
    }

    @Test
    fun readChallengesFromSecondActivity() {
        val startButton = device.findObject(By.text("Explicit Button"))
        startButton.click()

        val chalengeText = device.findObject(By.textContains("Software Engineering Challenges"))
        assertTrue("Text doesn't match", chalengeText.text.contains("It was challenging"))
    }
}