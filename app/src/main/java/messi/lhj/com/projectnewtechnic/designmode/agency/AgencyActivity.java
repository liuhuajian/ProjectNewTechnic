package messi.lhj.com.projectnewtechnic.designmode.agency;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.Proxy;

import messi.lhj.com.projectnewtechnic.R;
import messi.lhj.com.projectnewtechnic.designmode.agency.RealSubject;
import messi.lhj.com.projectnewtechnic.designmode.agency.Subject;

public class AgencyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);
        testDynamicProxy();
    }

    private void testDynamicProxy() {
        Subject realSubject = new RealSubject();
        DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = dynamicProxy.getClass().getClassLoader();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, new Class[]{realSubject.getClass()}, dynamicProxy);
        subject.displayType();

        Intent intent = new Intent();
        startActivity(intent);
    }
}
