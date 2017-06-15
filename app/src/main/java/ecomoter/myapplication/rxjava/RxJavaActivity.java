package ecomoter.myapplication.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ecomoter.myapplication.R;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG="RXJAVA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
//        rxJava();
//        rxJavaAction();
//        rxJavaMap();
        rxJavaFlatMap();
    }


    /**
     * RxJava基本使用
     */
    private void rxJava() {
        /**
         * 创建观察者
         */
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"onCompleted");

            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,s);
            }
        };
        /**
         * 创建被观察者
         * 方法一
         */
        Observable observable=Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {

                    subscriber.onNext("rxjava1");
                    subscriber.onNext("rxjava2");
                    subscriber.onCompleted();
            }
        });

        /**
         * 方法二
         */
        Observable observable1=Observable.just("rxjava1","rxjava2");

        /**
         * 方法三
         * 数组或集合
         */
        String [] words = {"rxjava1", "rxjava2"};
        Observable observable2=Observable.from(words);

        /**
         * 订阅
         */
        observable.subscribe(observer);
    }

    /**
     * RxJava Action使用
     */
    private void rxJavaAction(){
        Action0 onCompleteAction=new Action0() {
            @Override
            public void call() {
                Log.d(TAG,"onCompleted");
            }
        } ;

        Action1<String> onNextAction=new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG,s);
            }
        };

        Action1<Throwable> onErrorAction=new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG,"onError");
            }
        };

        Observable observable=Observable.just("hello","world");
        observable.subscribe(onNextAction,onErrorAction,onCompleteAction);
    }

    /**
     * map使用
     */
    private void rxJavaMap(){
        Student student1=new Student();
        student1.setName("张三");
        Student student2=new Student();
        student2.setName("李四");

        Observable.just(student1,student2).map(new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                return student.getName();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG,s);
            }
        });

    }

    /**
     * flatmap使用
     */
    private void rxJavaFlatMap(){
        Student student1=new Student();
        student1.setName("张三");
        Student student2=new Student();
        student2.setName("李四");

        Course course1=new Course();
        course1.setCourseName("语文");
        Course course2=new Course();
        course2.setCourseName("英语");
        List courses=new ArrayList();
        courses.add(course1);
        courses.add(course2);
        student1.setCourseList(courses);
        student2.setCourseList(courses);

        Observable.just(student1,student2).flatMap(new Func1<Student, Observable<Course>>() {

            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourseList());
            }
        }).subscribe(new Action1<Course>() {


            @Override
            public void call(Course course) {
                Log.d(TAG,course.getCourseName());
            }
        });

    }

    /**
     * 线程控制
     */
    private void rxJavaScheduler(){
        Observable.just("Hello", "Wrold")
                .subscribeOn(Schedulers.newThread())//指定：在新的线程中发起
                .observeOn(Schedulers.io())         //指定：在io线程中处理
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return "";
//                        return handleString(s);       //处理数据
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//指定：在主线程中处理
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
//                        show(s);                       //消费事件
                    }
                });
    }
}
