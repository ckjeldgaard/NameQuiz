package com.trifork.ckp.namequiz.start;

import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.fakes.FakeDepartmentsFactory;
import com.trifork.ckp.namequiz.model.Department;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class StartPresenterTest {

    private static final Department DEPARTMENT = new FakeDepartmentsFactory().produceDepartment("department.json");
    private static List<Department> DEPARTMENTS = new ArrayList<Department>() {{
        add(DEPARTMENT);
        add(DEPARTMENT);
    }};

    @Mock
    private Repository repository;

    @Mock
    private StartContract.StartView startView;

    @Captor
    private ArgumentCaptor<Repository.LoadDepartmentsCallback> loadDepartmentsCallbackCaptor;

    private StartPresenter startPresenter;

    @Before
    public void setupStartPresenter() {
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        startPresenter = new StartPresenter(repository, startView);
    }

    @Test
    public void loadDepartmentsFromRepositoryAndLoadIntoView() {
        startPresenter.loadDepartments();

        // Callback is captured and invoked with stubbed departments
        verify(repository).getDepartments(loadDepartmentsCallbackCaptor.capture());
        loadDepartmentsCallbackCaptor.getValue().onDepartmentsLoaded(DEPARTMENTS);

        // Then progress indicator is hidden and departments are shown in UI
        verify(startPresenter.getView()).showLoading(false);
        verify(startPresenter.getView()).setData(DEPARTMENTS);
        verify(startPresenter.getView()).showContent();
    }

    @Test
    public void testStartQuiz() throws Exception {
        startPresenter.startNewQuiz(DEPARTMENTS.get(0));
        verify(startPresenter.getView()).showStartQuiz(DEPARTMENTS.get(0));
    }
}
