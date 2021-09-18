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
class RoverResponseUnitTest {
    @Test
    fun checkIfRoverResponseValid() {
        val rover = Rover(1, "19-12-2020", "12-12-2020", "Any String", "Active")
        assertEquals(rover.id, 1)
        assertEquals(rover.landing_date, "19-12-2020")
        assertEquals(rover.launch_date, "12-12-2020")
        assertEquals(rover.name, "Any String")
        assertEquals(rover.status, "Active")
    }


}