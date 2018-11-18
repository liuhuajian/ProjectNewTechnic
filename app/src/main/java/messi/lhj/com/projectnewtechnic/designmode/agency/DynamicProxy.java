package messi.lhj.com.projectnewtechnic.designmode.agency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import messi.lhj.com.projectnewtechnic.designmode.agency.Subject;

/**
 * Created by messi on 2018/7/24.
 */

public class DynamicProxy implements InvocationHandler {

    private Object object;

    public DynamicProxy(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object subject = method.invoke(object, args);
        return subject;
    }
}
