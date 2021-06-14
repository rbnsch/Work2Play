package com.example.work2play;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest(AddReward.class)

public class FragmentRewardsTest {
    @Test
    public void testMethodReward() {
        final FragmentRewards fragmentRewards = mock(FragmentRewards.class);
        fragmentRewards.addReward(anyString(),anyInt(),anyBoolean());
        verify(fragmentRewards).addReward(anyString(),anyInt(),anyBoolean());

    }
}