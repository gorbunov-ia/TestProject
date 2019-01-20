package ru.gorbunov.test.autoclosables;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

class LockHolderService {

    @Test
    void testWithoutException() {
        final LockService lockService = Mockito.mock(LockService.class);
        final String id1 = "id1";
        final String id2 = "id2";

        mockUsuallyAnswer(id1, lockService);
        mockUsuallyAnswer(id2, lockService);

        method(Arrays.asList(id1, id2), lockService);

        Mockito.verify(lockService).unlock(id1);
        Mockito.verify(lockService).unlock(id2);
    }

    @Test
    void testSecondLockDoNotClose() {
        final LockService lockService = Mockito.mock(LockService.class);
        final String id1 = "id1";
        final String id2 = "id2";

        mockUsuallyAnswer(id1, lockService);
        mockUsuallyAnswer(id2, lockService);
        Mockito.doThrow(new RuntimeException()).when(lockService).unlock(Mockito.eq(id1));

        method(Arrays.asList(id1, id2), lockService);

        Mockito.verify(lockService).unlock(id1);
        Mockito.verify(lockService).unlock(id2);
    }

    @Test
    void testSecondLockDoNotGet() {
        final LockService lockService = Mockito.mock(LockService.class);
        final String id1 = "id1";
        final String id2 = "id2";

        mockUsuallyAnswer(id1, lockService);
        Mockito.when(lockService.lock(Mockito.eq(id2))).thenThrow(new RuntimeException());

        try {
            method(Arrays.asList(id1, id2), lockService);
            //check "method" catch block
            //Assertions.fail();
        } catch (Exception e) {
            //ignore
        }

        Mockito.verify(lockService).unlock(id1);
    }

    private void mockUsuallyAnswer(String id, LockService mockService) {
        Mockito.when(mockService.lock(Mockito.eq(id))).thenReturn(() -> mockService.unlock(id));

    }

    private void method(Collection<String> ids, LockService lockService) {
        try (AutoCloseable locks = getLocks(ids, lockService)) {
            System.out.println("locks got");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AutoCloseable getLocks(Collection<String> ids, LockService lockService) {
        final ArrayList<AutoCloseable> locks = new ArrayList<>();
        for (String id : ids) {
            try {
                locks.add(lockService.lock(id));
            } catch (Exception e) {
                new LockHolder(locks).close();
                throw e;
            }
        }
        return new LockHolder(locks);
    }
}
