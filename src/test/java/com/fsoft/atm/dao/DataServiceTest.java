package com.fsoft.atm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.*;

//import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataService.class)
public class DataServiceTest {

    @Test
    public void testReplaceData() throws Exception {
        final String modifyDataMethodName = "modifyData";
        final byte[] expectedBinaryData = new byte[] { 42 };
        final String expectedDataId = "id";

        // Mock only the modifyData method
        DataService tested = createPartialMock(DataService.class, modifyDataMethodName);

        // Expect the private method call to "modifyData"
        expectPrivate(tested, modifyDataMethodName, expectedDataId, expectedBinaryData).andReturn(true);
        replay(tested);

        assertTrue(tested.replaceData(expectedDataId, expectedBinaryData));

        verify(tested);
    }
}
