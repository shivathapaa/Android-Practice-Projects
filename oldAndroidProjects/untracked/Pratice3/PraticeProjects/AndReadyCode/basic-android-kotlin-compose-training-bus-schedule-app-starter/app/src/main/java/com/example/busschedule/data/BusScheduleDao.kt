package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    // The query now says to select all columns from the items, where the stopName matches the :id argument. Notice the :id uses the colon notation in the query to reference arguments in the function.
    @Query(
        """
        SELECT * FROM schedule 
        WHERE stop_name = :stopName 
        ORDER BY arrival_time ASC 
        """
    )
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
    //It is recommended to use Flow in the persistence layer. With Flow as the return type, you receive notification whenever the data in the database changes. The Room keeps this Flow updated for you, which means you only need to explicitly get the data once.

    @Query(
        """
        SELECT * FROM schedule 
        ORDER BY arrival_time ASC    
        """
    )
    fun getAll(): Flow<List<BusSchedule>>
}