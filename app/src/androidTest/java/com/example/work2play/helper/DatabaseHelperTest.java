package com.example.work2play.helper;

import com.example.work2play.model.Reward;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabaseHelperTest {

    @Test
    void testMethod(){
        final DatabaseHelper databaseHelper = mock(DatabaseHelper.class);

        List<Reward> rewardList = new ArrayList<>();
        verify(databaseHelper).getAllRewards();
    }
}