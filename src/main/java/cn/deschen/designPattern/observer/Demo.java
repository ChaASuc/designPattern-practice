package cn.deschen.designPattern.observer;

import cn.deschen.designPattern.observer.observer.FileObserver;
import cn.deschen.designPattern.observer.observer.impl.FileNotifyObserver;
import cn.deschen.designPattern.observer.observer.impl.FileSearchObserver;
import cn.deschen.designPattern.observer.subject.impl.FileSubjectImpl;

/**
 * @Author hanbin_chen
 * @Description 观察者模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {

        FileObserver searchObserver = new FileSearchObserver();
        FileObserver notifyObserver = new FileNotifyObserver();

        FileSubjectImpl fileSubject = new FileSubjectImpl();
        fileSubject.attach(searchObserver);
        fileSubject.attach(notifyObserver);

        fileSubject.uploadFile("/resource", "test.doc");
        fileSubject.deleteFile("/resource", "test01.doc");

        fileSubject.detach(notifyObserver);
        fileSubject.uploadFile("/resource", "test02.doc");
    }
}
