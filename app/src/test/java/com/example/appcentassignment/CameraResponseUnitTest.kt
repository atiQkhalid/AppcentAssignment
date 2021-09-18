package com.example.appcentassignment

import com.example.appcentassignment.models.response.Camera
import com.example.appcentassignment.models.response.Photo
import com.example.appcentassignment.models.response.Rover
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CameraResponseUnitTest {
    @Test
    fun checkIfCameraResponseValid() {
        val camera = Camera("attiQ", 1, "A", 23165431)
        assertEquals(camera.full_name, "attiQ")
        assertEquals(camera.id, 1)
        assertEquals(camera.name, "A")
        assertEquals(camera.rover_id, 23165431)
    }


}