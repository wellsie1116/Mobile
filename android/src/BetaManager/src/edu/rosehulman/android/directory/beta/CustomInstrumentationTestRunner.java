package edu.rosehulman.android.directory.beta;

import java.util.Enumeration;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import android.os.Bundle;
import android.test.AndroidTestRunner;
import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;
import android.util.Log;
import edu.rosehulman.android.directory.tests.ui.MainActivityTest;

public class CustomInstrumentationTestRunner extends InstrumentationTestRunner implements TestListener{

	public static String TAG = "CustomInstrumentationTestRunner";
	
	@Override
	public void onCreate(Bundle arguments) {
		Log.w(TAG, "creating instrumentation");
		
		super.onCreate(arguments);
		//getAndroidTestRunner().getTestResult().addListener(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onStart() {
		Log.w(TAG, "starting instrumentation");
		/*
		getAndroidTestRunner().addTestListener(this);
		TestSuite suite = getAllTests();
		AndroidTestRunner runner = getAndroidTestRunner();
		//runner.addTestListener(this);
		runner.setTest(suite);
		runner.runTest();
		//*/
		
		AndroidTestRunner runner = getAndroidTestRunner();
		TestSuite suite = getTestSuite();
		runner.setContext(getContext());
		runner.setInstrumentaiton(this);
		runner.setTest(suite);
		TestResult result = new TestResult();
		result.addListener(this);
		runner.runTest(result);
		
		Log.e(TAG, String.format("Errors: %d", result.failureCount()));
		Enumeration<TestFailure> failures = (Enumeration<TestFailure>)result.failures();
		while (failures.hasMoreElements()) {
			TestFailure failure = failures.nextElement();
			Test test = failure.failedTest();
			Log.e(TAG, String.format("Test %s failed at\n%s with %s", test.toString(), failure.trace(), failure.thrownException().toString()));
			/*
			Log.e(TAG, "Trace: " + failure.trace());
			Log.e(TAG, "Exception Message: " + failure.exceptionMessage());
			Log.e(TAG, "Thrown Exception: " + failure.thrownException().toString());
			Log.e(TAG, "Test: " + test.toString());
			//Log.e(TAG, failure.getClass().getName());
			*/
		}

		Bundle bundle = new Bundle();
		finish(REPORT_VALUE_RESULT_OK, bundle);
	}
	
	@Override
	public void onDestroy() {
		Log.w(TAG, "destroying instrumentation");
		//TODO show log		
		super.onDestroy();
	}
	//*
    @Override
    public TestSuite getAllTests() {
        InstrumentationTestSuite suite = new InstrumentationTestSuite(this);

        //TODO add more test cases
        suite.addTestSuite(MainActivityTest.class);
        
        return suite;
    }

    @Override
    public ClassLoader getLoader() {
        return CustomInstrumentationTestRunner.class.getClassLoader();
    }
    //*/

	public void addError(Test test, Throwable t) {
		Log.e(TAG, "Caught error", t);		
	}

	public void addFailure(Test test, AssertionFailedError t) {
		Log.e(TAG, "Test Failed: " + test.toString());
		Log.e(TAG, "Message: " + t.getMessage());
	}

	public void endTest(Test test) {
		Log.d(TAG, "End Test: " + test.toString());
	}

	public void startTest(Test test) {
		Log.d(TAG, "Start Test: " + test.toString());
	}
	
}
