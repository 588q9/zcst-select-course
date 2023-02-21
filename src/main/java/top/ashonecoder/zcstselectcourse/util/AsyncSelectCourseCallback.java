package top.ashonecoder.zcstselectcourse.util;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class AsyncSelectCourseCallback implements ListenableFutureCallback<ResponseEntity<String>> {

    @Override
    public void onFailure(Throwable ex) {
        System.out.println("出现错误!!!");
        ex.printStackTrace();
    }

    @Override
    public void onSuccess(ResponseEntity<String> result) {
        //TODO 查看选课返回结果
        System.out.println(result.getBody());
        System.out.println("选课成功");
        System.out.println();
    }
}
