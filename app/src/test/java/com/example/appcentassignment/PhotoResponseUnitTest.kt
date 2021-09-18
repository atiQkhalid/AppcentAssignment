package com.example.appcentassignment

import com.example.appcentassignment.models.response.Camera
import com.example.appcentassignment.models.response.Photo
import com.example.appcentassignment.models.response.Rover
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PhotoResponseUnitTest {
    @Test
    fun checkIfPhotoResponseValid() {

        val camera = Camera("attiQ", 1, "A", 23165431)
        val rover = Rover(1, "19-12-2020", "12-12-2020", "Any String", "Active")

        val photo = Photo(
            camera,
            "19-12-2020",
            10,
            "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FRB_486265257EDR_F0481570FHAZ00323M_.JPG",
            rover,
            5
        )

        Assert.assertEquals(photo.camera.rover_id, 23165431)
        Assert.assertEquals(photo.camera.name, "A")
        Assert.assertEquals(photo.camera.id, 1)
        Assert.assertEquals(photo.camera.full_name, "attiQ")
        Assert.assertEquals(photo.earth_date, "19-12-2020")
        Assert.assertEquals(photo.id, 10)
        Assert.assertEquals(photo.img_src, "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FRB_486265257EDR_F0481570FHAZ00323M_.JPG")
        Assert.assertEquals(photo.rover.landing_date, "19-12-2020")
        Assert.assertEquals(photo.rover.launch_date, "12-12-2020")
        Assert.assertEquals(photo.rover.name, "Any String")
        Assert.assertEquals(photo.rover.status, "Active")
        Assert.assertEquals(photo.sol, 5)
    }
}