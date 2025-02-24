package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class RetryListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        Throwable throwable = result.getThrowable();
        System.out.println("Test failed: "+result.getTestName()+"\n Issue: "+throwable.getMessage());
    }

    @Override
    public void onStart(ITestContext context){
        System.out.println(context.getName()+" Test started");
    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println(context.getName()+" Test finished");
    }
}
