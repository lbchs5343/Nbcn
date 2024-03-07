/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.AsyncTask
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Override
 */
package jiesheng;

import android.content.Context;
import android.os.AsyncTask;

public class 异步任务
        extends 组件 {
    private MyTask task = new MyTask();
    private 后台操作 $event_internal_后台操作;
    private 进度被改变 $event_internal_进度被改变;
    private 操作完成 $event_internal_操作完成;

    public 异步任务(Context context) {
        super(context);
    }

    public 异步任务() {
    }

    public void 开始后台操作() {
        this.task.executeOnExecutor(MyTask.THREAD_POOL_EXECUTOR, new Object[0]);
    }

    public void 更新进度(int n) {
        this.task.updateProgress(n);
    }

    public void 取消() {
        this.task.cancel(true);
    }

    public void 置后台操作(后台操作 后台操作2) {
        this.$event_internal_后台操作 = 后台操作2;
    }

    public void 置进度被改变(进度被改变 进度被改变2) {
        this.$event_internal_进度被改变 = 进度被改变2;
    }

    public void 置操作完成(操作完成 操作完成2) {
        this.$event_internal_操作完成 = 操作完成2;
    }

    @Override
    public void 初始化事件() {
    }

    public static interface 操作完成 {
        public void 操作完成();
    }

    public static interface 进度被改变 {
        public void 进度被改变(int var1);
    }

    public static interface 后台操作 {
        public Object 后台操作();
    }

    class MyTask
            extends AsyncTask {
        protected Object doInBackground(Object[] objectArray) {
            if (异步任务.this.$event_internal_后台操作 != null) {
                异步任务.this.$event_internal_后台操作.后台操作();
            }
            return null;
        }

        protected void onProgressUpdate(Object[] objectArray) {
            super.onProgressUpdate(objectArray);
            if (异步任务.this.$event_internal_进度被改变 != null) {
                异步任务.this.$event_internal_进度被改变.进度被改变((Integer) objectArray[0]);
            }
        }

        protected void onPostExecute(Object object) {
            super.onPostExecute(object);
            if (异步任务.this.$event_internal_操作完成 != null) {
                异步任务.this.$event_internal_操作完成.操作完成();
            }
        }

        public void updateProgress(int n) {
            this.publishProgress(new Object[]{n});
        }
    }
}

