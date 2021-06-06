package com.example.work2play;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FragmentTasksTest {
     @Test
     void testMethodTask() {
         final FragmentTasks fragmentTasks = mock(FragmentTasks.class);
         fragmentTasks.addTask(anyString(),anyInt());
         verify(fragmentTasks).addTask(anyString(),anyInt());


         //PowerMockito.verifyStatic(AddReward.class); //powermock for mocking statics
         //AddReward.staticMethod();
     }

}