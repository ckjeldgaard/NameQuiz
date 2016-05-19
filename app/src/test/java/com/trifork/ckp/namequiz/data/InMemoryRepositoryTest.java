package com.trifork.ckp.namequiz.data;

import com.trifork.ckp.namequiz.model.Department;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of the in-memory repository with cache.
 */
public class InMemoryRepositoryTest {

    private static List<Department> DEPARTMENTS = new ArrayList<Department>() {{
        add(new Department(1, "Copenhagen"));
        add(new Department(2, "Aarhus"));
    }};

    private InMemoryRepository repository;

    @Mock
    private ServiceApi serviceApi;

    @Mock
    private Repository.LoadDepartmentsCallback loadDepartmentsCallback;

    @Mock
    private Repository.LoadQuizCallback loadQuizCallback;

    @Captor
    private ArgumentCaptor<ServiceApi.ServiceCallback> serviceCallbackArgumentCaptor;

    @Before
    public void setupDepartmentsRepository() {
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        repository = new InMemoryRepository(serviceApi);
    }

    @Test
    public void getDepartments_repositoryCachesAfterFirstApiCall() {
        // Given a setup Captor to capture callbacks
        // When two calls are issued to the departments repository
        twoLoadCallsToRepository(repository, loadDepartmentsCallback);

        // Then departments where only requested once from Service API
        verify(serviceApi).getAllDepartments(any(ServiceApi.ServiceCallback.class));
    }

    @Test
    public void invalidateCache_DoesNotCallTheServiceApi() {
        Repository repo = new InMemoryRepository(serviceApi);

        // Given a setup Captor to capture callbacks
        twoLoadCallsToRepository(repo, loadDepartmentsCallback);

        // When data refresh is requested
        repository.refreshData();
        repository.getDepartments(loadDepartmentsCallback); // Third call to API

        // The departments where requested twice from the Service API (Caching on first and third call)
        verify(serviceApi, times(2)).getAllDepartments(any(ServiceApi.ServiceCallback.class));
    }

    @Test
    public void getDepartments_requestsAllDepartmentsFromServiceApi() {
        // When departments are requested from the notes repository
        repository.getDepartments(loadDepartmentsCallback);

        // Then notes are loaded from the service API
        verify(serviceApi).getAllDepartments(any(ServiceApi.ServiceCallback.class));
    }

    @Test
    public void getPersonsBelongingToDepartment_requestsPersonsFromServiceApi() throws Exception {
        Department department = new Department(1, "Copenhagen");

        repository.produceQuiz(loadQuizCallback, department.getId());
        verify(serviceApi).getAllDepartments(any(ServiceApi.ServiceCallback.class));
        //verify(serviceApi).getPersonsBelongingToDepartment(any(ServiceApi.ServiceCallback.class), eq(department));
    }

    /**
     * Convenience method that issues two calls to the departments repository
     */
    private void twoLoadCallsToRepository(Repository repo, Repository.LoadDepartmentsCallback callback) {
        // When departments are requested from repository
        repo.getDepartments(callback); // First call to API

        // Use the Mockito Captor to capture the callback
        verify(serviceApi).getAllDepartments(serviceCallbackArgumentCaptor.capture());

        // Trigger callback so departments are cached
        serviceCallbackArgumentCaptor.getValue().onLoaded(DEPARTMENTS);

        repo.getDepartments(callback); // Second call to API
    }
}
