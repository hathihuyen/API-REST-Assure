package com.hha.apiexamples.TestSuite;

import com.hha.apiexamples.Testcases.Example1Test;
import com.hha.apiexamples.Testcases.Example2Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Example1Test.class,
        Example2Test.class,
})

public class AllApiTest {
}