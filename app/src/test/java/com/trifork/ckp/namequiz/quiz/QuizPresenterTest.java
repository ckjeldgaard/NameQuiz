package com.trifork.ckp.namequiz.quiz;

import com.trifork.ckp.namequiz.data.Repository;
import com.trifork.ckp.namequiz.fakes.FakeDepartmentsFactory;
import com.trifork.ckp.namequiz.model.Department;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class QuizPresenterTest {

    private static final Department DEPARTMENT = new FakeDepartmentsFactory().produceDepartment("department.json");

    private QuizPresenter quizPresenter;

    @Mock
    private QuizView quizView;

    @Mock
    private Repository repository;

    @Before
    public void setupQuizPresenter() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.quizPresenter = new QuizPresenter(repository, quizView);
    }

    @Test
    public void testLoadPersons() throws Exception {
        this.quizPresenter.loadPersons(DEPARTMENT);
    }
}