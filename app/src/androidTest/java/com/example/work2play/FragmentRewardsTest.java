package com.example.work2play;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(PowerMockRunner.class)
@PrepareForTest(addReward)

class FragmentRewardsTest {
    @Test
    void testMethod() {
        final FragmentRewards fragmentRewards = mock(FragmentRewards.class);
        PowerMockito.verifyStatic(fragmentRewards.addReward); //powermock for mocking statics
        fragmentRewards.addReward.staticMethod();

    }
}