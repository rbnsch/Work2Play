package com.example.work2play;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest(AddReward.class)

public class FragmentRewardsTest {
    @Test
    void testMethod() {
        final FragmentRewards fragmentRewards = mock(FragmentRewards.class);
        static String testStr = anyString();
        static Integer testInt = anyInt();
        static Boolean testBool = anyBoolean();
        fragmentRewards.addReward(testStr,testInt,testBool);
        verify(fragmentRewards).addReward(testStr,testInt,testBool);


        //PowerMockito.verifyStatic(AddReward.class); //powermock for mocking statics
        //AddReward.staticMethod();
    }
}